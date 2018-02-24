package com.munstein.passwordreset

import com.munstein.passwordreset.model.PasswordReset
import com.munstein.passwordreset.views.main.MainModelRetrofit
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by @Munstein on 23/02/2018.
 */
class ApiTest {

    lateinit var mainModelRetrofit: MainModelRetrofit

    @Before
    fun init() {
        mainModelRetrofit = MainModelRetrofit()
    }

    @Test
    fun testResetPassword() {
        try {
            var result = mainModelRetrofit.resetPassword(PasswordReset("123456y", 1000)).blockingFirst()
            Assert.assertEquals(200, result.code())
        } catch (exception: Exception) {
            Assert.fail()
        }
    }

}