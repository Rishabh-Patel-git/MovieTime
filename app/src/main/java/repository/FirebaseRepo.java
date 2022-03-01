package repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.DetailsModel.Results;

public class FirebaseRepo {

    private DatabaseReference firebaseRef;
    private FirebaseAuthRepo authRepo;
    private FirebaseUser firebaseUser;
    private MutableLiveData<List<Results>> watchedMoviesResults = new MutableLiveData<>();

    public LiveData<List<Results>> getWatchedMoviesLiveData() {
        return watchedMoviesResults;
    }
    public FirebaseRepo() {
        firebaseRef = FirebaseDatabase.getInstance().getReference();

    }
    public FirebaseRepo(FirebaseUser firebaseUser) {
        firebaseRef = FirebaseDatabase.getInstance().getReference();
        this.firebaseUser = firebaseUser;
    }

    public void setWatchedMovies(Results movies) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Movies").child(movies.getImdb_id()).setValue(movies);
    }
    public  void setWatchLaterMovies(Results movies){
        if(firebaseUser != null){
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Movies").child(movies.getImdb_id()).setValue(movies);
        }
    }

    public void setWatchedShows(Results shows){
        if(firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Shows").child(shows.getImdb_id()).setValue(shows);
    }
    public void setWatchLaterShows(Results shows){
        if(firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Shows").child(shows.getImdb_id()).setValue(shows);
    }


}
