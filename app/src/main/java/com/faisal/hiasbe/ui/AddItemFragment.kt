package com.faisal.hiasbe.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.faisal.hiasbe.R
import com.faisal.hiasbe.data.model.Item

import com.faisal.hiasbe.databinding.FragmentAddItemBinding
import com.faisal.hiasbe.view_model.AddItemViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    companion object{
        val TAG= HomeFragment::class.java.name
        lateinit var viewModel: AddItemViewModel
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAddItemBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
//        setAdapter()
        return binding.root
    }

    override fun onResume() {
        super.onResume()


        HomeFragment.viewModel.loadData()


//        setObserver()
        setListener()

    }
    private fun setListener(){
        binding.ivCancel.setOnClickListener {
            cancel()
        }
        binding.tvSave.setOnClickListener {
            save()
        }
    }

    /**
     * Ferme la vue
     * @param view view
     */
    fun cancel() {
        findNavController().navigate(R.id.action_addItemFragment_to_homeFragment)
    }



   private fun save() {

        val mTitle:String = binding.tvTitle.text.toString()

        if (mTitle.isEmpty())
            Toast.makeText(context,
            "Error title and description cannot be empty !",
            Toast.LENGTH_SHORT
        ).show() else {

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            Log.e("TAG"," C DATE is  "+currentDate)

            if (false) {
                viewModel.addToDoItem(Item( mTitle,  currentDate,  false))
                cancel()
                Toast.makeText(
                    context,
                    "Added to the todo list",
                    Toast.LENGTH_SHORT
                ).show()

            } else Toast.makeText(
                context,
                "Error you can't enter a date that is already passed !",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}