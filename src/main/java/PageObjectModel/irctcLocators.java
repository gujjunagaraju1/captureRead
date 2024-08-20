package PageObjectModel;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket;

public class irctcLocators {
    public WebDriver driver;



        @BeforeMethod
        public void Startwith(){
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.irctc.co.in/nget/train-search");

        }
        @Test
        public void executeCapture() throws InterruptedException, IOException, TesseractException {
            driver.findElement(By.cssSelector("a.loginText")).click();
            driver.findElement(By.cssSelector("[formcontrolname=\"userid\"]")).sendKeys("nagar");
            Thread.sleep(1000);
            WebElement logo=driver.findElement(By.cssSelector("img.captcha-img"));
            File f=logo.getScreenshotAs(OutputType.FILE);
            String currentDir=System.getProperty("user.dir");
            FileUtils.copyFile(f,new File(currentDir+"/screenshot.png"));

            Tesseract data=new Tesseract();
            data.setDatapath("C:\\Users\\LT-483\\Pictures\\Tess4J\\tessdata");

            String value=data.doOCR(new File(System.getProperty("user.dir")+"/screenshot.png"));
            System.out.println(value);
            driver.findElement(By.id("captha")).sendKeys(value);


        }
        @AfterMethod
       public void tearDown(){
            driver.quit();
        }













    }


