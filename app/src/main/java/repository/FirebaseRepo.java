package repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import Models.DetailsModel.Results;

public class FirebaseRepo {

    private DatabaseReference firebaseRef;
    private FirebaseUser firebaseUser;
    private MutableLiveData<List<Results>> watchedMoviesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Results>> watchLaterMoviesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Results>> watchedShowsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Results>> watchLaterShowsLiveData = new MutableLiveData<>();

    public LiveData<List<Results>> getWatchedMoviesLiveData() {
        getWatchedMovies();
        return watchedMoviesLiveData;
    }

    public LiveData<List<Results>> getWatchLaterMoviesLiveData() {
        getWatchLaterMovies();
        return watchLaterMoviesLiveData;
    }

    public LiveData<List<Results>> getWatchLaterShowsLiveData() {

        getWatchLaterShows();
        return watchLaterShowsLiveData;
    }

    public LiveData<List<Results>> getWatchedShowsLiveData() {
        getWatchedShows();
        return watchedShowsLiveData;
    }



    public FirebaseRepo() {
        firebaseRef = FirebaseDatabase.getInstance().getReference();
        firebaseUser =  FirebaseAuth.getInstance().getCurrentUser();
    }

    public void setWatchedMovies(Results movies) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Movies").child(movies.getImdb_id()).setValue(movies);
    }

    public void setWatchLaterMovies(Results movies) {
        if (firebaseUser != null) {
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Movies").child(movies.getImdb_id()).setValue(movies);
        }
    }

    public void setWatchedShows(Results shows) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Shows").child(shows.getImdb_id()).setValue(shows);
    }

    public void setWatchLaterShows(Results shows) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Shows").child(shows.getImdb_id()).setValue(shows);
    }

    private void getWatchedMovies() {
        List<Results> list = new ArrayList<>();
        if (firebaseUser != null) {
            Query moviesQuery = firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Movies");
            moviesQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    list.add(snapshot.getValue(Results.class));
                    watchedMoviesLiveData.setValue(list);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void getWatchLaterMovies() {
        List<Results> list1 = new ArrayList<>();
        Query query = firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Movies");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                list1.add(snapshot.getValue(Results.class));
                watchLaterMoviesLiveData.setValue(list1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getWatchedShows(){
        List<Results> list = new ArrayList<>();
        firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Shows").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                list.add(snapshot.getValue(Results.class));
                watchedShowsLiveData.setValue(list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getWatchLaterShows(){
        List<Results> list = new ArrayList<>();
        firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Shows").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                list.add(snapshot.getValue(Results.class));
                watchLaterShowsLiveData.setValue(list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
