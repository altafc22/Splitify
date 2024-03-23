package com.altafrazzaque.splitify.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.altafrazzaque.splitify.databinding.DialogAddExpenseBinding
import com.altafrazzaque.splitify.model.ExpenseItem
import com.altafrazzaque.splitify.views.ExpenseViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@AndroidEntryPoint
class AddExpenseDialogFragment : DialogFragment() {

    private val viewModel: ExpenseViewModel by viewModels()

    private var _binding: DialogAddExpenseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddExpenseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddExpense.setOnClickListener {
            val itemName = binding.editTextItem.text.toString()
            val price = binding.editTextPrice.text.toString().toIntOrNull() ?: 0
            val paidBy = binding.editTextPaidBy.text.toString()

            val expenseItem = ExpenseItem(
                id = UUID.randomUUID().toString(),
                item = itemName,
                price = price,
                paidBy = paidBy,
                date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(Date()),
            )
            viewModel.addExpense(expenseItem)
            dismiss()
        }
    }

    companion object {
        const val TAG = "AddExpenseDialog"
        fun newInstance() = AddExpenseDialogFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

}
