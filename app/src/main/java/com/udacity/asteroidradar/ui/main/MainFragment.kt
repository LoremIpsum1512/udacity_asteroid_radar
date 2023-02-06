package com.udacity.asteroidradar.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.MainActivity
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.vo.Asteroid
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: AsteroidListAdapter
    lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
        viewModel.getPictureOfDay()
        viewModel.refreshAsteroidList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        adapter = AsteroidListAdapter(AsteroidClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionShowDetail(it)
            )
        })
        binding.asteroidRecycler.adapter = adapter

        viewModel.asteroidList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var message = ""
        when (item.itemId) {
            R.id.item_all -> {
                message = "Show all saved asteroids"
                viewModel.getAll()
            }
            R.id.item_today -> {
                message = "Show today asteroids"
                viewModel.getAsteroidFromDateRange(DateRange.today)
            }
            R.id.item_week -> {
                message = "Show week asteroids"
                viewModel.getAsteroidFromDateRange(DateRange.week)
            }
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        return true
    }
}

class AsteroidClickListener(val callback: (Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = callback(asteroid)
}