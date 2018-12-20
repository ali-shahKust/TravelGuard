package com.whatsclone.muhammadfaizan.travelguard.UserRequests.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.whatsclone.muhammadfaizan.travelguard.R;
import com.whatsclone.muhammadfaizan.travelguard.UserRequests.Model.RequestModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private Context context;
    private List<RequestModel> userList;

    public RequestAdapter(Context context, List<RequestModel> userList) {
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
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        RequestModel model = userList.get(position);
        holder.requestName.setText(model.user_name);
        holder.requestEmail.setText(model.email);
        Picasso.get().load(model.image_url).into(holder.imgRequest);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class RequestHolder extends RecyclerView.ViewHolder {
        CircleImageView imgRequest;
        TextView requestName, requestEmail;
        ImageView imgAccept, imgReject;

        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            imgRequest = itemView.findViewById(R.id.img_request);
            requestName = itemView.findViewById(R.id.txt_name);
            requestEmail = itemView.findViewById(R.id.txt_email);
            imgAccept = itemView.findViewById(R.id.img_accept);
            imgReject = itemView.findViewById(R.id.img_reject);
        }
    }
}
