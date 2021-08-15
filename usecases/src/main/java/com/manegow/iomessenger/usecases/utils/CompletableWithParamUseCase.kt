package com.manegow.iomessenger.usecases.utils

import io.reactivex.Completable

interface CompletableWithParamUseCase<in T> {
    fun execute(t: T): Completable
}