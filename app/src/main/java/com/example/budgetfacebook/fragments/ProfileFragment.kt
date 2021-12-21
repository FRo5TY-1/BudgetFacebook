package com.example.budgetfacebook.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.budgetfacebook.EditProfileActivity
import com.example.budgetfacebook.R
import com.example.budgetfacebook.adapters.ViewPagerFragmentsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerFragmentsAdapter: ViewPagerFragmentsAdapter
    private lateinit var profileName: TextView
    private lateinit var profilePicture: ImageView
    private lateinit var editProfileButton: Button
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPagerFragmentsAdapter = ViewPagerFragmentsAdapter(this)
        profileName = view.findViewById(R.id.profileName)
        profilePicture = view.findViewById(R.id.profilePictureView)
        editProfileButton = view.findViewById(R.id.editProfileButton)

        viewPager2.adapter = viewPagerFragmentsAdapter

        init()

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    private fun init() {

        editProfileButton.setOnClickListener {
            activity?.let{
                val intent = Intent (it, EditProfileActivity::class.java)
                it.startActivity(intent)
            }
            activity?.finish()
        }

        db.child(auth.currentUser?.uid!!).get().addOnSuccessListener {
            if (it.exists()) {
                val firstName = it.child("firstName").value
                val lastName = it.child("lastName").value
                val pictureUrl = it.child("pictureUrl").value

                Glide.with(this)
                    .load(pictureUrl)
                    .circleCrop()
                    .into(profilePicture)

                profileName.text = "$firstName $lastName"
            }
        }

    }
}
