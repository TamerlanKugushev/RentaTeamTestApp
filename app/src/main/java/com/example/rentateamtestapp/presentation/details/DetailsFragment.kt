package com.example.rentateamtestapp.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.rentateamtestapp.R
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(
            id: String,
            email: String,
            first_name: String,
            last_name: String,
            avatar: String
        ): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(
                    "id" to id,
                    "email" to email,
                    "first_name" to first_name,
                    "last_name" to last_name,
                    "avatar" to avatar
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName.text = requireArguments().getString("first_name")
        textViewSurname.text = requireArguments().getString("last_name")
        textViewEmail.text = requireArguments().getString("email")
        Glide.with(requireContext())
            .load(requireArguments().getString("avatar"))
            .into(imageViewAvatar)
    }
}