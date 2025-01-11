package com.example.firebase_130

import android.app.Application
import com.example.firebase_130.di.MahasiswaContainer

class MahasiswaApp : Application() {
    lateinit var containerApp: MahasiswaContainer

    override fun onCreate() {
        super.onCreate()
        containerApp = MahasiswaContainer(this)
    }
}