package com.munstein.passwordreset

import com.munstein.passwordreset.util.PasswordValidator
import junit.framework.Assert
import org.junit.Test

/**
 * Created by @Munstein on 21/02/2018.
 */
class PasswordValidatorTest {

    val passwordValidator = PasswordValidator()

    @Test
    fun testPasswordsMatchMinimumSize(){
        val validTestPassword = "123456"
        val invalidTestPassword = "12345"
        Assert.assertEquals(true,
                passwordValidator.passwordHasMinimumSize(validTestPassword))
        Assert.assertEquals(false,
                passwordValidator.passwordHasMinimumSize(invalidTestPassword))
    }

    @Test
    fun testPasswordsMatchHaveAtLeastOneNumber(){
        val validTestPassword = "abcde1"
        val invalidTestPassword = "abcdef"
        Assert.assertEquals(true,
                passwordValidator.passwordHasNumber(validTestPassword))
        Assert.assertEquals(false,
                passwordValidator.passwordHasNumber(invalidTestPassword))
    }

    @Test
    fun testPasswordsMatchSameValues(){
        val password1 = "123456"
        val password2 = "123456"
        Assert.assertEquals(true,
                passwordValidator.passwordsAreEqual(password1, password2))
    }

    @Test
    fun testPasswordsMatchWithDifferentValues(){
        val password1 = "123456"
        val password2 = "abcdef"
        Assert.assertEquals(false,
                passwordValidator.passwordsAreEqual(password1, password2))
    }

    @Test
    fun testPasswordIsValid(){
        val validTestNewPassword = "!1eef0"
        val validTestConfirmPassword = "!1eef0"
        Assert.assertEquals(true,
                passwordValidator.passwordsAreValid(validTestNewPassword, validTestConfirmPassword))
    }

    @Test
    fun testPasswordIsInvalidDifferent(){
        val validTestNewPassword = "!1eef0"
        val validTestConfirmPassword = "!"
        Assert.assertEquals(false,
                passwordValidator.passwordsAreValid(validTestNewPassword, validTestConfirmPassword))
    }

}