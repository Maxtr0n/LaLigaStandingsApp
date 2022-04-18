package hu.schutz.laligastandingsapp.ui.standings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.schutz.laligastandingsapp.data.Team
import hu.schutz.laligastandingsapp.databinding.TeamItemBinding

class StandingsAdapter : ListAdapter<Team, StandingsAdapter.StandingsViewHolder>(TeamComparator()) {

    class StandingsViewHolder(private val binding: TeamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(team: Team) {
                binding.apply {
                    Glide.with(itemView)
                        .load(team.logo)
                        .into(logoImageView)

                    teamNameTextView.text = team.name
                    rankTextView.text = team.rank.toString()
                    pointsTextView.text = team.points.toString()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        val binding = TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StandingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class TeamComparator : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
    }
}