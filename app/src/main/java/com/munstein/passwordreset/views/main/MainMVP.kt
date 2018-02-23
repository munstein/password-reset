package com.munstein.passwordreset.views.main

/**
 * Created by @Munstein on 23/02/2018. --03:17
 */
interface MainMVP {

    interface presenter{
        fun validate(password : String, confirmPassword : String)
        fun post(password: String, userID : Int)
    }

    interface view{
        fun showError(msg : String)
        fun clearError()
        fun showDialog()
        fun hideDialog()
        fun showPasswordConfirmActivity()
    }
}