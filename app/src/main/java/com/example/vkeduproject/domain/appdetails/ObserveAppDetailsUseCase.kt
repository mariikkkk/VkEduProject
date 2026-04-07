package com.example.vkeduproject.domain.appdetails

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppDetailsUseCase @Inject constructor(
    private val repository: AppDetailRepository
) {
    operator fun invoke(id: String): Flow<AppDetails> {
        return repository.observeAppDetails(id)
    }
}