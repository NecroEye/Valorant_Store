package com.muratcangzm.valorantstore.api

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ApiIsolationTests : TestCase() {


    @Test
    fun test_Valorant_Api_Events_Status_Success(){

        val api = TestApiImplementation.provideApi()
        val test = runBlocking {

            api.getEvents()

        }
        assertEquals(test.status, 200)
    }

    @Test
    fun test_Valorant_Api_Agents_Status_Success(){

        val api = TestApiImplementation.provideApi()
        val test = runBlocking {

            api.getAgent()

        }
        assertEquals(test.status, 200)
    }


    @Test
    fun test_Valorant_Api_Weaponry_Status_Success(){

        val api = TestApiImplementation.provideApi()
        val test = runBlocking {

            api.getWeaponry()

        }
        assertEquals(test.status, 200)
    }

    @Test
    fun test_Valorant_Api_Currency_Status_Success(){

        val api = TestApiImplementation.provideApi()
        val test = runBlocking {

            api.getCurrency()

        }
        assertEquals(test.status, 200)
    }

    @Test
    fun test_Valorant_Api_WeaponSkins_Status_Success(){

        val api = TestApiImplementation.provideApi()
        val test = runBlocking {

            api.getSkins()

        }
        assertEquals(test.status, 200)
    }

}