import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.List;

public class SendGift {
    private static AndroidDriver<MobileElement> driver;

    public SendGift(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public static void FilForm(){
        //scrlling down
        scrollToElementById("il.co.mintapp.buyme:id/goNextButton");
        //for hwo the gift
        driver.findElement(By.id("il.co.mintapp.buyme:id/toEditText")).sendKeys("Namma");
        driver.hideKeyboard();
        //open ocation dropdown
        driver.findElement(By.id("android:id/text1")).click();
        //find ocations
        List <MobileElement> ocatins = driver.findElements(By.id("android:id/text1"));
        //choose ocation
        ocatins.get(3).click();
        //bleesing
        driver.findElement(By.id("il.co.mintapp.buyme:id/blessEditText")).sendKeys("blessing");
        driver.hideKeyboard();
        //sender name
        driver.findElement(By.id("il.co.mintapp.buyme:id/userFrom")).sendKeys("Noam");
        driver.hideKeyboard();
        //pressing continue
        driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton")).click();
    }

    private static void scrollToElementById(String id){
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()." +
                "scrollable(true)).scrollIntoView(new UiSelector().resourceId(\""+id+"\"))"));
    }
}

