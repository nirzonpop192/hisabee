package com.faisal.hiasbe.ui

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.faisal.hiasbe.R
import com.faisal.hiasbe.adpter.OnItemClickListener
import com.faisal.hiasbe.adpter.TodoPagingAdapter
import com.faisal.hiasbe.data.model.Item
import com.faisal.hiasbe.databinding.FragmentHomeBinding
import com.faisal.hiasbe.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



@AndroidEntryPoint
class HomeFragment : Fragment() {



    lateinit var mAdapter: TodoPagingAdapter


    private lateinit var binding: FragmentHomeBinding
    companion object{
        val TAG= HomeFragment::class.java.name
        lateinit var viewModel: HomeViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setAdapter()
        return binding.root
    }

    override fun onResume() {
        super.onResume()


        viewModel.loadData()


        setObserver()
        setListener()

    }
    fun setObserver(){


        viewModel.pagingDataList.observe(viewLifecycleOwner){

            viewModel.isLoading.value=false;
            Log.e(TAG, " observing ")


           mAdapter.submitData(lifecycle, it)
            Log.e(TAG, "onResume:  size of adapter ${mAdapter.snapshot().items.size}", )

        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it)
                binding.progressBar.visibility=View.VISIBLE
            else
                binding.progressBar.visibility=View.INVISIBLE
        }
    }

    fun setAdapter (){
        mAdapter=TodoPagingAdapter()

        binding.rvRepositoryList.apply {
            this.layoutManager= LinearLayoutManager(requireContext())
            this.setHasFixedSize(true)
            this.adapter=mAdapter
        }
    }

    fun setListener(){

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addItemFragment)
        }
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Item?) {
//                var bundle = bundleOf("repository" to item)
//                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
            }
        })

    }



    override fun onPause() {
        super.onPause()

    }


}

class UiThreadExecutor: Executor {
    private val handler = Handler (Looper.getMainLooper ())
    override fun execute (command: Runnable) {
        handler.post (command)
    }
}

class ListDataSource (private val items: List<Item>): PageKeyedDataSource<Int, Item>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        callback.onResult(items, 0, items.size)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }
}