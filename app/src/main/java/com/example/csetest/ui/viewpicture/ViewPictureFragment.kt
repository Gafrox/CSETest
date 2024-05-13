package com.example.csetest.ui.viewpicture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.csetest.R
import com.example.csetest.databinding.FragmentViewPictureBinding
import com.example.csetest.ui.repository
import java.io.File

private const val PICTURE_ID = "picture id"

class ViewPictureFragment : Fragment() {

    private var pictureId: Int? = null
    private lateinit var binding: FragmentViewPictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pictureId = it.getInt(PICTURE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dir = requireContext().filesDir
        val file = File(dir, repository.getCarById(pictureId!!).picture)
        binding.iv.load(file) {
            placeholder(R.drawable.placeholder)
            error(R.drawable.placeholder)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            ViewPictureFragment().apply {
                arguments = Bundle().apply {
                    putInt(PICTURE_ID, id)
                }
            }
    }
}