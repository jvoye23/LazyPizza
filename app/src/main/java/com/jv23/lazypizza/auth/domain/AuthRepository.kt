package com.jv23.lazypizza.auth.domain

import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.EmptyResult

interface AuthRepository {

    suspend fun login()
}