package com.example.lifeshare_android.base.viewmodel

import android.app.Application

import androidx.lifecycle.AndroidViewModel

import com.example.lifeshare_android.database.repository.RoomRepository
import com.example.lifeshare_android.database.repository.TokenRepository
import com.example.lifeshare_android.database.repository.UserIdRepository
import com.example.lifeshare_android.widget.SingleLiveEvent

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<D> protected constructor(application: Application) : AndroidViewModel(application) {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val tokenManager: TokenRepository = TokenRepository(application)
    private val userIdManager: UserIdRepository = UserIdRepository(application)
    protected val repository: RoomRepository = RoomRepository(application)

    val onErrorEvent: SingleLiveEvent<Throwable> = SingleLiveEvent()

    var token: String
        get() = tokenManager.token.token
        set(value) = tokenManager.setToken(value)

    var userId: String
        get() = userIdManager.userId.id
        set(value) = userIdManager.setUserId(value)

    fun addDisposable(single: Single<*>, observer: DisposableSingleObserver<*>) {
        disposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer as SingleObserver<Any>) as Disposable)
    }

    val baseObserver: DisposableSingleObserver<String>
        get() = object : DisposableSingleObserver<String>() {
            override fun onSuccess(s: String) {
                onRetrieveBaseSuccess(s)
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        }

    val dataObserver: DisposableSingleObserver<D>
        get() = object : DisposableSingleObserver<D>() {
            override fun onSuccess(t: D) {
                onRetrieveDataSuccess(t)
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        }

    protected open fun onRetrieveDataSuccess(data: D) {}

    protected open fun onRetrieveBaseSuccess(message: String) {}

    open fun onCreate() {}
}