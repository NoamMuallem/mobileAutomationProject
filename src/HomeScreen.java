import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;

import java.util.List;

public class HomeScreen {
    private static AndroidDriver<MobileElement> driver;

    public HomeScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

public static void FindBusiness(ExtentTest test){

        //search button
        driver.findElement(By.id("il.co.mintapp.buyme:id/search")).click();
        //hide keyboard
        driver.findElement(By.id("il.co.mintapp.buyme:id/image_search_back")).click();
        //making list of categories
        List <MobileElement> bars = driver.findElements(By.className("android.support.v7.app.ActionBar$Tab"));
        bars.get(2).click();
        //list of the amount and location categris
        List <MobileElement> locationAndAmount = driver.findElements(By.id("il.co.mintapp.buyme:id/filterRecycleView"));
        MobileElement locations = locationAndAmount.get(0);
        MobileElement amounts = locationAndAmount.get(1);
        //list of locations from location bar
        List<MobileElement> locationsList = locations.findElements(By.id("il.co.mintapp.buyme:id/filterButton"));
        locationsList.get(1).click();
        //list of amount from location bar
        List<MobileElement> amountList = amounts.findElements(By.id("il.co.mintapp.buyme:id/filterButton"));
        amountList.get(1).click();
        //click search
        driver.findElement(By.id("il.co.mintapp.buyme:id/filterActionButton")).click();
        //choose gift
        List<MobileElement> gifts = driver.findElements(By.className("android.widget.RelativeLayout"));
        gifts.get(0).click();
        //enter price
        driver.findElement(By.id("il.co.mintapp.buyme:id/priceEditText")).sendKeys("120");
        //click buy
        driver.findElement(By.id("il.co.mintapp.buyme:id/purchaseButton")).click();
        test.log(Status.PASS,"business successfully chosen");
   }

}
