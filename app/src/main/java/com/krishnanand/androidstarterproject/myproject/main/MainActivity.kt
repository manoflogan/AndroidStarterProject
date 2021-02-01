package com.krishnanand.androidstarterproject.myproject.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krishnanand.androidstarterproject.R
import com.krishnanand.androidstarterproject.databinding.MainItemBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val emptyViewContainer: View by lazy {
        findViewById(R.id.empty_view_container)
    }

    private val emptyRetryButton: Button by lazy {
        emptyViewContainer.findViewById(R.id.empty_retry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainActivityAdapter = MainActivityAdapter(viewModel)
        viewModel.stringLiveData.observe(this, {
          if (it == null) {
              recyclerView.visibility = View.INVISIBLE
              emptyViewContainer.visibility = View.VISIBLE
          } else {
              emptyViewContainer.visibility = View.INVISIBLE
              recyclerView.visibility = View.VISIBLE
              mainActivityAdapter.setStringList(it)
              mainActivityAdapter.notifyDataSetChanged()
          }
        })
        with(recyclerView) {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = linearLayoutManager
            adapter = mainActivityAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@MainActivity, linearLayoutManager.orientation))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.makeAsyncRequest()
        emptyRetryButton.setOnClickListener {
            viewModel.makeAsyncRequest()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}

class MainActivityViewHolder(private val mainItemBinding: MainItemBinding, private val viewModel: MainViewModel): RecyclerView.ViewHolder(mainItemBinding.root) {

    fun bindViewHolder(str: String) {
        mainItemBinding.boundString = str
        mainItemBinding.viewModel = viewModel
        mainItemBinding.executePendingBindings()
    }
}

class MainActivityAdapter(private val viewModel: MainViewModel): RecyclerView.Adapter<MainActivityViewHolder>() {

    private val mutableStringList = mutableListOf<String>()

    public fun setStringList(stringList: List<String>) {
       with(mutableStringList) {
           clear()
           addAll(stringList)
       }
    }

    override fun getItemCount(): Int = mutableStringList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mainItemBinding: MainItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.main_item, parent, false)
        return MainActivityViewHolder(mainItemBinding, viewModel)
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
       holder.bindViewHolder(mutableStringList[position])
    }
}