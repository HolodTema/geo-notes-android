package com.terabyte.geonotes.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.databinding.ActivityMainBinding
import com.terabyte.geonotes.fragment.ListFragment
import com.terabyte.geonotes.fragment.MapFragment
import com.terabyte.geonotes.fragment.SettingsFragment
import com.terabyte.geonotes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureInsets()
        configureBottomNavView()
    }

    private fun configureInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configureBottomNavView() {
        viewModel.liveDataBottomNavChosenId.observe(this) { menuItemId ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameMainFragments, getFragmentFromMenuId(menuItemId))
                .commit()
        }

        binding.bottomNavMain.setOnItemSelectedListener { menuItem ->
            if (viewModel.liveDataBottomNavChosenId.value != menuItem.itemId) {
                viewModel.liveDataBottomNavChosenId.value = menuItem.itemId
            }
            true
        }

        binding.bottomNavMain.selectedItemId = viewModel.liveDataBottomNavChosenId.value!!
    }

    private fun getFragmentFromMenuId(menuItemId: Int): Fragment {
        return when(menuItemId) {
            R.id.menuItemMap -> {
                MapFragment()
            }
            R.id.menuItemList -> {
                ListFragment()
            }
            R.id.menuItemSettings -> {
                SettingsFragment()
            }
            else -> {
                MapFragment()
            }
        }
    }

}