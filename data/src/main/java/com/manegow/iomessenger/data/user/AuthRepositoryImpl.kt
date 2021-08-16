package com.manegow.iomessenger.data.user

import com.google.firebase.firestore.FirebaseFirestore
import com.manegow.iomessenger.domain.user.model.User
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val db: FirebaseFirestore): AuthRepository{
    override fun signup(userName: String, password: String): Single<User> {
        return Single.create(SingleOnSubscribe<User>{ emitter ->
            val ref = db.collection("users").document(userName)
            ref.get().addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    val document = task.result
                    if(document!!.exists()){
                        emitter.onError(Exception("El usuario ya existe"))
                    }else {
                        val user = User(userName, password)
                        db.collection("users")
                            .document(userName)
                            .set(user)
                            .addOnSuccessListener { emitter.onSuccess(user) }
                            .addOnFailureListener { emitter.onError(it) }
                    }
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }).subscribeOn(Schedulers.io())
    }

    override fun login(userName: String, password: String): Single<User> {
        return Single.create(SingleOnSubscribe<User> { emitter ->
            val ref = db.collection("users").document(userName)
            ref.get().addOnCompleteListener{
                if(it.isSuccessful){
                    val document = it.result
                    if(document!!.exists()){
                        val user = document.toObject(User::class.java)
                        if(user != null && user.password.contentEquals(password)){
                            emitter.onSuccess(user)
                        } else {
                            emitter.onError(Exception("Password incorrecto"))
                        }
                    } else {
                        emitter.onError(Exception("El usuario no existe"))
                    }
                } else {
                    emitter.onError(it.exception!!)
                }
            }
        }).subscribeOn(Schedulers.io())
    }
}