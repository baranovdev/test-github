package by.baranovdev.testgithub.presentation.browser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.baranovdev.testgithub.BuildConfig
import by.baranovdev.testgithub.domain.entity.PathEntity
import by.baranovdev.testgithub.domain.entity.params.GetPathParams
import by.baranovdev.testgithub.domain.usecase.browser.GetPathUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class BrowserViewModel @Inject constructor(
    private val getPathUseCase: GetPathUseCase
) : ViewModel() {

    private var collectorJob: Job? = null

    private val _pathStack = MutableLiveData<List<String>>(emptyList())
    val pathStack: LiveData<List<String>> = _pathStack

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _currentPath = MutableLiveData<List<PathEntity>>()
    val currentPath: LiveData<List<PathEntity>> = _currentPath

    fun getPath(url: String, addToStack: Boolean = true) {
        val query = url.replace(BuildConfig.BASE_URL, "").replace("{+path}", "")
        collectorJob?.cancel()
        _isLoading.value = true
        collectorJob = viewModelScope.launch(Dispatchers.IO) {
            getPathUseCase.execute(GetPathParams(query)).collect {
                it.onSuccess { data ->
                    if (addToStack) {
                        val stack = pathStack.value?.toMutableList()
                        stack?.add(query)
                        _pathStack.postValue(stack.orEmpty())
                    }
                    _currentPath.postValue(data.sortedBy { path -> path.type })
                }.onFailure { e ->
                    _error.postValue(e.localizedMessage)
                }
                Log.e("PATH RESULT:", it.toString())
                _isLoading.postValue(false)
            }
        }


    }

    fun navigateBackLocally() {
        val stack = pathStack.value
        val newStack = stack?.dropLast(1)
        newStack?.lastOrNull()?.let {
            getPath(it, false)
        }
        _pathStack.value = newStack.orEmpty()

    }

}