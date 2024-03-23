package com.altafrazzaque.splitify.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.altafrazzaque.splitify.R
import com.altafrazzaque.splitify.databinding.FragmentHomeBinding
import com.altafrazzaque.splitify.views.ExpenseViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: ExpenseViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    lateinit var adapter: SpentListAdapter

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = SpentListAdapter()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshUserDetails()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshUserDetails()
        viewModel.getTotalThisMonth().observe(viewLifecycleOwner) { totalAmount ->
            val amount = totalAmount?: 0.0
            binding.tvAmount.text = "₹${amount}"
        }
        viewModel.getExpenses().observe(viewLifecycleOwner) {
            Timber.tag(TAG).i("Data %s",it?.size)
            adapter.submitList(it)
        }

        binding.btnAdd.setOnClickListener {
            AddExpenseDialogFragment
                .newInstance()
                .show(requireActivity().supportFragmentManager,AddExpenseDialogFragment.TAG )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}