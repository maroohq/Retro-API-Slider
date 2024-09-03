package com.example.apidemoopenweather

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlideAdapter(val frag: Fragment) :FragmentStateAdapter(frag) {
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
      var frag :Fragment = TodayFragment()

        if (position == 1)
        {
            frag = itemFragment()
        }
        return  frag
    }
}