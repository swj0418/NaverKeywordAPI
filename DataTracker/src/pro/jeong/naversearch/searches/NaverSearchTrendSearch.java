package pro.jeong.naversearch.searches;

import pro.jeong.naversearch.filereader.NaverTrendFileReader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class NaverSearchTrendSearch {
    String geckoDriverPath = "";
    String phantomJSDriverPath = "";
    String chromeDriverPath = "";

    String downloadPath = "";

    WebDriver driver = null;
    String keyword = "";

    LocalDate from = null;
    LocalDate to = null;

    public NaverSearchTrendSearch() {
        configurePath();

        //setSeleniumDriver();
        //setSeleniumDriverHtmlUnitDriver();
        //setSeleniumDriverPhantomJS();
        setSeleniumDriverChrome();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    private void configurePath() {
        if(System.getProperty("os.name").equals("Mac OS X")) {
            geckoDriverPath = "/Users/top1/Workspace/Programming/Java/DataTracker/driver/geckodriver";
            phantomJSDriverPath = "/Users/top1/Workspace/Programming/Java/DataTracker/driver/phantomjs";
            chromeDriverPath = "/Users/top1/Workspace/Programming/Java/DataTracker/driver/chromedriver";

            downloadPath = "/Users/top1/Workspace/Programming/Java/DataTracker/data/";
        }
    }

    private void setSeleniumDriver() {
        System.out.println("Selenium running on " + System.getProperty("os.name"));

        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "/Users/top1/Workspace/Programming/Java/DataTracker/data/");
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true );
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.openFile","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;");
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.openFile", "");
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", true);


        options.setProfile(profile);
        driver = new FirefoxDriver(options);
    }

    private void setSeleniumDriverChrome() {
        System.out.println("Selenium running on " + System.getProperty("os.name"));
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        //options.addArguments("headless");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Deprecated
    private void setSeleniumDriverHtmlUnitDriver() {
        System.out.println("Selenium running on " + System.getProperty("os.name"));
        driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1000, 3000));
    }

    @Deprecated
    private void setSeleniumDriverPhantomJS() {
        System.out.println("Selenium running on " + System.getProperty("os.name"));
        System.setProperty("phantomjs.binary.path", phantomJSDriverPath);
        driver = new PhantomJSDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public ArrayList<Float> getTrends() {
        ArrayList<Float> retArray = new ArrayList<>();
        driver.get("https://datalab.naver.com/keyword/trendSearch.naver");
        WebElement searchBox = driver.findElement(By.cssSelector("#item_sub_keyword1_1"));
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[2]/div[1]/div/form/fieldset/a"));
        searchBox.sendKeys(keyword);
        searchButton.click();

        downloadFile();
        driver.close();

        return readTrendFile();
    }

    private void downloadFile() {
        WebElement downloadButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div[1]/div[4]/a"));
        driver.get(downloadButton.getAttribute("href"));
    }

    private ArrayList<Float> readTrendFile() {
        NaverTrendFileReader reader = new NaverTrendFileReader();
        reader.setTrendFilePath(downloadPath + "datalab.xlsx");
        ArrayList<Float> trendData = reader.readFile();
        this.from = reader.getFrom();
        this.to = reader.getTo();
        return trendData;
    }
}
