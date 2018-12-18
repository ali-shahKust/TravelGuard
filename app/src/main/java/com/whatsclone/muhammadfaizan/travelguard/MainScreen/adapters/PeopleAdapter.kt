package com.whatsclone.muhammadfaizan.travelguard.MainScreen.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.models.PeopleModel
import com.whatsclone.muhammadfaizan.travelguard.R
import de.hdodenhof.circleimageview.CircleImageView

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
        p0.txt_user_email.text = obj.email
        p0.txt_user_name.text = obj.user_name
        Picasso.get().load(obj.image_url).into(p0.img_user)
    }

    inner class PeopleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txt_user_name: TextView = view.findViewById(R.id.txt_people_name)
        var txt_user_email: TextView = view.findViewById(R.id.txt_people_email)
        var img_user: CircleImageView = view.findViewById(R.id.img_people_profile)
    }

}