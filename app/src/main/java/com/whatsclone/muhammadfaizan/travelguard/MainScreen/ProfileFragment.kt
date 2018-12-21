package com.whatsclone.muhammadfaizan.travelguard.MainScreen


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
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
import com.whatsclone.muhammadfaizan.travelguard.Signin_Register.View.LoginView
import com.whatsclone.muhammadfaizan.travelguard.UserRequests.View.ActivityRequests
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    lateinit var imgProfile: CircleImageView
    lateinit var imgRequests: ImageView
    lateinit var imgSignout: ImageView
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
        imgRequests = view.findViewById(R.id.imgRequests)
        imgSignout = view.findViewById(R.id.img_signout)
        txtUserName = view.findViewById(R.id.txtUserName)
        txtUserEmail = view.findViewById(R.id.txtUserEmail)
        imgEdit = view.findViewById(R.id.imgEdit)

        imgRequests.setOnClickListener {
            startActivity(Intent(view.context, ActivityRequests::class.java))
            activity!!.finish()
        }

        imgSignout.setOnClickListener {
            var dialog = AlertDialog.Builder(view.context, R.style.AlertDialogCustom);
            dialog.setTitle("Confirm!")
            dialog.setMessage("Are you sure you want to sign out?")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(view.context, LoginView::class.java))
                activity!!.finish()
            }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })

            dialog.show()
        }
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
