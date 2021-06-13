package com.toobler.myfriends

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.toobler.myfriends.adapter.FriendsAdapter
import com.toobler.myfriends.databinding.ActivityMainBinding
import com.toobler.myfriends.details.UserDetailsActivity
import com.toobler.myfriends.model.User
import com.toobler.myfriends.model.UserData
import com.toobler.myfriends.utils.PublicConstance

private var _binding: ActivityMainBinding? = null
private val bind get() = _binding

private lateinit var mRvAdapter: FriendsAdapter
private lateinit var mViewModel: MainActivityViewModel

private var mUserList: ArrayList<User> = ArrayList()

private lateinit var searchView: SearchView

class MainActivity : AppCompatActivity(), MainActivityNavigator,  FriendsAdapter.UserSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind?.root)

        initViewModel()

        initAdapter()

        loadUserList()
    }

    /*init view model*/
    private fun initViewModel(){
        val factory: ViewModelProvider.Factory = NewInstanceFactory()
        mViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        mViewModel.setNavigatorCallBack(this)
    }

    //load user data using ViewModel
    private fun loadUserList() {
        mViewModel.getUserData()
    }

    /*initialize the recyclerView Adapter */
    private fun initAdapter(){
        mRvAdapter = FriendsAdapter(this, this)

        bind?.rvUserList?.adapter = mRvAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //open the details page with selected user data
    override fun onUserSelected(user: User) {
        val fullName = user.firstName + " " + user.lastName
        val userData = UserData(fullName, user.email!!, user.avatar!! )

        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(PublicConstance.ARG_USER_DATA, userData)
        startActivity(intent)
    }

    override fun updateUserData(userList: List<User>) {
        mUserList = (userList as ArrayList<User>?)!!

        mRvAdapter.updateUserData(mUserList)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.searchItems)
            .actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.maxWidth = Int.MAX_VALUE
        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                mRvAdapter.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                mRvAdapter.filter?.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.searchItems) {
            true
        } else super.onOptionsItemSelected(item)
    }
}