package com.example.animalia.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.animalia.R;
import com.example.animalia.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpFragment extends Fragment {

    User user;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    EditText inputEmail,inputPassword,inputName;
    Button btnSignUp;
    ProgressDialog progressDialog;


    private void Auth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String name = inputName.getText().toString();
        user=new User();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError(getString(R.string.Wrong_email_address));
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError(getString(R.string.Enter_Proper_Password));
        } else if (name.isEmpty()) {
            inputName.setError(getString(R.string.You_forgot_to_write_your_name));
        } else {

            progressDialog.setMessage(getString(R.string.Please_Wait_While_Registration___));
            progressDialog.setTitle(getString(R.string.Registration));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user=new User(name,email,password);
                        DatabaseReference myRef = database.getReference("Users");
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = auth.getCurrentUser();
                        myRef.child(currentUser.getUid()).setValue(user);
                        Toast.makeText(getActivity(), getString(R.string.Registration_Successful), Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(getView()).navigate(R.id.menuFragment);


                    } else {
                        Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();


                }
            });
        }
    }










    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        database=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        inputEmail = view.findViewById(R.id.enter_email);
        inputPassword = view.findViewById(R.id.enter_password);
        inputName = view.findViewById(R.id.full_name);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        progressDialog = new ProgressDialog(getActivity());


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth();

            }
        });


        return view;
    }
}




