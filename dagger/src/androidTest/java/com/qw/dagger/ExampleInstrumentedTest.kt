package com.qw.dagger

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.qw.dagger.dagger.DaggerApplicationComponent
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.qw.dagger.test", appContext.packageName)
    }

    @Test
    fun component() {
        val dagger1 = DaggerApplicationComponent.create()
        val dagger2 = DaggerApplicationComponent.create()
        assert(dagger1 == dagger2)
    }

    @Test
    fun instance() {
        val daggerApplicationComponent = DaggerApplicationComponent.create()
        val login1VM = daggerApplicationComponent.loginVM()
        val login2VM = daggerApplicationComponent.loginVM()
        assert(login1VM != login2VM)
    }

    @Test
    fun singleInstance() {
        val component = DaggerApplicationComponent.create()
        val user1VM = component.userVM()
        val user2VM = component.userVM()
        assert(user1VM == user2VM)
    }


    @Test
    fun providesObj() {
        val daggerApplicationComponent = DaggerApplicationComponent.create()
        val login1VM = daggerApplicationComponent.loginVM()
        val result = login1VM.login("qinwei", "123456")
        assert(result)
    }
}