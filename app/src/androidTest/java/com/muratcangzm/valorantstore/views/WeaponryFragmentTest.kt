package com.muratcangzm.valorantstore.views

import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@MediumTest
class WeaponryFragmentTest {


     @get:Rule
     var hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp(){

        hiltRule.inject()

    }




}