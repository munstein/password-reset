package com.munstein.passwordreset.views.main

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.munstein.passwordreset.base.BaseActivity
import com.munstein.passwordreset.R
import com.munstein.passwordreset.model.PasswordReset
import com.munstein.passwordreset.util.PasswordValidator
import com.munstein.passwordreset.views.passwordconfirmation.PasswordConfirmationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainMVP.view, View.OnClickListener, TextWatcher{

    private lateinit var progressDialog : MaterialDialog
    private lateinit var msgDialog : MaterialDialog
    private lateinit var presenter: MainMVP.presenter

    private val userId = 1000

    private lateinit var colorStateListError : ColorStateList
    private lateinit var colorStateListDefault : ColorStateList

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = MaterialDialog.Builder(this)
                .content(R.string.loading_message)
                .progress(true, 0)
                .build()

        msgDialog = MaterialDialog.Builder(this)
                .content("")
                .positiveText("ok")
                .build()

        presenter = MainPresenter(this, MainModelRetrofit(), PasswordValidator())


        edit_repeat_password.addTextChangedListener(this)
        edit_new_password.addTextChangedListener(this)
        btn_confirm.setOnClickListener(this)

        init()

    }

    fun init(){
        colorStateListError = ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.colorError))

        colorStateListDefault = ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.colorAccent))
    }

    override fun onClick(view: View?) {
        presenter.post(PasswordReset(edit_new_password.text.toString(), userId))
    }

    override fun afterTextChanged(p0: Editable?) {
        presenter.validate(edit_new_password.text.toString(),
                edit_repeat_password.text.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun showPasswordNotEqualError() {
        txt_password_error.text = getString(R.string.hint_password_match_error)
    }

    override fun showPasswordLengthError() {
        txt_password_error.text = getString(R.string.hint_password_length_error)
    }

    override fun showPasswordNumberError() {
        txt_password_error.text = getString(R.string.hint_password_number_error)
    }

    override fun showTextError(msg: String) {
        txt_password_error.text = msg
    }

    override fun showMsgDialog(msg : String){
        msgDialog.setContent(msg)
        msgDialog.show()
    }

    override fun clearError() {
        txt_password_error.text = ""

        ViewCompat.setBackgroundTintList(edit_new_password,colorStateListDefault)
        ViewCompat.setBackgroundTintList(edit_repeat_password,colorStateListDefault)
    }

    override fun showErrorViews() {
        btn_confirm.background = getDrawable(R.drawable.round_border_inactive)
        imgview_locker.setImageDrawable(getDrawable(R.drawable.ic_password))
        btn_confirm.isEnabled = false

        ViewCompat.setBackgroundTintList(edit_new_password,colorStateListError)
        ViewCompat.setBackgroundTintList(edit_repeat_password,colorStateListError)
    }

    override fun showSuccessViews() {
        btn_confirm.background = getDrawable(R.drawable.round_border_active)
        btn_confirm.isEnabled = true
        imgview_locker.setImageDrawable(getDrawable(R.drawable.ic_password_green))
    }

    override fun showDialog() {
        progressDialog.show()
    }

    override fun hideDialog() {
        progressDialog.hide()
    }

    override fun showPasswordConfirmActivity() {
        val intent = Intent(this, PasswordConfirmationActivity::class.java)
        startActivity(intent)
    }
}
