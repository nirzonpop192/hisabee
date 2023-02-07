package com.faisal.hiasbe.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faisal.hiasbe.data.model.Item
import com.faisal.hiasbe.databinding.RowItemRepoBinding


class TodoPagingAdapter : PagingDataAdapter<Item, TodoPagingAdapter.RepoViewHolder>(COMPARATOR) {


    var listener:OnItemClickListener?=null
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item:Item?= getItem(position)
        item.let {
            holder.tvName.text= item?.title

            holder.isDone.isChecked= item?.status == true
            holder.itemView.setOnClickListener{
                listener?.onItemClick(item)

                holder.isDone.isChecked= item?.status == true
            }
        }

    }
    public fun getSize(): Int {
        return getItemCount()
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener=listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = RowItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    class RepoViewHolder(val  binding:RowItemRepoBinding): RecyclerView.ViewHolder(binding.root){
        val tvName=binding.tvTitle
        val isDone=binding.cbDone

    }

    companion object{
        private val COMPARATOR= object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return  oldItem.dueDate==newItem.dueDate
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return  oldItem == newItem
            }
        }
    }
}