package com.example.poc_selenium4

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import java.util.logging.Logger

abstract class BasePage(driver: WebDriver) {

    val LOG = Logger.getLogger(this.javaClass.name)
    val driver : WebDriver = driver

    init {
        PageFactory.initElements(driver, this)

    }


}
