package com.munstein.passwordreset.util

/**
 * Created by @Munstein on 21/02/2018.
 */
interface IPasswordValidator {
    fun passwordsAreValid(password: String, newPassword: String) : Boolean
    fun passwordsAreEqual(password : String, newPassword : String) : Boolean
    fun passwordHasMinimumSize(password : String) : Boolean
    fun passwordHasNumber(password: String) : Boolean
}