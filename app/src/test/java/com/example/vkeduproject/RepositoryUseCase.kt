package com.example.vkeduproject

import com.example.vkeduproject.data.appdetails.AppDetailsApi
import com.example.vkeduproject.data.appdetails.AppDetailsDao
import com.example.vkeduproject.data.appdetails.AppDetailsDto
import com.example.vkeduproject.data.appdetails.AppDetailsEntity
import com.example.vkeduproject.data.appdetails.AppDetailsEntityMapper
import com.example.vkeduproject.data.appdetails.AppDetailsMapper
import com.example.vkeduproject.data.appdetails.AppDetailsRepositoryImpl
import com.example.vkeduproject.data.appdetails.CategoryMapper
import com.example.vkeduproject.domain.appdetails.Category
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RepositoryUseCase {
    private val api: AppDetailsApi = mockk(relaxUnitFun = true)
    private val dao: AppDetailsDao = mockk(relaxUnitFun = true)
    private val categoryMapper = CategoryMapper()
    private val mapper = AppDetailsMapper(categoryMapper)
    private val entityMapper = AppDetailsEntityMapper()
    private val repository = AppDetailsRepositoryImpl(mapper, api, dao, entityMapper)
    private val dummyEntity = AppDetailsEntity("1", "App", "Dev", Category.APP, 3, 10f, "", "", false)
    private val dummyDto = AppDetailsDto("1", "App", "Dev", "App", 3, 10f, "", emptyList(), "")
    @Test
    fun `get app details with data in DB EXPECT return data from DB`() = runTest {
        every { dao.getAppDetails("1") } returns flowOf(dummyEntity)

        val result = repository.get("1")

        assertEquals("1", result.id)
        assertEquals("App", result.name)
        coVerify(exactly = 0) { api.getAppDetails(any()) }
    }

    @Test
    fun `get app details with empty DB EXPECT fetch from API and save to DB`() = runTest {
        every { dao.getAppDetails("1") } returns flowOf(null)
        coEvery { api.getAppDetails("1") } returns dummyDto

        val result = repository.get("1")

        assertEquals("1", result.id)
        coVerify(exactly = 1) { dao.insertAppDetails(any()) }
    }

    @Test
    fun `toggle wishlist with false status EXPECT update status to true`() = runTest {
        every { dao.getAppDetails("1") } returns flowOf(dummyEntity.copy(isInWishList = false))

        repository.toggleWishlist("1")

        coVerify(exactly = 1) { dao.updateWishlistStatus("1", true) }
    }

    @Test
    fun `toggle wishlist with true status EXPECT update status to false`() = runTest {
        every { dao.getAppDetails("1") } returns flowOf(dummyEntity.copy(isInWishList = true))

        repository.toggleWishlist("1")

        coVerify(exactly = 1) { dao.updateWishlistStatus("1", false) }
    }

    @Test
    fun `observe app details EXPECT return mapped flow`() = runTest {
        every { dao.getAppDetails("1") } returns flowOf(dummyEntity)

        val flowResult = repository.observeAppDetails("1")
        val appDetails = flowResult.first()

        assertEquals("1", appDetails.id)
        assertEquals(Category.APP, appDetails.category)
    }
}
