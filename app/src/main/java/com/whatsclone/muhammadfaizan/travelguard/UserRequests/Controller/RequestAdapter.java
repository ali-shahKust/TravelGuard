package com.whatsclone.muhammadfaizan.travelguard.UserRequests.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.whatsclone.muhammadfaizan.travelguard.R;
import com.whatsclone.muhammadfaizan.travelguard.UserRequests.Model.RequestModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

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
        holder.imgAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imgReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("Requests").child(model.uid).setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toasty.info(context, "Requests deleted", Toast.LENGTH_SHORT, true).show();
                        holder.layout.setMaxHeight(0);
                    }
                });
            }
        });
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
        ConstraintLayout layout;

        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            imgRequest = itemView.findViewById(R.id.img_request);
            requestName = itemView.findViewById(R.id.txt_name);
            requestEmail = itemView.findViewById(R.id.txt_email);
            imgAccept = itemView.findViewById(R.id.img_accept);
            imgReject = itemView.findViewById(R.id.img_reject);
            layout = itemView.findViewById(R.id.people_row_layout);
        }
    }
}
