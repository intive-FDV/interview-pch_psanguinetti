package com.challenge.myfirstmillion.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.challenge.myfirstmillion.domain.business.MillionCalculator
import com.challenge.myfirstmillion.domain.repositories.PeopleRepository
import com.challenge.myfirstmillion.ui.format.DateFormat
import com.challenge.myfirstmillion.ui.format.NameFormat
import com.challenge.myfirstmillion.ui.format.WageFormat
import com.challenge.myfirstmillion.ui.model.Resource
import com.challenge.myfirstmillion.ui.model.UserUI
import com.challenge.myfirstmillion.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val nameFormat: NameFormat,
    private val dateFormat: DateFormat,
    private val wageFormat: WageFormat,
    private val millionCalculator: MillionCalculator
) :
    ViewModel() {

    fun save(userId: String, hourlyWage: Int) =
        liveData<Resource<Void>>(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                peopleRepository.update(userId, hourlyWage)
                emit(Resource.Ok())
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }

    val flowPeople: Flow<PagingData<UserUI>> =
        peopleRepository.getPostSteam().cachedIn(viewModelScope).map { pagingData ->
            pagingData.map {
                //The time to be millionaire is not being saved in DB to avoid migrate DB if the "Million calculation" change.
                it.toUI(
                    nameFormat,
                    wageFormat,
                    dateFormat,
                    millionCalculator.calculateMonths(it.hourlyWage)
                )
            }
        }
}