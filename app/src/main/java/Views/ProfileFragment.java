package Views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.FragmentProfileBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import repository.FirebaseAuthRepo;


public class ProfileFragment extends Fragment {


    private FragmentProfileBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentProfileBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseAuthRepo repo = new FirebaseAuthRepo(getContext());
        repo.getFirebaseUser().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUsers) {
               binding.profileName.setText(firebaseUsers.getDisplayName());
               binding.emailText.setText(firebaseUsers.getEmail());
                Glide.with(getContext()).load(firebaseUsers.getPhotoUrl()).into(binding.profilePic);
                Glide.with(view).load("https://images.wallpapersden.com/image/download/jujutsu-kaisen-satoru-gojo_bGtubmuUmZqaraWkpJRnZWltrWZmamc.jpg")
                        .into(binding.bannerImage);
            }
        });

        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               repo.signOut();
            }
        });

    }
}