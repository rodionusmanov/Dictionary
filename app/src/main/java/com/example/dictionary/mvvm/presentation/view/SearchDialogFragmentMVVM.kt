package com.example.dictionary.mvvm.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.dictionary.databinding.SearchDialogBinding

class SearchDialogFragmentMVVM : DialogFragment() {
    private var _binding: SearchDialogBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null
    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int, before: Int, count:
            Int
        ) {
            if (binding.searchDialogInputText.text != null &&
                binding.searchDialogInputText.text.toString().isNotEmpty()
            ) {
                binding.searchChip.isEnabled = true
                binding.clearTextImageview.visibility = View.VISIBLE
            } else {
                binding.searchChip.isEnabled = false
                binding.clearTextImageview.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after:
            Int
        ) {
        }

        override fun afterTextChanged(s: Editable) {}
    }
    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchDialogInputText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentDialog = dialog
        if (currentDialog != null) {
            currentDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchChip.setOnClickListener(onSearchButtonClickListener)
        binding.searchDialogInputText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchDialogInputText.setText("")
            binding.searchChip.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchDialogFragmentMVVM {
            return SearchDialogFragmentMVVM()
        }
    }
}