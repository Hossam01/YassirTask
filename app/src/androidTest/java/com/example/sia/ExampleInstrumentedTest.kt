package com.example.sia

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    fun checkOnlineState(): Boolean {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val CManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val NInfo = CManager!!.activeNetworkInfo
        return if (NInfo != null && NInfo.isConnectedOrConnecting) {
            true
        } else false
    }

        @Test
        fun useAppContext() {
            assertEquals(true, checkOnlineState())


    }
}