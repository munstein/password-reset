package com.munstein.passwordreset

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.munstein.passwordreset.views.main.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by @Munstein on 23/02/2018.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext()
    }

    @Test
    fun testDifferentPasswordsErrorAppear() {
        onView(withId(R.id.edit_new_password))
                .perform(typeText("12345"))
        closeSoftKeyboard()
        onView(withId(R.id.edit_repeat_password))
                .perform(typeText("54321"))
        closeSoftKeyboard()
        onView(withText(context.getString(R.string.hint_password_match_error)))
                .check(matches(isDisplayed()));
    }

    @Test
    fun testLengthPasswordsErrorAppear() {
        Espresso.onView(withId(R.id.edit_new_password))
                .perform(typeText("123"))
        closeSoftKeyboard()
        onView(withId(R.id.edit_repeat_password))
                .perform(typeText("123"))
        closeSoftKeyboard()
        onView(withText(context.getString(R.string.hint_password_length_error)))
                .check(matches(isDisplayed()));
    }

    @Test
    fun testHasNumberPasswordsErrorAppear() {
        onView(withId(R.id.edit_new_password))
                .perform(typeText("abcdef"))
        closeSoftKeyboard()
        onView(withId(R.id.edit_repeat_password))
                .perform(typeText("abcdef"))
        closeSoftKeyboard()
        onView(withText(context.getString(R.string.hint_password_number_error)))
                .check(matches(isDisplayed()));
    }

    @Test
    fun testWithNoPasswordErrors() {
        onView(withId(R.id.edit_new_password))
                .perform(typeText("qazwsx1"))
        closeSoftKeyboard()
        onView(withId(R.id.edit_repeat_password))
                .perform(typeText("qazwsx1"))
        closeSoftKeyboard()
        onView(withId(R.id.txt_password_error))
                .check(matches(not(isDisplayed())))
    }

    @Test
    fun testFullAppFlow() {
        onView(withId(R.id.edit_new_password))
                .perform(typeText("qazwsx1"))
        closeSoftKeyboard()
        onView(withId(R.id.edit_repeat_password))
                .perform(typeText("qazwsx1"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_confirm)).perform(click())
        Thread.sleep(2000)
        onView(withText(context.getString(R.string.confirmation_message)))
                .check(matches(isDisplayed()));
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.munstein.passwordreset", appContext.packageName)
    }
}