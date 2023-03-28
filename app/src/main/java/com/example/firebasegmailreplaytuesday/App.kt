package com.example.firebasegmailreplaytuesday

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//offline firebase database uchun
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

    }
}