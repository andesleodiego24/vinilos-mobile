package com.example.drawerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.drawerapp.R


class AlbumDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
        }

        val args: AlbumDetailsFragmentArgs by navArgs()
        val albumName: TextView = activity.findViewById(R.id.album_name)
        val albumLabel: TextView = activity.findViewById(R.id.album_label)
        val albumCover: ImageView = activity.findViewById(R.id.album_cover)
        val albumPerformers: TextView = activity.findViewById(R.id.album_performers)
        val albumYear: TextView = activity.findViewById(R.id.album_year)
        val albumDescription: TextView = activity.findViewById(R.id.album_description)

        albumName.text = args.albumName
        albumLabel.text = args.albumLabel.uppercase()
        Glide.with(this)
            .load(args.albumCover.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_broken_image))
            .into(albumCover)
        albumPerformers.text = args.albumPerformers
        albumYear.text = args.albumReleseaDate.take(4)
        albumDescription.text = args.albumDescription
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}