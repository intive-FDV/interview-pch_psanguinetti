package com.challenge.myfirstmillion.ui.people

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.challenge.myfirstmillion.databinding.ActivityPeopleBinding
import com.challenge.myfirstmillion.ui.model.Resource
import com.challenge.myfirstmillion.ui.model.UserUI
import com.challenge.myfirstmillion.ui.utils.RetryLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleActivity : AppCompatActivity(), PeopleAdapter.RowListener {
    private lateinit var binding: ActivityPeopleBinding
    private val peopleAdapter: PeopleAdapter by lazy {
        PeopleAdapter(
            this,
            PeopleAdapter.PeopleDiffCallBack,
            this
        )
    }

    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()
    }

    private fun setupUI() {

        binding.refresh.setOnRefreshListener {
            peopleAdapter.refresh()
        }

        binding.rvPeople.adapter = peopleAdapter.withLoadStateHeaderAndFooter(
            header = RetryLoadStateAdapter(peopleAdapter::retry),
            footer = RetryLoadStateAdapter(peopleAdapter::retry)
        )

        lifecycleScope.launch {
            peopleAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.refresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            viewModel.flowPeople.collectLatest { pagingData ->
                peopleAdapter.submitData(pagingData)
            }
        }

    }

    override fun save(userId: String, hourlyWage: Int) {
        viewModel.save(userId, hourlyWage)
            .observe(this, {
                when (it) {
                    is Resource.Ok -> {
                        peopleAdapter.refresh()
                    }
                    is Resource.Loading -> {
                        //TODO show progress while saving data
                    }
                    is Resource.Validation -> {
                        //TODO show validation errors...
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            this,
                            it.exception.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }

    override fun edit(userUI: UserUI) {
        //Nothing by now
    }

    override fun cancel() {
        //Nothing by now
    }
}