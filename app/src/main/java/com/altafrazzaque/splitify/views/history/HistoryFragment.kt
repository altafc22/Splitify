package com.altafrazzaque.splitify.views.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.altafrazzaque.splitify.R
import com.altafrazzaque.splitify.databinding.FragmentExpenseHistoryBinding
import com.altafrazzaque.splitify.views.ExpenseViewModel
import com.altafrazzaque.splitify.views.home.AddExpenseDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private val viewModel: ExpenseViewModel by viewModels()

    private var _binding: FragmentExpenseHistoryBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: ExpenseHistoryListAdapter
    companion object {
        private const val TAG = "HistoryFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_history, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        adapter = ExpenseHistoryListAdapter()
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshUserDetails()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExpenses().observe(viewLifecycleOwner) {
            Timber.tag(TAG).i("Data %s",it?.size)
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}