package com.example.roni.profiler;

import com.example.roni.profiler.login.LoginContract;
import com.example.roni.profiler.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by roni on 03/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    private LoginPresenter presenter;

    @Mock
    LoginContract.View loginView;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter();
    }

    @Test
    public void whenLoginAttempSucceeds(){
        Mockito.verify(loginView).goToProfilePageActivity();
    }

    @Test
    public void whenLoginAttempFail(){
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenRegisterButtonIsClicked(){
        Mockito.verify(loginView).goToCreateAccountActivity();
    }

    @Test
    public void whenEmailIsEmpty(){
        Mockito.when(loginView.getEmail()).thenReturn("");
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenPasswordIsEmpty(){
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenEmailAndPasswordDoesNotMatch(){
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }
}
