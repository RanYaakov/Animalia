package com.example.animalia.fragments;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.animalia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    EditText inputEmail,inputPassword;
    Button btnSignIn;
    TextView signup;
    ProgressDialog progressDialog;





    private void Auth()
    {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            inputEmail.setError(getString(R.string.Wrong_email_address));
        }
        else if(password.isEmpty() || password.length()<6)
        {
            inputPassword.setError(getString(R.string.Enter_Proper_Password));
        }
        else
        {
            progressDialog.setMessage(getString(R.string.Please_Wait_While_Login___));
            progressDialog.setTitle(getString(R.string.Login));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),getString(R.string.Login_Successful),Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(getView()).navigate(R.id.menuFragment);



                    }else
                    {
                        Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();

                }

            });


        }

    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);



        mAuth = FirebaseAuth.getInstance();
        inputEmail=view.findViewById(R.id.email_address);
        inputPassword=view.findViewById(R.id.pass_word);
        btnSignIn=view.findViewById(R.id.btn_sign_in);
        signup=view.findViewById(R.id.tv_sign_up);
        progressDialog=new ProgressDialog(getActivity());
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.signUpFragment);

            }
        });




        return view;
    }


}