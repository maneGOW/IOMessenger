package com.manegow.iomessenger.usecases.utils

import io.reactivex.Observable

interface ObservableUseCase<T> {

    fun execute(): Observable<T>
}