package com.example.lifeshare_android.base.activity

import android.app.KeyguardManager

import android.content.Intent

import android.hardware.fingerprint.FingerprintManager

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties

import android.view.animation.AlphaAnimation
import android.view.animation.Animation

import android.widget.Toast

import androidx.annotation.LayoutRes

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProviders

import com.example.lifeshare_android.base.viewmodel.BaseViewModel

import java.io.IOException

import java.security.*
import java.security.cert.CertificateException

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity() {

    protected val anim: Animation = AlphaAnimation(0.0f, 1.0f)

    protected var keyStore: KeyStore? = null
    protected var cipher: Cipher? = null
    protected var keyGenerator: KeyGenerator? = null
    protected val KEY_NAME: String = "example_key"

    protected lateinit var fingerprintManager: FingerprintManager
    protected lateinit var keyguardManager: KeyguardManager

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModel(): Class<VM>

    protected abstract fun getBindingVariable(): Int

    protected abstract fun initObserver()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        initObserver()
    }

    private fun performDataBinding() {
        performViewDataBinding()
    }

    private fun performViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModel = if(::viewModel.isInitialized) viewModel else ViewModelProviders.of(this).get(getViewModel())
        binding.lifecycleOwner = this
        binding.setVariable(getBindingVariable(), viewModel)
        binding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::binding.isInitialized) binding.unbind()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (VERSION.SDK_INT != VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    protected fun startActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    protected fun startActivityWithFinish(activity: Class<*>) {
        startActivity(Intent(this, activity))
        finish()
    }

    protected fun simpleToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun simpleToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun <T : Fragment?> newInstance(fragment: T): T {
            val args = Bundle()
            fragment!!.arguments = args
            return fragment
        }
    }

    protected fun initSetBlinkAnimation() {
        anim.duration = 50 // You can manage the time of the blink with this parameter
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 5 // blink time
    }

    //Cipher Init()
    protected fun cipherInit(): Boolean {
        cipher = try {
            Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES + "/"
                        + KeyProperties.BLOCK_MODE_CBC + "/"
                        + KeyProperties.ENCRYPTION_PADDING_PKCS7)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: NoSuchPaddingException) {
            throw RuntimeException("Failed to get Cipher", e)
        }
        return try {
            keyStore!!.load(null)
            val key = keyStore!!.getKey(KEY_NAME, null) as SecretKey
            cipher!!.init(Cipher.ENCRYPT_MODE, key)
            true
        } catch (e: KeyPermanentlyInvalidatedException) {
            false
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: CertificateException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        }
    }

    //Key Generator
    protected fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }
        keyGenerator = try {
            KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get KeyGenerator instance", e)
        } catch (e: NoSuchProviderException) {
            throw RuntimeException("Failed to get KeyGenerator instance", e)
        }
        try {
            keyStore!!.load(null)
            keyGenerator!!.init(
                KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build())
            keyGenerator!!.generateKey()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e)
        } catch (e: CertificateException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}