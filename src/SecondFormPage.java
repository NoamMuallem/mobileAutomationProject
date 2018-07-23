import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import jdk.net.SocketFlow;
import org.openqa.selenium.By;

import java.util.List;

public class SecondFormPage {
    private static AndroidDriver<MobileElement> driver;

    public SecondFormPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public static void Fill(ExtentTest test){
        //send now
        driver.findElement(By.id("il.co.mintapp.buyme:id/nowRadioButton")).click();
        //choose via mail
        List<MobileElement> buttons = driver.findElements(By.id("il.co.mintapp.buyme:id/optionCheckBox"));
        buttons.get(2).click();
        //enter email adress
        buttons = driver.findElements(By.id("il.co.mintapp.buyme:id/description"));
        buttons.get(1).sendKeys("thedude071@gmail.com");
        driver.hideKeyboard();
        scrollToElementById("il.co.mintapp.buyme:id/goNextButton");
        //press next
        driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton")).click();
        test.log(Status.PASS,"all details was entered successfully, test complete!");
        test.log(Status.INFO,"test is done!");
    }

    private static void scrollToElementById(String id){
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()." +
                "scrollable(true)).scrollIntoView(new UiSelector().resourceId(\""+id+"\"))"));
    }
}
