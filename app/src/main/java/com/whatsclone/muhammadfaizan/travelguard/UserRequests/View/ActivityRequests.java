package com.whatsclone.muhammadfaizan.travelguard.UserRequests.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity;
import com.whatsclone.muhammadfaizan.travelguard.R;
import com.whatsclone.muhammadfaizan.travelguard.UserRequests.Controller.RequestAdapter;
import com.whatsclone.muhammadfaizan.travelguard.UserRequests.Model.RequestModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRequests extends AppCompatActivity {

    @BindView(R.id.request_list)
    RecyclerView userList;
    private List<RequestModel> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        ButterKnife.bind(this);
        listData = new ArrayList<RequestModel>();
        populateList();
    }

    private void populateList() {
        RequestAdapter adapter = new RequestAdapter(this, listData);
        userList.setLayoutManager(new LinearLayoutManager(this));
        userList.setAdapter(adapter);
        getUsersFromFirebaseDB(adapter);
    }

    private void getUsersFromFirebaseDB(RequestAdapter adapter) {
        FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users")
                .child(FirebaseAuth.getInstance().getUid()).child("Requests").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                RequestModel model = dataSnapshot.getValue(RequestModel.class);
                listData.add(model);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                listData.remove(dataSnapshot.getValue());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ActivityRequests.this, MainActivity.class));
        this.finish();
    }
}
