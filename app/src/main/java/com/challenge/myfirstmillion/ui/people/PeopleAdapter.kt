package com.challenge.myfirstmillion.ui.people

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.myfirstmillion.databinding.ItemEditionPersonBinding
import com.challenge.myfirstmillion.databinding.ItemPersonBinding
import com.challenge.myfirstmillion.ui.model.UserUI


class PeopleAdapter(
    private val context: Context,
    diffCallBack: DiffUtil.ItemCallback<UserUI>,
    private val rowListener: RowListener
) :
    PagingDataAdapter<UserUI, RecyclerView.ViewHolder>(diffCallBack) {

    companion object {
        internal const val EDIT_VIEW_TYPE = 1001
        internal const val READ_VIEW_TYPE = 1002
    }

    object PeopleDiffCallBack : DiffUtil.ItemCallback<UserUI>() {
        override fun areItemsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
            return oldItem.equals(newItem)
        }
    }

    interface RowListener {
        fun save(userId: String, hourlyWage: Int)
        fun edit(userUI: UserUI)
        fun cancel()
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position)!!.viewHolderType == EDIT_VIEW_TYPE -> {
                EDIT_VIEW_TYPE
            }
            else -> {
                READ_VIEW_TYPE
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is ReadPersonViewHolder -> {
                    holder.bind(it)
                }
                is EditPersonViewHolder -> {
                    holder.bind(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            READ_VIEW_TYPE -> {
                ReadPersonViewHolder(
                    ItemPersonBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                EditPersonViewHolder(
                    ItemEditionPersonBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    inner class ReadPersonViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserUI) = with(binding) {
            Glide.with(context).load(item.picture).circleCrop().into(imgAvatar)
            tvName.text = item.formatedName
            tvWage.text = item.wage
            tvTimeRequired.text = item.timeRequired
            btnEdit.setOnClickListener {
                item.viewHolderType = EDIT_VIEW_TYPE
                notifyDataSetChanged()
                rowListener.edit(item)
            }
        }
    }

    inner class EditPersonViewHolder(
        val binding: ItemEditionPersonBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserUI) = with(binding) {
            Glide.with(context).load(item.picture).circleCrop().into(imgAvatar)
            tvName.text = item.formatedName
            tvWage.text = item.wage
            tvTimeRequired.text = item.timeRequired
            etvHourlyWage.setText(item.hourlyWage.toString())

            btnSave.setOnClickListener {
                switchToReadView(item, binding.root)
                rowListener.save(item.id, etvHourlyWage.text.toString().toInt())
            }

            btnCancel.setOnClickListener {
                switchToReadView(item, binding.root)
                rowListener.cancel()
            }

            etvHourlyWage.setOnEditorActionListener({ _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    switchToReadView(item, binding.root)
                    rowListener.save(item.id, etvHourlyWage.text.toString().toInt())
                }
                false
            })
        }

        fun switchToReadView(item: UserUI, view: View) {
            item.viewHolderType = READ_VIEW_TYPE
            hideKeyboardFrom(context, view)
            notifyDataSetChanged()
        }

        fun hideKeyboardFrom(context: Context, view: View) {
            val imm: InputMethodManager =
                context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

}