package com.example.dictionary.mvvm.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import coil.load
import coil.size.OriginalSize
import com.example.dictionary.databinding.TranslationImageDialogFragmentBinding

class TranslationDialogFragment(
    private val header: String,
    private val translation: String,
    private val imageLink: String
) : DialogFragment() {

    private var _viewBinding: TranslationImageDialogFragmentBinding? = null
    private val viewBinding: TranslationImageDialogFragmentBinding
        get() {
            return _viewBinding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return TranslationImageDialogFragmentBinding.inflate(inflater, container, false).also {
            _viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewBinding) {
            tvHeader.text = header
            tvTranslation.text = translation
            useCoilToLoadPhoto(iv, imageLink)
        }
    }

    private fun useCoilToLoadPhoto(iv: ImageView, imageLink: String) {
        iv.load("https:$imageLink") {
            size(OriginalSize)
        }
    }
}