package com.example.roni.profiler;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.database.DatabaseSource;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.profilePage.ProfilePageContract;
import com.example.roni.profiler.ui.profilePage.ProfilePagePresenter;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by roni on 10/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfilePagePresenterTest {

    @Mock
    AuthService authService;

    @Mock
    DatabaseSource databaseSource;

    @Mock
    ProfilePageContract.AppView profilePageAppView;

    private ProfilePagePresenter<ProfilePageContract.AppView> presenter;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);

        presenter = new ProfilePagePresenter<>(
                authService,
                TestSchedulerProvider.getInstance(),
                new CompositeDisposable(),
                databaseSource);
        presenter.onAttach(profilePageAppView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.onDetach();
    }

    @Test
    public void whenFabButtonIsClickedOpenEditProfileScreen(){
        presenter.onEditProfileClick();
        verify(profilePageAppView).goToEditProfileActivity(any(String.class));
    }

    @Test
    public void whenLogoutButtonIsClickedShowDialog(){
        presenter.onLogoutClick();
        verify(profilePageAppView).showLogoutDialog();
    }

    @Test
    public void whenLogoutIsConfirmedAndIsSuccessfulGoToLoginScreen(){
        Mockito.when(authService.logUserOut())
                .thenReturn(Completable.complete());
        presenter.onLogoutConfirmed();
        verify(profilePageAppView).goToLoginActivity();
        Mockito.verify(profilePageAppView).showLoading();
        Mockito.verify(profilePageAppView).hideLoading();
    }

    @Test
    public void whenLogoutIsConfirmedAndFailsShowMessage(){
        Mockito.when(authService.logUserOut())
                .thenReturn(Completable.error(new Exception("")));
        presenter.onLogoutConfirmed();
        Mockito.verify(profilePageAppView).showLoading();
        Mockito.verify(profilePageAppView).hideLoading();
        Mockito.verify(profilePageAppView).showMessage(Mockito.anyString());
    }

    @Test
    public void whenPhotoIsClickedShowImagePicker(){
        presenter.onThumbnailClick();
        verify(profilePageAppView).openPhotoGallery();
    }
}
