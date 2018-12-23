package com.whatsclone.muhammadfaizan.travelguard.MainScreen.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.whatsclone.muhammadfaizan.travelguard.MainScreen.models.FriendsModel;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsHolder> {


    private List<FriendsModel> friendsList;
    private Context context;

    public FriendsAdapter(List<FriendsModel> friendsList, Context context) {
        this.context = context;
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public FriendsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsHolder friendsHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class FriendsHolder extends RecyclerView.ViewHolder {
        FriendsHolder(View view) {
            super(view);

        }
    }
}
