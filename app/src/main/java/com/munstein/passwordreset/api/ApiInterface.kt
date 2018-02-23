package com.munstein.passwordreset.api

import com.munstein.passwordreset.model.PasswordReset
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by @Munstein on 23/02/2018.
 */
interface ApiInterface {
    @POST("/resetPassword")
    fun resetPassword(@Body passwordReset : PasswordReset) : Observable<Response<Unit>>
}