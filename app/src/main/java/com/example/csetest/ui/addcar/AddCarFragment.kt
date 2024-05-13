package com.example.csetest.ui.addcar

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import coil.load
import com.example.csetest.App
import com.example.csetest.R
import com.example.csetest.databinding.FragmentAddCarBinding
import com.example.csetest.db.CarEntity
import com.example.csetest.navigation.Screens
import com.example.csetest.ui.carlist.adapter.Car
import com.example.csetest.ui.repository
import com.example.csetest.utils.Utils.copyImageUriToInternalStorage
import java.io.File

private const val CAR_ID = "car id"

class AddCarFragment : Fragment() {

    private val router = App.INSTANCE.router
    private lateinit var binding: FragmentAddCarBinding
    private var carId: Int? = null

    private lateinit var imageUri: Uri

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                binding.iv.load(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            carId = it.getInt(CAR_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            if (carId != null) {
                val car = repository.getCarById(carId!!).toDto()
                etName.editText?.setText(car.name)
                etEngine.editText?.setText(car.engine)
                etGearbox.editText?.setText(car.gearbox)
                etBody.editText?.setText(car.body)
                iv.load(File(requireContext().filesDir, car.picture)) {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }
            }
            btnAddPicture.setOnClickListener {
                getContent.launch("image/*")
            }
            btnSave.setOnClickListener {
                carId = if (carId == null) repository.getAll().size else carId
                copyImageUriToInternalStorage(requireContext(), imageUri, "img${carId}.jpg")
                val car = Car(
                    id = carId!!,
                    name = etName.editText?.text.toString(),
                    engine = etEngine.editText?.text.toString(),
                    gearbox = etGearbox.editText?.text.toString(),
                    body = etBody.editText?.text.toString(),
                    picture = "img${carId}.jpg"
                )
                repository.saveCar(CarEntity.fromDto(car))
                router.backTo(Screens.cars())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            AddCarFragment().apply {
                arguments = Bundle().apply {
                    putInt(CAR_ID, id)
                }
            }
    }
}