package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Presenter

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts.MainContract
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Model.Model

class Presenter constructor(view: MainContract.IView) : MainContract.IPresenter {

    private var view: MainContract.IView = view
    private lateinit var model: MainContract.IModel
    private lateinit var uri: String;
    private lateinit var userName: String
    private var storageRef: StorageReference = FirebaseStorage.getInstance()
            .getReference("Profile_Pictures/" + FirebaseAuth.getInstance().uid!!.toString() + ".jpg")

    override fun onSaveClicked(uri: String, userName: String) {
        this.userName = userName
        this.uri = uri
        model = Model(uri, userName)
        view.onSaveClickResult(model.validate())
    }

    override fun saveUserToFB() {
        var uploadTask = storageRef.putFile(Uri.parse(uri))
        val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation storageRef.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var userProfileChangeRequest: UserProfileChangeRequest? = UserProfileChangeRequest.Builder()
                        .setDisplayName(this.userName).setPhotoUri(task.result).build()
                FirebaseAuth.getInstance().currentUser!!.updateProfile(userProfileChangeRequest!!).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var map = HashMap<String, String>()
                        map["user_name"] = this.userName
                        map["image_url"] = FirebaseAuth.getInstance().currentUser!!.photoUrl.toString()
                        FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users")
                                .child(FirebaseAuth.getInstance().uid.toString()).updateChildren(map.toMap()).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        view.onFirebaseResult(true, "success")
                                    } else {
                                        view.onFirebaseResult(false, task.exception!!.message.toString())
                                    }
                                }
                    } else {
                        view.onFirebaseResult(false, task.exception!!.message.toString())
                    }
                }
            } else {
                view.onFirebaseResult(false, task.exception!!.message!!.toString())
            }
        }
    }
}