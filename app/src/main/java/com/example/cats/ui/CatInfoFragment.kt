package com.example.cats.ui

import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.navArgs
import com.example.cats.databinding.FragmentCatInfoBinding
import com.example.cats.placePictureInView
import com.example.cats.setBackGroundAnimation
import kotlin.random.Random


class CatInfoFragment : Fragment(){

    private var _binding: FragmentCatInfoBinding? = null
    private val mBinding get() = _binding!!
    private val args by navArgs<CatInfoFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatInfoBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        mBinding.textView.setText(args.url)
        placePictureInView(mBinding.ivCatInfo,args.url)
        setBackGroundAnimation(mBinding.catInfoLayout.background as AnimationDrawable)

        mBinding.saveImageBtn.setOnClickListener {
        saveImage(mBinding.ivCatInfo.drawToBitmap())
        }

        return mBinding.root
    }


    private fun saveImage(bitmap: Bitmap){

        val imageFileName = "CAT_" + Random.nextInt()
        val savedImageURL: String = MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            bitmap,
            imageFileName,
            "CaT Image"
        )
        Handler().postDelayed({
            Toast.makeText(requireContext(),"Image Downloaded",Toast.LENGTH_LONG).show()
        },1000*5)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}