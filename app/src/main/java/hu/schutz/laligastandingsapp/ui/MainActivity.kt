package hu.schutz.laligastandingsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.schutz.laligastandingsapp.R
import hu.schutz.laligastandingsapp.databinding.ActivityMainBinding
import hu.schutz.laligastandingsapp.ui.about.AboutFragment
import hu.schutz.laligastandingsapp.ui.standings.StandingsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.appToolbar)

        firebaseAnalytics = Firebase.analytics

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<StandingsFragment>(R.id.fragment_container_view)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.aboutMenuItem -> {
            if (supportFragmentManager.findFragmentByTag("AboutFragment") == null) {
                supportFragmentManager.commit {
                    replace<AboutFragment>(R.id.fragment_container_view, "AboutFragment")
                    setReorderingAllowed(true)
                    addToBackStack("name")
                }
            }
            true
        }
        R.id.testCrashMenuItem -> {
            throw RuntimeException("Test Crash")
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}