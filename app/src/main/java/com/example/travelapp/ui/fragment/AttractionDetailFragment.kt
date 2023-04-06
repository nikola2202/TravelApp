package com.example.travelapp.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment: BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_url).into(binding.headerImageView)
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.descriptionTextView.text = attraction.description
            binding.numberOfFactsTextView.text = "${attraction.facts.size} facts"
            binding.numberOfFactsTextView.setOnClickListener {

                val stringBuilder = StringBuilder("")
                attraction.facts.forEach {
                    stringBuilder.append("\u2022 $it")
                    stringBuilder.append("\n\n")
                }
                val message = stringBuilder.toString().substring(0,stringBuilder.toString().lastIndexOf("\n\n"))

                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog,which ->
                        dialog.dismiss()
                    }
                    .setNegativeButton("NO!") {dialog, which ->
                        dialog.dismiss()
                    }
                    .show()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemLocation -> {
                val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

