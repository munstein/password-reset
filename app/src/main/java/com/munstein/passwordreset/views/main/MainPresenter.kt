package com.munstein.passwordreset.views.main

import com.munstein.passwordreset.model.PasswordReset
import com.munstein.passwordreset.util.IPasswordValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 23/02/2018.
 */
class MainPresenter : MainMVP.presenter{

    val view : MainMVP.view
    val passwordValidator : IPasswordValidator
    val model : MainMVP.model

    val errorMsg = "Não foi possível concluir a ação. Tente novamente."


    constructor(view : MainMVP.view, model : MainMVP.model, passwordValidator: IPasswordValidator){
        this.view = view
        this.passwordValidator = passwordValidator
        this.model = model
    }

    override fun validate(password: String, confirmPassword: String) : Boolean{

        if(password.length>0  && confirmPassword.length >0) {

            view.showErrorViews()

            if (passwordValidator.passwordsAreEqual(password, confirmPassword) == false) {
                view.showPasswordNotEqualError()
                return false
            }

            if (passwordValidator.passwordHasNumber(password) == false) {
                view.showPasswordNumberError()
                return false
            }

            if (passwordValidator.passwordHasMinimumSize(password) == false) {
                view.showPasswordLengthError()
                return false
            }

            view.clearError()
            view.showSuccessViews()
            return true
        }else{
            view.clearError()
            return false
        }

    }

    override fun post(passwordReset: PasswordReset) {
        view.showDialog()
        try {
            model.resetPassword(passwordReset)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ view.hideDialog() },
                            {
                                view.hideDialog()
                                view.showMsgDialog(errorMsg)
                            },
                            { view.showPasswordConfirmActivity() })
        }catch (exception : Exception){
            view.hideDialog()
            view.showMsgDialog(errorMsg)
        }
    }

}