package com.example.drawerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.drawerapp.R

class ArtistDetailsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_artist_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
        }

        val args: ArtistDetailsFragmentArgs by navArgs()
        val artistName: TextView = activity.findViewById(R.id.artist_name)
        val artistImage: ImageView = activity.findViewById(R.id.artist_image)
        val artistDescription: TextView = activity.findViewById(R.id.artist_description)

        artistName.text = args.artistName
        Glide.with(this)
            .load(args.artistImage.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_broken_image))
            .into(artistImage)
        artistDescription.text = args.artistDescription
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}