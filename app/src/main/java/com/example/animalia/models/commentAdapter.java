package com.example.animalia.models;


import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentViewHolder> {

    Context context;
    List<comment> commentList;


    public commentAdapter(Context context, List<comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }


    public class commentViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTv,commentTv,timeTv;



        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.nameComment);
            commentTv=itemView.findViewById(R.id.commentText);
            timeTv=itemView.findViewById(R.id.timeComment);
        }
    }

    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=LayoutInflater.from(context).inflate(R.layout.comment_layout,parent,false);

        return new commentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull commentViewHolder holder, int position)
    {
        String name = commentList.get(position).getName();
        String comment = commentList.get(position).getComment();
        String timestamp=commentList.get(position).getTimestamp();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String pTime = DateFormat.format("dd/MM/yyyy\n\t\tHH:mm", calendar).toString();



        holder.nameTv.setText(name);
        holder.commentTv.setText(comment);
        holder.timeTv.setText(pTime);



    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


}
