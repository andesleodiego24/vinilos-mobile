package com.example.drawerapp.ui.adapaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.drawerapp.R
import com.example.drawerapp.databinding.AlbumItemBinding
import com.example.drawerapp.models.Album
import com.example.drawerapp.ui.AlbumListFragmentDirections

class AlbumListAdapter: RecyclerView.Adapter<AlbumListAdapter.AlbumItemViewHolder>() {
    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumItemViewHolder.LAYOUT,
            parent,
            false)
        return AlbumItemViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumListFragmentDirections.openAlbumDetails(
                albumName = albums[position].name,
                albumLabel = albums[position].recordLabel,
                albumCover = albums[position].cover,
                albumPerformers = albums[position].performers,
                albumReleseaDate = albums[position].releaseDate,
                albumDescription = albums[position].description
            )

            holder.viewDataBinding.root.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumItemViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.albumCover)
        }
    }
}