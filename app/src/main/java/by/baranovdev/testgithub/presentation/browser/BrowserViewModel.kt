package by.baranovdev.testgithub.presentation.browser

import androidx.lifecycle.ViewModel
import by.baranovdev.testgithub.domain.usecase.SearchUsersUseCase
import javax.inject.Inject

class BrowserViewModel @Inject constructor(
    private val getUsersUseCase: SearchUsersUseCase
): ViewModel() {
}