package com.example.cats.login

import android.annotation.SuppressLint
import androidx.core.util.PatternsCompat
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.cats.breeds.BaseViewModel
import com.example.cats.breeds.LoginResponse
import javax.inject.Inject

open class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var loginRepository: LoginRepository

    var btnSelected: ObservableBoolean? = null
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var userMutableLiveData = MutableLiveData<LoginUser>()

    init {
        btnSelected = ObservableBoolean(false)
    }

    @SuppressLint("CheckResult")
    fun onLoginClick() {

        val loginUser = LoginUser(emailAddress.value, password.value)
        dataLoading.value = true
        loginRepository.login(loginUser,
            { loginResponse -> onLoginSucess(loginUser, loginResponse) },
            { throwable -> onLoginError(throwable) })
    }

    fun onLoginError(it: Throwable?) {

    }

    fun onLoginSucess(
        loginUser: LoginUser,
        result: LoginResponse
    ) {
        //save the user token somewhere
        userMutableLiveData.setValue(loginUser)

    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(isEmailValid(s.toString()) && isPasswordLengthGreaterThan5(password.value))
    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(isEmailValid(emailAddress.value) && isPasswordLengthGreaterThan5(s.toString()))
    }

    fun isEmailValid(strEmailAddress: String?): Boolean {
        return strEmailAddress?.let {
            PatternsCompat.EMAIL_ADDRESS.matcher(strEmailAddress).matches()
        } ?: run {
            false
        }
    }

    fun isPasswordLengthGreaterThan5(password: String?): Boolean {
        return password?.let {
            password.length > 5
        } ?: run {
            false
        }
    }
}