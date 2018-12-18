package com.whatsclone.muhammadfaizan.travelguard.MainScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.adapters.PeopleAdapter;
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.models.PeopleModel;
import com.whatsclone.muhammadfaizan.travelguard.R;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<PeopleModel> mList;
    private PeopleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        initViews(view);
        populateList(view);
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.list_people);
        mList = new ArrayList<>();
        adapter = new PeopleAdapter(view.getContext(), mList);
    }

    private void populateList(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                PeopleModel modelObj = dataSnapshot.getValue(PeopleModel.class);
                mList.add(modelObj);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
