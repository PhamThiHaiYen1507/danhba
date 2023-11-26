package com.example.contact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.model.ContactInfoModel


class PhoneBookRecycleViewAdapter(
    val mContext: Context,
    val items: ArrayList<ContactInfoModel>,
    val onClickListener: (ContactInfoModel) -> Unit,
    val onLongClickListener: (ContactInfoModel, View) -> Unit,
) : RecyclerView.Adapter<PhoneBookRecycleViewAdapter.ViewHolder?>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextName: TextView
        val mShortName: TextView


        init {
            mTextName = itemView.findViewById(R.id.name)
            mShortName = itemView.findViewById(R.id.shortText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val heroView: View = inflater.inflate(R.layout.phone_contact_item, parent, false)
        return ViewHolder(heroView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: ContactInfoModel = items.get(position)

        holder.mTextName.text = contact.name

        holder.mShortName.text = contact.name[0].toString()

        holder.itemView.setOnClickListener {
            onClickListener(items[position])
        }

        holder.itemView.setOnLongClickListener(View.OnLongClickListener() {
            onLongClickListener(items[position], holder.itemView)
            true
        })
    }
}