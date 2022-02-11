package com.example.animalia.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.animalia.R;
import com.example.animalia.models.User;
import com.example.animalia.models.comment;
import com.example.animalia.models.commentAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonkeyFragment extends Fragment {

    User user;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    EditText commentText;
    ImageView btnSend;
    List<comment> commentList;
    commentAdapter commentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monkey, container, false);

        user=new User();
        mAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        database=FirebaseDatabase.getInstance();
        String userId=currentUser.getUid();
        DatabaseReference myRef = database.getReference("Users").child(userId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                User value = snapshot.getValue(User.class);
                user.setName(value.getName());
                user.setEmail(value.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        commentText=view.findViewById(R.id.monkey_comment);
        btnSend=view.findViewById(R.id.btn_send_monkey);
        recyclerView= view.findViewById(R.id.monkey_comments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        commentList=new ArrayList<>();
        commentAdapter = new commentAdapter(getContext(),commentList);
        recyclerView.setAdapter(commentAdapter);

        loadComments();




        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComment();
            }
        });



        return view;
    }
    private void loadComments()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        commentList=new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Comments").child("Monkey");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                commentList.clear();
                for (DataSnapshot ds:snapshot.getChildren())
                {

                    comment comment = ds.getValue(comment.class);
                    commentList.add(comment);
                    commentAdapter = new commentAdapter(getContext(),commentList);
                    recyclerView.setAdapter(commentAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void postComment()
    {

        String comment = commentText.getText().toString().trim();

        if(TextUtils.isEmpty(comment))
        {
            Toast.makeText(getContext(),getString(R.string.Comment_is_empty___),Toast.LENGTH_SHORT).show();
            return;
        }


        String timestamp = String.valueOf(System.currentTimeMillis());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Comments").child("Monkey");

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("comment",comment);
        hashMap.put("name",user.getName());
        hashMap.put("email",user.getEmail());
        hashMap.put("timestamp",timestamp);

        ref.child(timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused)
            {
                Toast.makeText(getContext(),getString(R.string.Comment_Added),Toast.LENGTH_SHORT).show();
                commentText.setText("");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });



    }


}