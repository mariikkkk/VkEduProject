package com.example.vkeduproject

import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.Category
import com.example.vkeduproject.domain.appdetails.GetAppDetailsUseCase
import com.example.vkeduproject.domain.appdetails.ObserveAppDetailsUseCase
import com.example.vkeduproject.domain.appdetails.ToggleWishlistUseCase
import com.example.vkeduproject.domain.applist.AppListRepository
import com.example.vkeduproject.domain.applist.GetAppListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test

class UseCaseTest {

    val appDetailsRep: AppDetailRepository = mockk(relaxUnitFun = true)
    val appListRep: AppListRepository = mockk(relaxUnitFun = true)
    private val dummyApp = AppDetails("1", "App", "Dev", Category.APP, 3, 10f, "", emptyList(), "", false)
    private val gameApp = AppDetails("2", "Game", "Dev", Category.GAME, 12, 100f, "", emptyList(), "", false)
    private val getAppListUseCase = GetAppListUseCase(appListRep)
    private val getAppDetailsUseCase = GetAppDetailsUseCase(appDetailsRep)
    private val toggleWishlistUseCase = ToggleWishlistUseCase(appDetailsRep)
    private val observeAppDetailsUseCase = ObserveAppDetailsUseCase(appDetailsRep)

    @Test
    fun `get app details without game category EXPECT return app`() = runTest {
        coEvery { appDetailsRep.get("1") } returns dummyApp
        val result = getAppDetailsUseCase("1")
        assertEquals(dummyApp, result)
    }

    @Test
    fun `get app details with game category EXPECT throw exception`() = runTest {
        coEvery { appDetailsRep.get("2") } returns gameApp
        assertThrows(IllegalStateException::class.java) {
            runTest { getAppDetailsUseCase("2") }
        }
    }

    @Test
    fun `toggle wishlist EXPECT call repository toggle`() = runTest {
        toggleWishlistUseCase("1")
        coVerify(exactly = 1) { appDetailsRep.toggleWishlist("1") }
    }

    @Test
    fun `observe app details EXPECT return flow`() {
        val fakeFlow = flowOf(dummyApp)
        every { appDetailsRep.observeAppDetails("1") } returns fakeFlow

        val result = observeAppDetailsUseCase("1")

        assertEquals(fakeFlow, result)
    }



}