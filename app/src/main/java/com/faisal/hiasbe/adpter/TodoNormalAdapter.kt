package com.faisal.hiasbe.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.faisal.hiasbe.R
import com.faisal.hiasbe.data.model.Item
import com.faisal.hiasbe.databinding.RowItemRepoBinding


class TodoNormalAdapter (private var list : List<Item>
): RecyclerView.Adapter<TodoNormalAdapter.ViewHolder>() {

    var listener:OnItemClickListener?=null
    class ViewHolder(binding: RowItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        val title : TextView = binding.tvTitle
        val isDone : CheckBox = binding.cbDone
//        val deliveryDate : TextView = binding.tvDate
//        val totalItem : TextView = binding.tvTotalItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  binding: RowItemRepoBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_repo,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text=list.get(position).title

        holder.isDone.isChecked=list.get(position).status

        holder.itemView.setOnClickListener{
            listener?.onItemClick(list.get(position))
            holder.isDone.isChecked=list.get(position).status
        }
        holder.isDone.setOnCheckedChangeListener{
                buttonView, isChecked ->
            listener?.onItemClick(list.get(position))
        }
//        holder.price.text="ট ${list.get(position).totalValue.toString()}"
//        holder.deliveryDate.text=DateFormatManager.formatDateTime(list.get(position).cd)
//        holder.totalItem.text= "Total item:${list.get(position).totalItem.toString()}"


    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener=listener
    }

    fun updateData(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }


}