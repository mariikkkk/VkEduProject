package com.example.vkeduproject.domain.appdetails

import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val repository: AppDetailRepository
) {
    suspend operator fun invoke(id: String) {
        repository.toggleWishlist(id)
    }
}