package com.muratcangzm.valorantstore.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.muratcangzm.valorantstore.getOrAwaitValueTest
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.EventsModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.repository.DataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DataViewModelTest {

    // Set LiveData to execute tasks instantly in tests
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    // Mock the repository
    @Mock
    lateinit var repository: DataRepository

    // Class under test
    private lateinit var viewModel: DataViewModel

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        viewModel = DataViewModel(repository)

    }

    @Test
    fun `getEvents returns correct value`() {

        val mockEventsModel = mock(EventsModel::class.java)

        `when`(repository.getEvents().getOrAwaitValueTest()).thenReturn(mockEventsModel)

        val result = viewModel.getEvents.getOrAwaitValueTest()

        assertThat(result).isEqualTo(mockEventsModel)

    }

    @Test
    fun `getWeaponry returns correct value`(){

        val mockWeaponryModel = mock(WeaponryModel::class.java)

        `when`(repository.getWeaponry().getOrAwaitValueTest()).thenReturn(mockWeaponryModel)


        val result = viewModel.getWeaponry.getOrAwaitValueTest()

        assertThat(result).isEqualTo(mockWeaponryModel)


    }

    @Test
    fun `getCurrency returns correct value`(){


        val mockCurrencyModel = mock(CurrencyModel::class.java)


      `when`(repository.getCurrency().getOrAwaitValueTest()).thenReturn(mockCurrencyModel)

      val result = viewModel.getCurrency.getOrAwaitValueTest()

        assertThat(result).isEqualTo(mockCurrencyModel)

    }


    @Test
    fun`getAgent returns correct value`(){

        val mockAgentModel = mock(AgentModel::class.java)

        `when`(repository.getAgent().getOrAwaitValueTest()).thenReturn(mockAgentModel)

        val result = viewModel.getAgent.getOrAwaitValueTest()

        assertThat(result).isEqualTo(mockAgentModel)

    }

}