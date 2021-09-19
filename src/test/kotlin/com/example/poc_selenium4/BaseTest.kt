package com.example.poc_selenium4

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.*
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import org.openqa.selenium.devtools.v91.fetch.Fetch
import org.openqa.selenium.devtools.v91.network.Network
import org.openqa.selenium.devtools.v91.*

abstract class BaseTest{
    val LOG = Logger.getLogger(this.javaClass.name)

    lateinit var driver: ChromeDriver
    @BeforeEach
    fun setUp() {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver()
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.get("https://www.youtube.com/feed/trending")
        LandingPage(driver)

    }
    
    @AfterEach
    fun tearDown() {
        driver.close()
    }


}
