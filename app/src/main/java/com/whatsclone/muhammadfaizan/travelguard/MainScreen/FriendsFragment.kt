package com.whatsclone.muhammadfaizan.travelguard.MainScreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.whatsclone.muhammadfaizan.travelguard.R

class FriendsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_friends, container, false)
        return view
    }

}
