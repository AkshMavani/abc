package com.example.delete.bottombar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.delete.R
import com.example.delete.databinding.ActivityMain17Binding

class MainActivity : AppCompatActivity(),Backpressedlistener {
    private lateinit var binding:ActivityMain17Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain17Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val firstFragment=BlankFragment()
        val secondFragment=BlankFragment2()
        val thirdFragment=BlankFragment3()

        setCurrentFragment(firstFragment)

       binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(firstFragment)
                R.id.person->setCurrentFragment(secondFragment)
                R.id.settings->setCurrentFragment(thirdFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed() // Close the app if there are no more fragments in the back stack
        }
    }
}