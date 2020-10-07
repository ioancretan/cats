package com.example.cats

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cats.breeds.LoginResponse
import com.example.cats.login.LoginUser
import com.example.cats.login.LoginViewModel
import com.example.cats.networking.ApiClient
import com.example.cats.networking.RxSingleSchedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class LoginViewModelTest {

    @JvmField
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiClient: ApiClient

    private var viewModel: LoginViewModel? = null


    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel()
        viewModel!!.rxSingleSchedulers = RxSingleSchedulers.TEST_SCHEDULER
        viewModel!!.breedsApiBreeds = apiClient
    }

    @Test
    fun SHOULD_call_onLoginSucess_WHEN_logged_in_with_success() {
        val viewmodelSpy = spy(viewModel)!!

        val loginUser = LoginUser("", "")
        val loginResponse = LoginResponse("token");

        viewmodelSpy.onLoginClick()

        verify(viewmodelSpy)!!.onLoginSucess(loginUser, loginResponse)
    }

    @Test
    fun SHOULD_return_TRUE_if_email_is_valid() {

        val value = viewModel!!.isEmailValid("ioan.cretan@yahoo.com")

        Assert.assertEquals(true, value)
    }

    @Test
    fun SHOULD_return_FALSE_if_email_is_not_valid() {

        val value = viewModel!!.isEmailValid("ioan.cretan@yahoocom")

        Assert.assertEquals(false, value)
    }

    @Test
    fun SHOULD_return_TRUE_if_password_is_greter_than_5() {

        val value = viewModel!!.isPasswordLengthGreaterThan5("23232323")

        Assert.assertEquals(true, value)
    }

    @Test
    fun SHOULD_return_FALSE_if_password_is_null() {

        val value = viewModel!!.isPasswordLengthGreaterThan5(null)

        Assert.assertEquals(false, value)
    }

    @Test
    fun SHOULD_return_FALSE_if_password_is_not_greter_than_5() {

        val value = viewModel!!.isPasswordLengthGreaterThan5("2")

        Assert.assertEquals(false, value)
    }


}