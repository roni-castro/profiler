package com.example.roni.profiler;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.ui.createAccount.CreateAccountContract;
import com.example.roni.profiler.ui.createAccount.CreateAccountPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by roni on 04/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountUnitTest {
    private static final String VALID_NAME = "Roni";
    private static final String INVALID_NAME = "";
    private static final String INVALID_EMAIL = "testeteste.com";
    private static final String VALID_EMAIL = "teste@teste.com";
    private static final String VALID_PASSWORD = "123456";
    private static final String EMPTY_PASSWORD = "";
    private static final String INVALID_PASSWORD = "123";


    private CreateAccountPresenter<CreateAccountContract.AppView> presenter;

    @Mock
    AuthService authService;

    @Mock
    private CreateAccountContract.AppView appView;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);

        presenter = new CreateAccountPresenter<>(
                authService,
                TestSchedulerProvider.getInstance(),
                new CompositeDisposable());
        presenter.onAttach(appView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.onDetach();
    }

    @Test
    public void testEmptyNameFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(INVALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testEmptyEmailFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn("");
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testInvalidEmailFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(INVALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testEmptyPasswordFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(EMPTY_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testEmptyConfirmationPasswordFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(EMPTY_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testPasswordAndConfirmationMismatchFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(INVALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testPasswordIsWeakFieldShowsMessage(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(INVALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(INVALID_PASSWORD);
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }

    @Test
    public void testCreationOfAccountSuccess(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(authService.createAccount(Mockito.any(Credentials.class)))
                .thenReturn(Completable.complete());
        presenter.onCreateAccount();
        Mockito.verify(appView).goToProfilePageActivity();
    }

    @Test
    public void testCreationOfAccountFail(){
        Mockito.when(appView.getName()).thenReturn(VALID_NAME);
        Mockito.when(appView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(appView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(appView.getConfirmationPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(authService.createAccount(Mockito.any(Credentials.class)))
                .thenReturn(Completable.error(new Exception()));
        presenter.onCreateAccount();
        Mockito.verify(appView).showMessage(Mockito.anyString());
    }
}
