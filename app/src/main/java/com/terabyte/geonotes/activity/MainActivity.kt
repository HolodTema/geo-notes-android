package com.terabyte.geonotes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.databinding.ActivityMainBinding
import com.terabyte.geonotes.fragment.ListFragment
import com.terabyte.geonotes.fragment.MapFragment
import com.terabyte.geonotes.fragment.SettingsFragment
import com.terabyte.geonotes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavMain.selectedItemId = viewModel.liveDataBottomNavMenuItemId.value ?: R.id.menu_item_map
        setFragment(viewModel.liveDataBottomNavMenuItemId.value ?: R.id.menu_item_map)
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNavMain.setOnItemSelectedListener { menuItem ->
            viewModel.setBottomNavMenuItemId(menuItem.itemId)
            true
        }

        viewModel.liveDataBottomNavMenuItemId.observe(this) {
            setFragment(it)
        }
    }

    private fun setFragment(menuItemId: Int) {
        val fragment = when(menuItemId) {
            R.id.menu_item_map -> {
                MapFragment.newInstance()
            }
            R.id.menu_item_list -> {
                ListFragment.newInstance()
            }
            R.id.menu_item_settings -> {
                SettingsFragment.newInstance()
            }
            else -> {
                MapFragment.newInstance()
            }
        }

        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_main_fragments)
        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_main_fragments, fragment)
                .commit()
        }
        else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_main_fragments, fragment)
                .commit()
        }
    }
}