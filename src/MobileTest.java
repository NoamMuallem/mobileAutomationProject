import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MobileTest {

    static ExtentReports extent;
    static ExtentTest test;
    static ArrayList<String> parameters = new ArrayList<>();
    static AndroidDriver<MobileElement> driver;
    static IntroAndRegistration introAndRegistration;
    static HomeScreen homeScreen;
    static SendGift sendGift;
    static SecondFormPage secondFormPage;

    @BeforeClass
    public static void init(){
        //reading from XML - 0.package, 1.activities, 2.reports path
        getData(parameters);
        DriverInit();
        PageInit();
        reportsInit();
        introAndRegistration.SighnOut(test);
    }

    @Test
    public void test01_introAndRegistration(){
        introAndRegistration.Register(test);
    }

    @Test
    public void test02_SearchingBusiness(){
        homeScreen.FindBusiness(test);
    }

    @Test
    public void test03_FillingForm(){
        sendGift.FilForm();
    }
    @Test
    public void test04_FillForm2(){
        secondFormPage.Fill(test);
    }

    @AfterClass
    public static void terminate(){
        driver.quit();
        extent.flush();
    }

    public static void getData(ArrayList<String> output) {

        File configXmlFile = new File("C:\\Users\\noam\\mobile project\\config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;

        assert dBuilder != null;
        try {
            try {
                doc = dBuilder.parse(configXmlFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        output.add(doc.getElementsByTagName("AppPackage").item(0).getTextContent());
        output.add(doc.getElementsByTagName("AppActivity").item(0).getTextContent());
        output.add(doc.getElementsByTagName("ReportPath").item(0).getTextContent());
    }

    public static void reportsInit() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(parameters.get(2));

        // choose to append each test
        htmlReporter.setAppendExisting(true);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest(MyConst.TEST_NAME, MyConst.TEST_DESCRIPTION);
    }

    public static void DriverInit(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        capabilities.setCapability("appPackage", parameters.get(0));
        capabilities.setCapability("appActivity", parameters.get(1));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static void PageInit(){
        introAndRegistration = new IntroAndRegistration(driver);
        homeScreen = new HomeScreen(driver);
        sendGift = new SendGift(driver);
        secondFormPage = new SecondFormPage(driver);
    }
}