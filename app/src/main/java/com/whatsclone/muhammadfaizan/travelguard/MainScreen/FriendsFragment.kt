package com.whatsclone.muhammadfaizan.travelguard.MainScreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.adapters.FriendsAdapter
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.models.FriendsModel

import com.whatsclone.muhammadfaizan.travelguard.R

class FriendsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var friendsList: MutableList<FriendsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_friends, container, false)
        initViews(view)
        populateList(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.friends_list)
        friendsList = ArrayList<FriendsModel>()
    }

    private fun populateList(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        var adapter: FriendsAdapter = FriendsAdapter(friendsList, view.context)
        recyclerView.adapter = adapter

        FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").child(FirebaseAuth.getInstance().uid!!)
                .child("Friends").addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError) {}

                    override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

                    override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

                    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                        var obj: FriendsModel = p0.getValue(FriendsModel::class.java)!!
                        friendsList.add(obj)
                        adapter.notifyDataSetChanged()
                    }

                    override fun onChildRemoved(p0: DataSnapshot) {}
                })
    }

}
