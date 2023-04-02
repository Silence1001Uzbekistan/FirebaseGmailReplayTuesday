package com.example.firebasegmailreplaytuesday

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.example.firebasegmailreplaytuesday.adapters.MessageAdapter
import com.example.firebasegmailreplaytuesday.databinding.ActivityMessageBinding
import com.example.firebasegmailreplaytuesday.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMessageBinding

    lateinit var firebaseAuth: FirebaseAuth

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference

    lateinit var messageAdapter: MessageAdapter

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        val user = intent.getSerializableExtra("user") as User

        binding.sendBtn.setOnClickListener {

            val m = binding.messageEt.text.toString()
            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val date = simpleDateFormat.format(Date())
            val message = com.example.firebasegmailreplaytuesday.models.Message(
                m, date,
                firebaseAuth.currentUser!!.uid, user.uid
            )

            val key = reference.push().key
            //tomonidan
            reference.child("${firebaseAuth.currentUser!!.uid}/messages/${user.uid}/$key")
                .setValue(message)

            //ga
            reference.child("${user.uid}/messages/${firebaseAuth.currentUser!!.uid}/$key")
                .setValue(message)

            Toast.makeText(this, "Bosildiii", Toast.LENGTH_SHORT).show()

        }

        reference.child("${firebaseAuth.currentUser!!.uid}/messages/${user.uid}")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = ArrayList<com.example.firebasegmailreplaytuesday.models.Message>()
                    val children = snapshot.children


                    for (child in children) {

                        val value =
                            child.getValue(com.example.firebasegmailreplaytuesday.models.Message::class.java)

                        if (value != null) {

                            list.add(value)

                        }

                    }

                    messageAdapter = MessageAdapter(list, firebaseAuth.currentUser!!.uid)
                    binding.messageRv.adapter = messageAdapter

                }

                override fun onCancelled(error: DatabaseError) {


                }

            })


    }
}