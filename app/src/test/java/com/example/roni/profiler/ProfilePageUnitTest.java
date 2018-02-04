package com.example.roni.profiler;

import com.example.roni.profiler.createAccount.CreateAccountContract;
import com.example.roni.profiler.createAccount.CreateAccountPresenter;
import com.example.roni.profiler.dataModel.auth.AuthService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by roni on 04/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfilePageUnitTest {
    private static final String VALID_NAME = "Roni";
    private static final String INVALID_NAME = "";
    private static final String INVALID_EMAIL = "testeteste.com";
    private static final String VALID_EMAIL = "teste@teste.com";
    private static final String VALID_PASSWORD = "123456";
    private static final String EMPTY_PASSWORD = "";
    private static final String INVALID_PASSWORD = "123";


    private CreateAccountPresenter presenter;

    @Mock
    private AuthService authService;

    @Mock
    private CreateAccountContract.View view;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);

        presenter = new CreateAccountPresenter();
    }

    @Test
    public void testEmptyNameFieldShowsMessage(){
        Mockito.when(view.getName()).thenReturn(INVALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }

    @Test
    public void testEmptyEmailFieldShowsMessage(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn("");
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }

    @Test
    public void testInvalidEmailFieldShowsMessage(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(INVALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }

    @Test
    public void testEmptyPasswordFieldShowsMessage(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(EMPTY_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }

    @Test
    public void testEmptyConfirmationPasswordFieldShowsMessage(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(EMPTY_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }

    @Test
    public void testPasswordAndConfirmationMismatchFieldShowsMessage(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(INVALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }

    @Test
    public void testCreationOfAccountSuccess(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).onAccountCreatedSuccessfully();
        Mockito.verify(view).goToProfilePageActivity();
    }

    @Test
    public void testCreationOfAccountFail(){
        Mockito.when(view.getName()).thenReturn(VALID_NAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(view).showToast(Mockito.anyString());
    }
}
