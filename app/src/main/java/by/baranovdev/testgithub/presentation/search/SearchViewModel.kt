package by.baranovdev.testgithub.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.baranovdev.testgithub.domain.entity.SearchResultItem
import by.baranovdev.testgithub.domain.entity.params.GetRepositoriesParams
import by.baranovdev.testgithub.domain.entity.params.GetUserParams
import by.baranovdev.testgithub.domain.usecase.search.SearchRepositoryUseCase
import by.baranovdev.testgithub.domain.usecase.search.SearchUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val searchRepositoryUseCase: SearchRepositoryUseCase
) : ViewModel() {

    private val _searchResult: MutableLiveData<List<SearchResultItem>> = MutableLiveData()
    val searchResult: LiveData<List<SearchResultItem>> = _searchResult

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _currentQuery: MutableLiveData<String> = MutableLiveData()
    val currentQuery: LiveData<String> = _currentQuery

    private var collectorJob: Job? = null

    fun setQuery(query: String) {
        _currentQuery.value = query
    }

    fun search(query: String) {
        collectorJob?.cancel()
        _isLoading.value = true
        collectorJob = viewModelScope.launch(Dispatchers.IO) {
            val tempResult = ArrayList<SearchResultItem>()
            merge(
                searchUsersUseCase.execute(GetUserParams(query)),
                searchRepositoryUseCase.execute(GetRepositoriesParams(query))
            ).collect { result ->
                result.onSuccess { r ->
                    tempResult.addAll(r.data)
                    _searchResult.postValue(tempResult.sortedBy { it.sortKey.lowercase() })
                }.onFailure { e ->
                    _error.postValue(e.message)
                    cancel()
                }

                _isLoading.postValue(false)
            }
        }

    }

}
