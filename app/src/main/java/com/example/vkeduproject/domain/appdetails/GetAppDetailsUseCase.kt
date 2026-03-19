package com.example.vkeduproject.domain.appdetails

class GetAppDetailsUseCase(
    private val repository: AppDetailRepository
) {
    suspend operator fun invoke(id: String): AppDetails {
        val appDetails = repository.get(id)
        if (appDetails.category == Category.GAME){
            throw IllegalStateException("Никаких игр!!!!")
        }
        return appDetails
    }
}