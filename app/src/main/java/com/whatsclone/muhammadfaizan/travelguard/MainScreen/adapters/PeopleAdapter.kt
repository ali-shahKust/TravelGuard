package com.whatsclone.muhammadfaizan.travelguard.MainScreen.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.models.PeopleModel

class PeopleAdapter constructor(context : Context, userList : List<PeopleModel>) : RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {
    var context = context
    var userList = userList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PeopleHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: PeopleHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class PeopleHolder(view : View) : RecyclerView.ViewHolder(view){

    }

}