package com.example.poc_selenium4

import org.openqa.selenium.support.FindBy
import org.openqa.selenium.WebDriver
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter




class LandingPage(driver: WebDriver): BasePage(driver) {
    
    @FindBy(xpath = "//*[contains(@aria-label,'Agree')]")
    lateinit var iAgreeButton: WebElement

    private val videoList : List<WebElement>
        get() = driver.findElements(By.xpath("//ytd-video-renderer"))


    fun clickIAgreeButton(){
        iAgreeButton.click()
    }
    fun assertTotalTrending(numTotal : Int){
        assertThat(videoList.size).isEqualTo(numTotal)
    }

    fun videoSerializer(): List<VideoModel>{
        var serializedList = listOf<VideoModel>()
        videoList.forEach { 
            val videoTitle = it.findElement(By.xpath(".//h3")).text
            val numViews = it.findElement(By.xpath(".//*[@id='metadata-line']/span[1]")).text
            val timeLength = it.findElement(By.xpath(".//ytd-thumbnail-overlay-time-status-renderer/span")).text
            val href = it.findElement(By.xpath(".//*[@id='video-title']")).getAttribute("href")
            serializedList += VideoModel(timeLength, videoTitle, numViews, href)
        }
        serializedList.forEach{    
            it.numViews = it.numViews
            .replace("K", "000")
            .replace("M","000000")
            .filter {
                it.isDigit()
            }
        }
        return serializedList
    }


    fun mostViewSorter(ls : List<VideoModel>) {
        val topfive = ls.sortedByDescending { it.numViews.toInt() }.take(5)
        topfive.forEach{
            LOG.info(it.videoTitle + "------" + it.numViews + "------" + it.href + "\n")
        } 

    }
    fun sum50Views(ls : List<VideoModel>) {
        val top = ls.take(50)
        val sumViews = top.sumBy { 
            it.numViews.toInt()
        }
        LOG.info("The view sum of the first 50 videos: " + sumViews + "\n")
    }

    fun top5Longuest(ls : List<VideoModel>){
        ls.forEach { 
            val time = LocalTime.parse(it.timeLength, DateTimeFormatter.ofPattern("H:mm:ss"))
        }
    }

}