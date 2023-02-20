package com.example.dictionary.mvvm.presentation.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.dictionary.R
import com.example.dictionary.databinding.AlertDialogFragmentBinding
import com.example.dictionary.mvvm.model.data.OnlineLiveData

class AlertDialogFragment() : DialogFragment() {

    private var _viewBinding: AlertDialogFragmentBinding? = null
    private val viewBinding: AlertDialogFragmentBinding
        get() {
            return _viewBinding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return AlertDialogFragmentBinding.inflate(inflater, container, false).also {
            _viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            tvHeader.text = getString(R.string.Network_offline_header)
            tvDescription.text = getString(R.string.Network_offline_description)
        }
        showUntilOnline()
    }

    private fun showUntilOnline() {
        OnlineLiveData(this.requireContext()).observe(
            this@AlertDialogFragment,
            Observer<Boolean> {
                if (it) {
                    dismiss()
                }
            })
    }
}