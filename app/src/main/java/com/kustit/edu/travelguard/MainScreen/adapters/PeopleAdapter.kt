package com.kustit.edu.travelguard.MainScreen.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.kustit.edu.travelguard.MainScreen.models.PeopleModel
import com.whatsclone.muhammadfaizan.travelguard.R
import de.hdodenhof.circleimageview.CircleImageView
import es.dmoral.toasty.Toasty

class PeopleAdapter constructor(context: Context, userList: List<PeopleModel>) : RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {
    var context = context
    var userList = userList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PeopleHolder {
        var inflater: LayoutInflater = LayoutInflater.from(p0.context)
        var view: View = inflater.inflate(R.layout.people_row_design, p0, false)
        var obj = PeopleHolder(view)
        return obj
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(p0: PeopleHolder, p1: Int) {
        var obj: PeopleModel = userList.get(p1)
        if (obj.uid == FirebaseAuth.getInstance().uid!!) {
            p0.layout.maxHeight = 0
        }
        p0.txt_user_email.text = obj.email
        p0.txt_user_name.text = obj.user_name
        p0.img_add.setOnClickListener {
            addPerson(obj.uid, p0)
        }
        try{
            Picasso.get().load(obj.image_url).into(p0.img_user)
        } catch (exc : Exception){
            
        }
    }

    private fun addPerson(uid: String, holder: PeopleHolder) {
        var map = HashMap<String, String>()
        map["email"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
        map["image_url"] = FirebaseAuth.getInstance().currentUser!!.photoUrl.toString()
        map["user_name"] = FirebaseAuth.getInstance().currentUser!!.displayName.toString()
        map["uid"] = FirebaseAuth.getInstance().uid!!
        FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users")
                .child(uid).child("Requests").child(FirebaseAuth.getInstance().uid!!.toString()).setValue(map).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toasty.success(context, "Request sent", Toast.LENGTH_SHORT, true).show()
                    } else {
                        Toasty.error(context, task.exception!!.message.toString(), Toast.LENGTH_LONG, true).show()
                    }
                }

    }

    inner class PeopleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txt_user_name: TextView = view.findViewById(R.id.txt_friend_name)
        var txt_user_email: TextView = view.findViewById(R.id.txt_friend_email)
        var img_user: CircleImageView = view.findViewById(R.id.img_friend)
        var img_add: ImageView = view.findViewById(R.id.img_friend_location)
        var layout: ConstraintLayout = view.findViewById(R.id.people_row_layout)
    }

}