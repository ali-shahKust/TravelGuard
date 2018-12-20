package com.whatsclone.muhammadfaizan.travelguard.UserRequests.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whatsclone.muhammadfaizan.travelguard.R;
import com.whatsclone.muhammadfaizan.travelguard.UserRequests.Model.RequestModel;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private Context context;
    private List<RequestModel> userList;

    public RequestAdapter(Context context, List<RequestModel> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.request_row_design, viewGroup, false);
        return new RequestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder requestHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class RequestHolder extends RecyclerView.ViewHolder
    {

        public RequestHolder(@NonNull View itemView) {
            super(itemView);
        }
    }}
