package com.example.firebasegmailreplaytuesday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebasegmailreplaytuesday.adapters.UserAdapter
import com.example.firebasegmailreplaytuesday.databinding.ActivityRealBinding
import com.example.firebasegmailreplaytuesday.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RealActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference

    private val TAG = "RealActivity"

    lateinit var binding: ActivityRealBinding

    lateinit var userAdapter: UserAdapter
    var list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser


        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")


        val email = currentUser!!.email
        val displayName = currentUser.displayName
        val phoneNumber = currentUser.phoneNumber
        val photoUrl = currentUser.photoUrl
        val uid = currentUser.uid

        val user = User(email, displayName, phoneNumber, photoUrl.toString(), uid)

        //write
        reference.child(uid).setValue(user)

        //read
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                list.clear()

                val children = snapshot.children

                for (child in children) {

                    val value = child.getValue(User::class.java)

                    if (value != null && uid != value.uid) {

                        list.add(value)

                    }
                }

                userAdapter = UserAdapter(list, object : UserAdapter.OnItemClickListener {

                    override fun onItemClick(user: User) {


                    }

                })

                binding.userRv.adapter = userAdapter

            }

            override fun onCancelled(error: DatabaseError) {


            }

        })

        Toast.makeText(this, "${currentUser!!.email}", Toast.LENGTH_SHORT).show()

    }
}