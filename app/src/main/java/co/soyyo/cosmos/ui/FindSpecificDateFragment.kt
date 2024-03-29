package co.soyyo.cosmos.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import co.soyyo.cosmos.R
import co.soyyo.cosmos.databinding.FragmentFindSpecificDateBinding
import co.soyyo.cosmos.presentation.CommunicationBetweenFragmentsSearchAndHome

class FindSpecificDateFragment : Fragment(R.layout.fragment_find_specific_date) {

    private val communicationBetweenFragmentsSearch: CommunicationBetweenFragmentsSearchAndHome by activityViewModels()

    private lateinit var fragmentFindSpecificDateBinding : FragmentFindSpecificDateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentFindSpecificDateBinding = FragmentFindSpecificDateBinding.bind(view)

        fragmentFindSpecificDateBinding.materialButtonSearch.setOnClickListener {
            val date: String = fragmentFindSpecificDateBinding.textInputEditTextDate.text.toString()
            if (validateFields(date)) {
                communicationBetweenFragmentsSearch.setDate(date)
                findNavController().navigate(R.id.action_findSpecificDateFragment_to_homeFragment)
            }
            Log.d("TESTButton",
                fragmentFindSpecificDateBinding.textInputEditTextDate.text.toString()
            )
        }

    }

    private fun validateFields(date: String): Boolean {

        var isValid = true

        if (date.isEmpty())
        {
            fragmentFindSpecificDateBinding.textInputLayoutDate.error = getString(R.string.error_text_input_empty_title)
            fragmentFindSpecificDateBinding.textInputLayoutDate.requestFocus()
            isValid = false
        } else {
            fragmentFindSpecificDateBinding.textInputLayoutDate.error = null
        }

        return isValid
    }

}