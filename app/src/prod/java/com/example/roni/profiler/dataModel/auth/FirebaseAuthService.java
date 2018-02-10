package com.example.roni.profiler.dataModel.auth;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.roni.profiler.dataModel.database.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Created by roni on 01/02/18.
 */

public class FirebaseAuthService implements AuthService {
    private static FirebaseAuthService instance;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;

    private FirebaseAuthService() {
        auth = FirebaseAuth.getInstance();
    }

    public static synchronized FirebaseAuthService getInstance() {
        if(instance == null){
            instance = new FirebaseAuthService();
        }
        return instance;
    }

    @Override
    public Completable createAccount(final Credentials credentials) {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter emitter) throws Exception {
                        auth.createUserWithEmailAndPassword(credentials.getEmail(), credentials.getPassword())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Created user successfully and logged him in
                                            //FirebaseUser user = auth.getCurrentUser();
                                            emitter.onComplete();
                                        } else {
                                            // If creation of user faileds
                                            emitter.onError(task.getException());
                                        }
                                    }
                                });
                    }
                }
        );
    }

    @Override
    public Completable loginAccount(final Credentials credentials) {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter emitter) throws Exception {
                        auth.signInWithEmailAndPassword(credentials.getEmail(), credentials.getPassword())
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success
                                            Log.d("FirebaseAuthService", "signInWithEmail:success");
                                            emitter.onComplete();
                                        } else {
                                            // Sign in fails
                                            Log.w("FirebaseAuthService", "signInWithEmail:failure", task.getException());
                                            emitter.onError(task.getException());
                                        }
                                    }
                                });
                    }
                });
    }

    @Override
    public Completable reAuthenticateUserAccount(String credentials) {
        return null;
    }

    @Override
    public Completable logUserOut() {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter emitter) throws Exception {
                        listener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                                if (firebaseAuth.getCurrentUser() == null) {
                                    emitter.onComplete();
                                } else {
                                    emitter.onError(new Exception());
                                }
                                auth.removeAuthStateListener(listener);
                            }
                        };
                        auth.addAuthStateListener(listener);
                        auth.signOut();
                    }
                }
        );
    }

    @Override
    public Completable deleteUserAccount() {
        return null;
    }

    @Override
    public Maybe<User> getUser() {
        Maybe<User> maybe = Maybe.create(
                new MaybeOnSubscribe<User>() {
                    @Override
                    public void subscribe(final MaybeEmitter<User> e) throws Exception {
                        if (listener != null) {
                            auth.removeAuthStateListener(listener);
                        }
                        listener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                if(firebaseUser != null){
                                    // User is logged in
                                    User user = new User(
                                            firebaseUser.getEmail(),
                                            firebaseUser.getUid()
                                    );
                                    Maybe.just(user);
                                    e.onSuccess(user);
                                } else{
                                    // User is logged out
                                    e.onComplete();
                                }
                            }
                        };
                        auth.addAuthStateListener(listener);
                    }
                }
        );
        return maybe;
    }

    @Override
    public void removeAuthListener() {
        if(listener != null) {
            auth.removeAuthStateListener(listener);
            listener = null;
        }
    }

    @Override
    public Completable updateProfileInDB(Profile profile) {
        return null;
    }
}
