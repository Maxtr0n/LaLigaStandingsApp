package hu.schutz.laligastandingsapp.ui.standings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hu.schutz.laligastandingsapp.R
import hu.schutz.laligastandingsapp.databinding.FragmentStandingsBinding
import hu.schutz.laligastandingsapp.util.Resource

@AndroidEntryPoint
class StandingsFragment : Fragment() {

    private val viewModel: StandingsViewModel by viewModels()

    companion object {
        fun newInstance() = StandingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStandingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val standingsAdapter = StandingsAdapter()
        binding.apply {
            rvStandings.apply {
                adapter = standingsAdapter
                layoutManager = LinearLayoutManager(activity)
            }

            viewModel.teams.observe(viewLifecycleOwner) { result ->
                standingsAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorTextView.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                errorTextView.text = result.error?.localizedMessage
            }
        }

        return view
    }

}