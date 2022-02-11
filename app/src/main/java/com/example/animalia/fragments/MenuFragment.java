package com.example.animalia.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.animalia.R;
import com.example.animalia.models.Animal;
import com.example.animalia.models.MenuAdapter;
import com.example.animalia.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Animal> dataSet;
    MenuAdapter adapter;
    FirebaseAuth mAuth;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();



        recyclerView= view.findViewById(R.id.my_recycler_view);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataSet = new ArrayList<Animal>();
        dataSet.add(new Animal(R.string.Dog,R.drawable.dog_icon));
        dataSet.add(new Animal(R.string.Lion,R.drawable.lion_icon));
        dataSet.add(new Animal(R.string.Horse,R.drawable.horse_icon));
        dataSet.add(new Animal(R.string.Giraffe,R.drawable.giraffe_icon));
        dataSet.add(new Animal(R.string.Crocodile,R.drawable.crocodile_icon));
        dataSet.add(new Animal(R.string.Deer,R.drawable.deer_icon));
        dataSet.add(new Animal(R.string.Tiger,R.drawable.tiger_icon));
        dataSet.add(new Animal(R.string.Dolphin,R.drawable.dolphin_icon));
        dataSet.add(new Animal(R.string.Rhino,R.drawable.rhino_icon));
        dataSet.add(new Animal(R.string.Chameleon,R.drawable.chameleon_icon));
        dataSet.add(new Animal(R.string.Monkey,R.drawable.monkey_icon));
        dataSet.add(new Animal(R.string.Koala,R.drawable.koala_icon));











        adapter=new MenuAdapter(dataSet);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new MenuAdapter.MenuListener() {
            @Override
            public void onAnimalClicked(int position, View view) {
                switch(position){
                    case 0:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_dogFragment);
                        break;
                    case 1:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_lionFragment);
                        break;
                    case 2:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_horseFragment);
                        break;
                    case 3:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_giraffeFragment);
                        break;
                    case 4:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_crocodileFragment);
                        break;
                    case 5:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_deerFragment);
                        break;
                    case 6:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_tigerFragment);
                        break;
                    case 7:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_dolphinFragment);
                        break;
                    case 8:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_rhinoFragment);
                        break;
                    case 9:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_chameleonFragment);
                        break;
                    case 10:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_monkeyFragment);
                        break;
                    case 11:
                        Navigation.findNavController(getView()).navigate(R.id.action_menuFragment_to_koalaFragment);
                        break;








                }
            }
        });




        return view;
    }
}