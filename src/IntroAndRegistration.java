import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntroAndRegistration {

    private static AndroidDriver<MobileElement> driver;


    public IntroAndRegistration(AndroidDriver<MobileElement> driver) {
    this.driver = driver;
    }

    public static void Register(ExtentTest test){
        driver.findElement(By.id("il.co.mintapp.buyme:id/walletTab")).click();
        driver.findElement(By.id("il.co.mintapp.buyme:id/googleButton")).click();
        //if ther is only one acount ther is nothing to chose from
        try {
            driver.findElement(By.id("com.google.android.gms:id/account_name")).click();
        }catch(WebDriverException e){}
        driver.findElement(By.id("il.co.mintapp.buyme:id/homeTab")).click();
        test.log(Status.PASS,"log in was successful!");
    }

    public static void SighnOut(ExtentTest test){
        //enter profile tab
        driver.findElement(By.id("il.co.mintapp.buyme:id/profileTab")).click();
        //checks if logged in- if the log out button is not visible WebDriver exception
        try{
            scrollToElementByText("יציאה");
            //click by text
            driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"יציאה\")")).click();
            //click i am sur
            driver.findElement(By.id("android:id/button1")).click();
            test.log(Status.INFO,"test start, log out process completed");
        }catch(WebDriverException e){
            test.log(Status.INFO,"test start");
        }
    }

    private static void scrollToElementByText(String text){
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().text(\""+text+"\"))"));
    }
}
