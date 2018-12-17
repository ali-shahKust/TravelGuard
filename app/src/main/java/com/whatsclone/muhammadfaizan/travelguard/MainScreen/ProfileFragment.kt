package com.whatsclone.muhammadfaizan.travelguard.MainScreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.whatsclone.muhammadfaizan.travelguard.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    lateinit var imgProfile: CircleImageView
    lateinit var txtUserName: TextView
    lateinit var btnRequests: Button
    lateinit var btnEdit: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        imgProfile = view.findViewById(R.id.imgProfilePicture)
        txtUserName = view.findViewById(R.id.txtUserName)
        btnEdit = view.findViewById(R.id.btnEditProfile)
        btnRequests = view.findViewById(R.id.btnRequests)
    }
}
