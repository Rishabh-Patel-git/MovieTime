package repository;

import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import Models.DetailsModel.Results;

public class FirebaseRepo {

    private DatabaseReference firebaseRef;
    private FirebaseUser firebaseUser;


    public FirebaseRepo() {
        firebaseRef = FirebaseDatabase.getInstance().getReference();
        firebaseUser =  FirebaseAuth.getInstance().getCurrentUser();
    }

    public void setWatchedMovies(Results movies) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Movies").child(movies.getImdb_id()).setValue(movies);
        firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Movies").child(movies.getImdb_id()).removeValue();

    }

    public void setWatchLaterMovies(Results movies) {
        if (firebaseUser != null) {
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Movies").child(movies.getImdb_id()).setValue(movies);
        }
    }

    public void setWatchedShows(Results shows) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watched Shows").child(shows.getImdb_id()).setValue(shows);
        firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Shows").child(shows.getImdb_id()).removeValue();
    }

    public void setWatchLaterShows(Results shows) {
        if (firebaseUser != null)
            firebaseRef.child("Users").child(firebaseUser.getUid()).child("Watch Later Shows").child(shows.getImdb_id()).setValue(shows);
    }



}
