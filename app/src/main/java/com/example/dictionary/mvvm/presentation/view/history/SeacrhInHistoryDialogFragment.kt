package com.example.dictionary.mvvm.presentation.view.history

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.dictionary.databinding.SearchInHistoryDialogFragmentBinding

class SeacrhInHistoryDialogFragment : DialogFragment() {
    private var _binding: SearchInHistoryDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSearchInHistoryClickListener: OnSearchInHistoryClickListener? = null
    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int, before: Int, count:
            Int
        ) {
            if (binding.searchInHistoryDialogInputText.text != null &&
                binding.searchInHistoryDialogInputText.text.toString().isNotEmpty()
            ) {
                binding.searchInHistoryChip.isEnabled = true
                binding.clearInHistoryTextImageview.visibility = View.VISIBLE
            } else {
                binding.searchInHistoryChip.isEnabled = false
                binding.clearInHistoryTextImageview.visibility = View.GONE
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
            onSearchInHistoryClickListener?.onClick(binding.searchInHistoryDialogInputText.text.toString())
            dismiss()
        }

    internal fun setOnSearchInHistoryClickListener(listener: OnSearchInHistoryClickListener) {
        onSearchInHistoryClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchInHistoryDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentDialog = dialog
        if (currentDialog != null) {
            currentDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchInHistoryChip.setOnClickListener(onSearchButtonClickListener)
        binding.searchInHistoryDialogInputText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchInHistoryClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        binding.clearInHistoryTextImageview.setOnClickListener {
            binding.searchInHistoryDialogInputText.setText("")
            binding.searchInHistoryChip.isEnabled = false
        }
    }

    interface OnSearchInHistoryClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SeacrhInHistoryDialogFragment {
            return SeacrhInHistoryDialogFragment()
        }
    }
}