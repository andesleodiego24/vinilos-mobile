package com.example.drawerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.drawerapp.R

class CollectorDetailsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_collector_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
        }

        val args: CollectorDetailsFragmentArgs by navArgs()
        val collectorName: TextView = activity.findViewById(R.id.collector_name)
        val collectorEmail: TextView = activity.findViewById(R.id.collector_email)
        val collectorPhone: TextView = activity.findViewById(R.id.collector_phone)

        collectorName.text = args.collectorName
        collectorEmail.text = args.collectorEmail
        collectorPhone.text = args.collectorPhone
    }

}