package co.soyyo.consumo.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import co.soyyo.consumo.R
import co.soyyo.consumo.databinding.FragmentFindSpecificDateBinding
import co.soyyo.consumo.presentation.CommunicationBetweenFragmentsSearchAndHome

class FindSpecificDateFragment : Fragment(R.layout.fragment_find_specific_date) {

    private val communicationBetweenFragmentsSearch: CommunicationBetweenFragmentsSearchAndHome by activityViewModels()

    lateinit var fragmentFindSpecificDateBinding : FragmentFindSpecificDateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentFindSpecificDateBinding = FragmentFindSpecificDateBinding.bind(view)

        fragmentFindSpecificDateBinding.materialButtonSearch.setOnClickListener {
            val text: String = fragmentFindSpecificDateBinding.editTextDate.text.toString()
            communicationBetweenFragmentsSearch.setDate(text)
            findNavController().navigate(R.id.action_findSpecificDateFragment_to_homeFragment)
            Log.d("TESTButton", "${fragmentFindSpecificDateBinding.editTextDate.text}")
        }

    }

}