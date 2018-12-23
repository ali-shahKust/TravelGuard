package com.whatsclone.muhammadfaizan.travelguard.MainScreen.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.models.FriendsModel;
import com.whatsclone.muhammadfaizan.travelguard.R;

import java.util.List;

import butterknife.ButterKnife;

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
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.friends_row_design, viewGroup, false );
        return new FriendsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsHolder holder, int position) {
        FriendsModel model = friendsList.get(position);
        holder.txtFriendsName.setText(model.user_name);
        holder.txtFriendEmail.setText(model.email);
        Picasso.get().load(model.image_url).into(holder.imgFriend);
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class FriendsHolder extends RecyclerView.ViewHolder {
        ImageView imgFriend, imgLocation;
        TextView txtFriendsName, txtFriendEmail;
        public FriendsHolder(View view) {
            super(view);
            imgFriend = view.findViewById(R.id.img_friend);
            imgLocation = view.findViewById(R.id.img_friend_location);
            txtFriendsName = view.findViewById(R.id.txt_friend_name);
            txtFriendEmail = view.findViewById(R.id.txt_friend_email);
        }
    }
}
