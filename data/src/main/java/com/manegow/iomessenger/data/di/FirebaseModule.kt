package com.manegow.iomessenger.data.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun firebaseFirestoreProvider() = FirebaseFirestore.getInstance()
}