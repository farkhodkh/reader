package ru.farkhodkhaknazarov.pickabureader.ui.activity

import androidx.viewpager.widget.ViewPager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.farkhodkhaknazarov.pickabureader.R
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.FragmentAdapter
import ru.farkhodkhaknazarov.pickabureader.ui.presenter.MainActivityPresenter
import ru.farkhodkhaknazarov.pickabureader.ui.view.MainView

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {
    lateinit var pager: ViewPager
    lateinit var adapter: FragmentAdapter
    private val presenter by moxyPresenter { MainActivityPresenter() }

    override fun setupMainAdapter() {
        adapter = FragmentAdapter(supportFragmentManager)
        presenter.prepareAdapter(adapter)
    }

    override fun setupPager(adapter: FragmentAdapter) {
        pager = findViewById(R.id.pager)
        pager.adapter = adapter
    }
}