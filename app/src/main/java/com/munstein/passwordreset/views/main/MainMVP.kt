package com.munstein.passwordreset.views.main

import com.munstein.passwordreset.model.PasswordReset
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by @Munstein on 23/02/2018.
 */
interface MainMVP {

    interface presenter{
        fun validate(password : String, confirmPassword : String) : Boolean
        fun post(passwordReset: PasswordReset)
    }

    interface view{
        fun showTextError(msg : String)
        fun showMsgDialog(msg : String)
        fun showPasswordNotEqualError()
        fun showPasswordLengthError()
        fun showPasswordNumberError()
        fun clearError()
        fun showErrorViews()
        fun showSuccessViews()
        fun showDialog()
        fun hideDialog()
        fun showPasswordConfirmActivity()
    }

    interface model{
        fun resetPassword(passwordReset: PasswordReset) : Observable<Response<Unit>>
    }
}