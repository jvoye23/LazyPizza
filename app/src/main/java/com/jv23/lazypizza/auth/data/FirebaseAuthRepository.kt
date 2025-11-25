package com.jv23.lazypizza.auth.data

import com.jv23.lazypizza.auth.domain.AuthRepository
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.EmptyResult

class FirebaseAuthRepository : AuthRepository {

    override suspend fun login() {
        /*
        /**
 * Signs the user in anonymously if they are not already.
 * This is a suspend function, so call it from a coroutine.
 */

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            try {
                auth.signInAnonymously().await()
                Log.d("Firebase", "Signed in anonymously.")
            } catch (e: Exception) {
                Log.e("Firebase", "Anonymous sign-in failed.", e)
            }
        }*/





    }
}

