package com.example.drawerapp.ui.adapaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.drawerapp.R
import com.example.drawerapp.databinding.ArtistItemBinding
import com.example.drawerapp.models.Artist
import com.example.drawerapp.ui.ArtistListFragmentDirections

class ArtistListAdapter: RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder>() {
    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }
        holder.bind(artists[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = ArtistListFragmentDirections.openArtistDetails(
                 artistName = artists[position].name,
                 artistImage = artists[position].image,
                 artistDescription = artists[position].description
            )

            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }

        fun bind(artist: Artist) {
            Glide.with(itemView)
                .load(artist.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.artistImage)
        }
    }
}