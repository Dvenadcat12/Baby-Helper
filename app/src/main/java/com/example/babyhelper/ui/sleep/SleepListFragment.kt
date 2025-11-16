package com.example.babyhelper.ui.sleep

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babyhelper.data.db.AppDatabase
import com.example.babyhelper.data.repository.SleepRepository
import com.example.babyhelper.databinding.FragmentSleepListBinding
import com.example.babyhelper.viewmodels.SleepViewModel
import com.example.babyhelper.viewmodels.SleepViewModelFactory

class SleepListFragment : Fragment() {
    private var _binding: FragmentSleepListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SleepAdapter
    private val viewModel: SleepViewModel by viewModels {
        val dao = AppDatabase.getInstance(requireContext()).sleepDao()
        SleepViewModelFactory(SleepRepository(dao))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        _binding = FragmentSleepListBinding.inflate(inflater, container, false)
        adapter = SleepAdapter { entry ->
            // onClick: открыть диалог редактирования/удаления
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.sleepList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        // Пример: кнопка добавить — добавляем фиктивную запись (потом заменим на UI)
        binding.fabAdd.setOnClickListener {
            val now = System.currentTimeMillis()
            viewModel.addSleep(now - 1000*60*60*9, now) // 9 часов сна
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
