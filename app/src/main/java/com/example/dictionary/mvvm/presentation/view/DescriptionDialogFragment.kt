package com.example.dictionary.mvvm.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import coil.load
import coil.size.OriginalSize
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.dictionary.databinding.DescriptionImageDialogFragmentBinding

class DescriptionDialogFragment(
    private val header: String,
    private val description: String,
    private val imageLink: String
) : DialogFragment() {

    private var _viewBinding: DescriptionImageDialogFragmentBinding? = null
    private val viewBinding: DescriptionImageDialogFragmentBinding
        get() {
            return _viewBinding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DescriptionImageDialogFragmentBinding.inflate(inflater, container, false).also {
            _viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewBinding) {
            tvHeader.text = header
            tvDescription.text = description
            useCoilToLoadPhoto(iv, imageLink)
        }
    }

    private fun useCoilToLoadPhoto(iv: ImageView, imageLink: String) {
        iv.load("https:$imageLink") {
            size(OriginalSize)
        }
    }
}