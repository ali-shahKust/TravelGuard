package com.whatsclone.muhammadfaizan.travelguard.MainScreen


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.View.ActivityEditUserProfile
import com.whatsclone.muhammadfaizan.travelguard.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    lateinit var imgProfile: CircleImageView
    lateinit var txtUserName: TextView
    lateinit var txtUserEmail: TextView
    lateinit var imgEdit: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        loadData(view)
        return view
    }

    private fun initViews(view: View) {
        imgProfile = view.findViewById(R.id.imgProfilePicture)
        txtUserName = view.findViewById(R.id.txtUserName)
        txtUserEmail = view.findViewById(R.id.txtUserEmail)
        imgEdit = view.findViewById(R.id.imgEdit)
    }

    private fun loadData(view: View) {
        FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").child(FirebaseAuth.getInstance().uid!!).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                var map: HashMap<String, String> = p0.value as HashMap<String, String>
                Picasso.get().load(map["image_url"]).into(imgProfile)
                txtUserName.text = map["user_name"]
                txtUserEmail.text = map["email"]
            }
        })

        imgEdit.setOnClickListener {
            startActivity(Intent(view.context, ActivityEditUserProfile::class.java))
            activity!!.finish()
        }
    }
}
