package com.toobler.myfriends.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.toobler.myfriends.R
import com.toobler.myfriends.databinding.AdapterFriendsBinding
import com.toobler.myfriends.model.User

class FriendsAdapter(userSelectedListener: UserSelectedListener, var context: Context) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>(), Filterable {

    var mUserList = ArrayList<User>()
    var mFilteredUserList = ArrayList<User>()

    var mUserSelectedListener = userSelectedListener

    interface UserSelectedListener {
        fun onUserSelected(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AdapterFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mFilteredUserList[position]) {
                val userName = this.firstName + " " + this.lastName
                binding.tvFirstName.text = userName
//                binding.tvLastName.text = this.lastName

                Glide.with(binding.ivUserProfile).load(this.avatar).error(R.mipmap.ic_launcher)
                    .into(binding.ivUserProfile)

                itemView.setOnClickListener {
                    mUserSelectedListener.onUserSelected(this)
                }
            }
        }
    }

    fun updateUserData(userList: ArrayList<User>) {
        mUserList = userList
        mFilteredUserList = userList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mFilteredUserList.size
    }

    inner class ViewHolder(val binding: AdapterFriendsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                val filteredList = ArrayList<User>()
                if (charString.isEmpty() || charString.length < 3) {
                    filteredList.addAll(mUserList)
                    mFilteredUserList = filteredList
                } else {
                    for (user in mUserList) {
                        val name: String = user.firstName!!
                        val email: String = user.email!!
                        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email)) {
                            if (name.toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(user)
                            } else if (!TextUtils.isEmpty(email)) {
                                if (email.toLowerCase().contains(charString.toLowerCase())) {
                                    filteredList.add(user)
                                }
                            }
                        }
                    }
                    mFilteredUserList = filteredList
                    if (mFilteredUserList.size == 0) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.empty_search_result_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = mFilteredUserList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if (filterResults.values != null) {
                    mFilteredUserList = filterResults.values as ArrayList<User>
                    notifyDataSetChanged()
                }
            }
        }
    }
}