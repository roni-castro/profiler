package com.example.roni.profiler;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.profilePage.edit.EditProfileContract;
import com.example.roni.profiler.ui.profilePage.edit.EditProfilePresenter;

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
 * Created by roni on 10/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class EditProfilePresenterTest {
    private static final String VALID_BIO = "I am an Android Developer whose dream is to become and software engineer and an entrepreneur";
    private static final String VALID_INTERESTS =
            "* Develop apps\n " +
            "* Study about financial market and good practice in software development and play games\n" +
            "* See twitch livestreams";

    private EditProfilePresenter<EditProfileContract.AppView> presenter;

    @Mock
    AuthService authService;

    @Mock
    EditProfileContract.AppView editProfileAppView;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);

        presenter = new EditProfilePresenter<>(
                authService,
                TestSchedulerProvider.getInstance(),
                new CompositeDisposable());
        presenter.onAttach(editProfileAppView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.onDetach();
    }

    @Test
    public void whenConfirmButtonIsClickedAndUpdateProfileSuccessfully() {
        Mockito.when(editProfileAppView.getBio()).thenReturn(VALID_BIO);
        Mockito.when(editProfileAppView.getInterests()).thenReturn(VALID_INTERESTS);
        Mockito.when(authService.updateProfileInDB(Mockito.any(Profile.class)))
                .thenReturn(Completable.complete());
        presenter.onConfirmUpdateMenuClick();
        Mockito.verify(editProfileAppView).showLoading();
        Mockito.verify(editProfileAppView).hideLoading();
        Mockito.verify(editProfileAppView).updateProfileBioAndInterest();
    }

    public void whenConfirmButtonIsClickedAndUpdateProfileFails() {
        Mockito.when(editProfileAppView.getBio()).thenReturn(VALID_BIO);
        Mockito.when(editProfileAppView.getInterests()).thenReturn(VALID_INTERESTS);
        Mockito.when(authService.updateProfileInDB(Mockito.any(Profile.class)))
                .thenReturn(Completable.error(new Exception("Error saving profile in DB")));
        presenter.onConfirmUpdateMenuClick();
        Mockito.verify(editProfileAppView).showLoading();
        Mockito.verify(editProfileAppView).hideLoading();
        Mockito.verify(editProfileAppView).showMessage(Mockito.anyString());
    }
}