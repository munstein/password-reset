package com.munstein.passwordreset.views.main

import com.munstein.passwordreset.api.ApiInterface
import com.munstein.passwordreset.api.RetrofitBuilder
import com.munstein.passwordreset.model.PasswordReset
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by @Munstein on 23/02/2018.
 */
class MainModelRetrofit : MainMVP.model {
    override fun resetPassword(passwordReset: PasswordReset): Observable<Response<Unit>> {
        var api = RetrofitBuilder.getRetrofit().create(ApiInterface::class.java)
        return api.resetPassword(passwordReset)
    }
}