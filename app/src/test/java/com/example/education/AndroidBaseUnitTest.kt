package com.example.education

import android.os.Build
import com.example.education.data.TaskEntity
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    application = App::class,
    manifest = Config.NONE,
    //shadows = [ ShadowBuildConfig::class ],
    sdk = [Build.VERSION_CODES.O_MR1]
)
abstract class AndroidBaseUnitTest : AutoCloseKoinTest() {

    @Before
    fun before() {
        beforePreparation()
    }

    @After
    fun after() {
        afterPreparation()
    }

    abstract fun beforePreparation()
    abstract fun afterPreparation()

}