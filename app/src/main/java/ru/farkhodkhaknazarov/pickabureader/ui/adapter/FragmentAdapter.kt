package ru.farkhodkhaknazarov.pickabureader.ui.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.farkhodkhaknazarov.pickabureader.ui.view.BaseFragments

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var titels: ArrayList<String> = arrayListOf()
    var fragments: ArrayList<BaseFragments> = arrayListOf()

    override fun getItem(position: Int): BaseFragments {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titels[position]
    }
}