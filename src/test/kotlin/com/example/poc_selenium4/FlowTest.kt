package com.example.poc_selenium4

import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.devtools.events.ConsoleEvent
import org.openqa.selenium.devtools.v91.network.Network
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.remote.http.DumpHttpExchangeFilter.LOG
import java.util.*
import java.util.function.Consumer

class FlowTest: BaseTest() {

    @Test
    fun trendingVideosDisplayed() {
        LandingPage(driver).apply {
            clickIAgreeButton()
            val serializedList = videoSerializer()
            serializedList.let {
                mostViewSorter(it)
                sum50Views(it)
                //top5Longuest(it)
            }
            assertTotalTrending(50)

        }
    }
}
