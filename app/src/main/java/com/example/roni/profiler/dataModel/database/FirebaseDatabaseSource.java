package com.example.roni.profiler.dataModel.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by roni on 10/02/18.
 */

public class FirebaseDatabaseSource implements DatabaseSource {
    private static FirebaseDatabaseSource instance;
    private static final String USER_PROFILE = "user_profile";

    private FirebaseDatabaseSource() {
    }

    public static synchronized FirebaseDatabaseSource getInstance() {
        if(instance == null){
            instance = new FirebaseDatabaseSource();
        }
        return instance;
    }

    @Override
    public Completable createNewProfileToUser(final Profile profile) {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter emitter) throws Exception {
                        final DatabaseReference dataBaseProfile =
                                FirebaseDatabase.getInstance().getReference(USER_PROFILE).child(profile.getUserId());
                        String profileUid = dataBaseProfile.push().getKey();
                        profile.setUid(profileUid);
                        dataBaseProfile.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (!snapshot.exists()) {
                                    dataBaseProfile.child(profile.getUid()).setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                emitter.onComplete();
                                            } else {
                                                emitter.onError(task.getException());
                                            }
                                        }
                                    });
                                } else {
                                    emitter.onComplete();
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("FIREBASE", databaseError.toString());
                                emitter.onError(databaseError.toException());
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Completable updateProfile(final Profile profile) {
        return Completable.create(
            new CompletableOnSubscribe() {
                @Override
                public void subscribe(final CompletableEmitter emitter) throws Exception {
                    DatabaseReference databaseUser =
                            FirebaseDatabase
                                    .getInstance()
                                    .getReference(USER_PROFILE)
                                    .child(profile.getUserId());
                    databaseUser
                            .child(profile.getUid())
                            .setValue(profile)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException());
                            }
                        }
                    });
                }
            });
    }

    public Completable deleteUser(final String email, final String password) {
        return Completable.create(
            new CompletableOnSubscribe() {
                @Override
                public void subscribe(final CompletableEmitter emitter) throws Exception {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user != null) {
                        AuthCredential credential = EmailAuthProvider.getCredential(email, password);
                        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("FIREBASE", "User account deleted.");
                                            emitter.onComplete();
                                        } else{
                                            emitter.onError(task.getException());
                                        }
                                    }
                                });

                            }
                        });
                    }
                }
            }
        );

    }

    @Override
    public Observable<Profile> getProfile(final String userUid) {
        return Observable.create(
            new ObservableOnSubscribe<Profile>() {

                @Override
                public void subscribe(final ObservableEmitter<Profile> emitter) throws Exception {
                    final DatabaseReference dataBaseProfile =
                            FirebaseDatabase.getInstance().getReference().child(USER_PROFILE).child(userUid);
                    dataBaseProfile.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Profile profile = null;
                                // There's always only one Profile object associated to each user
                                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                    profile = dataSnapshot.getValue(Profile.class);
                                }
                                emitter.onNext(profile);
                            } else {
                                emitter.onError(new Exception("User does not have a valid profile"));
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("FIREBASE", databaseError.toString());
                            //e.onError(databaseError.toException());
                        }
                    });
                }
            }
        );
    }
}
