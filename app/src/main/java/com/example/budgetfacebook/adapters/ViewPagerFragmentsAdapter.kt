package com.example.budgetfacebook.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.budgetfacebook.fragments.ProfileFragment
import com.example.budgetfacebook.viewpagerfragments.FragmentInformation
import com.example.budgetfacebook.viewpagerfragments.FragmentPhotos
import com.example.budgetfacebook.viewpagerfragments.FragmentVideos

class ViewPagerFragmentsAdapter(activity: ProfileFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentInformation()
            1 -> FragmentPhotos()
            2 -> FragmentVideos()
            else -> FragmentInformation()
        }
    }

}