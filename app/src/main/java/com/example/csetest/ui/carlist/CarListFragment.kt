package com.example.csetest.ui.carlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.csetest.App
import com.example.csetest.R
import com.example.csetest.databinding.FragmentCarListBinding
import com.example.csetest.navigation.Screens
import com.example.csetest.ui.carlist.adapter.Car
import com.example.csetest.ui.carlist.adapter.CarAdapter
import com.example.csetest.ui.repository
import com.example.csetest.utils.Utils.copyImageToInternalStorage
import kotlinx.coroutines.launch

class CarListFragment : Fragment() {

    private val router = App.INSTANCE.router
    private lateinit var binding: FragmentCarListBinding
    private val viewModel: CarListViewModel by viewModels()
    private var carAdapter: CarAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRepository()
        initAdapter()
        viewModel.updateList()
        viewModel.cars.observe(viewLifecycleOwner) {
            carAdapter?.submitList(it)
        }
        with(binding) {
            btnAdd.setOnClickListener {
                viewModel.onAddClicked()
            }
            btnFilter.setOnClickListener {
                viewModel.onFilterClicked()
            }
            btnSort.setOnClickListener {
                viewModel.onSortClicked()
            }
        }
    }

    private fun initRepository() {
        if (repository.getAll().isEmpty()) {
            lifecycleScope.launch {
                copyImageToInternalStorage(requireContext(), R.drawable.img_skoda, "img0.jpg")
                copyImageToInternalStorage(requireContext(), R.drawable.img_bmw, "img1.jpg")
                copyImageToInternalStorage(requireContext(), R.drawable.img_audi, "img2.jpg")
                copyImageToInternalStorage(requireContext(), R.drawable.img_mb, "img3.jpg")
                repository.saveAll(list)
            }
        }
    }

    private fun initAdapter() {
        carAdapter = CarAdapter(
            context = requireContext(),
            navigateAction = {
                router.navigateTo(Screens.viewPicture(it.id))
            },
            editAction = {
                router.navigateTo(Screens.editCar(it.id))
            }
        )
        with(binding) {
            rv.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            rv.adapter = carAdapter
        }
    }

    companion object {
        val list = listOf(
            Car(
                0,
                "Skoda Superb",
                "2.0 l / 190 hp / Gasoline",
                "Robotic",
                "Liftback",
                "img0.jpg"
            ),

            Car(
                1,
                "BMW 5 Series",
                "2.0 l / 197 hp / Diesel",
                "Automatic",
                "Sedan",
                "img1.jpg"
            ),
            Car(
                2,
                "Audi A4",
                "2.0 l / 204 hp / Gasoline",
                "Robotic",
                "Sedan",
                "img2.jpg"
            ),
            Car(
                3,
                "Mercedes-Benz E-Class",
                "2.0 l / 258 hp / Gasoline",
                "Automatic",
                "Sedan",
                "img3.jpg"
            )
        )
    }
}