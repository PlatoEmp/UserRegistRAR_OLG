package com.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class SelFirst {
    public static void main(String[] args) throws InterruptedException {
        String gender = (DataGen.generateRandomGender());
        String firstName = (DataGen.generateRandomName(DataGen.generateRandomNumber(5, 10)));
        String lastName = (DataGen.generateRandomName(DataGen.generateRandomNumber(5, 10)));
        String middleInitial = (DataGen.generateRandomName(1));
        String addressName = (DataGen.generateRandomNumber(1, 600).toString() + " " + DataGen.generateRandomAddress(DataGen.generateRandomNumber(5, 10)));
        String addressPostal = ("M3C 0C1");
        String city = "Toronto";
        String[] dob = DataGen.generateRandomDob();
        String dobMonth = DataGen.convertToMonthNum(dob[1]);
        String email = (firstName + "@" + lastName + ".com");
        String phoneNum = DataGen.generateRandomTORPhone();
        String userName = (firstName + middleInitial + lastName);
        String password = ("Passw0rd");


        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //System.setProperty("webdriver.edge.driver", "C:\\Selenium\\edgedriver_win64\\msedgedriver.exe");
        //WebDriver driver = new EdgeDriver();

        driver.get("https://np01-playolg.wma.bedegaming.net/en/home.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Set<String> cookie_set = new HashSet<>();
        cookie_set.add(String.valueOf(driver.manage().getCookies()));
        System.out.println(cookie_set);
        driver.findElement(By.linkText("SIGN UP")).click();

        driver.findElement(By.xpath("//*[@id=\"preRegEmail\"]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"preRegistration\"]/form/fieldset/button")).click();

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("middleName")).sendKeys(middleInitial);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        Select drp1 = new Select((driver.findElement(By.id("securtyQuestion1"))));
        drp1.selectByVisibleText("What is the name of your oldest sibling?");
        String answer1 = RandomStringUtils.randomAlphabetic(5);
        driver.findElement(By.id("securityAnswer1")).sendKeys(answer1);
        Select drp2 = new Select((driver.findElement(By.xpath("//*[@id=\"securtyQuestion2\"]"))));
        drp2.selectByVisibleText("What was your favourite subject in high school?");
        String answer2 = RandomStringUtils.randomAlphabetic(5);
        driver.findElement(By.xpath("//*[@id=\"securityAnswer2\"]")).sendKeys(answer2);

        String Expected_url = "https://np05-playolg.wma.bedegaming.net/en/home.html";
        String actual_url = driver.getCurrentUrl();
        if (Expected_url.equals(actual_url)) {
            System.out.println("The page URL is correct");
        } else {
            System.out.println("The page URL is Incorrect");
        }

        driver.findElement(By.xpath("//*[@id=\"accountInfo\"]/form/fieldset/button")).click();
        sleep(2000);

        //-------------------------------SECOND PAGE-------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------

        driver.findElement(By.xpath("//input[@id='street']")).sendKeys(addressName);
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@id='postalCode']")).sendKeys(addressPostal);
        driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys(phoneNum);
        Select drp3 = new Select((driver.findElement(By.xpath("//select[@id='dateOfBirth-select-date']"))));
        drp3.selectByVisibleText(dob[2]);
        Select drp4 = new Select(driver.findElement(By.xpath("//select[@id='dateOfBirth-select-month']")));
        drp4.selectByVisibleText(dobMonth);
        Select drp5 = new Select(driver.findElement(By.xpath("//select[@id='dateOfBirth-select-year']")));
        drp5.selectByVisibleText(dob[3]);
        Select drp6 = new Select(driver.findElement(By.xpath("//select[@id='citizenships']")));
        drp6.selectByVisibleText("Canadian");
        driver.findElement(By.xpath("//*[@id=\"personalInfo\"]/form/fieldset/button")).click();
        sleep(2000);

        //-------------------------------------END OF PAGE 2-------------------------------------------------------
        //--------------------------------------START OF PAGE 3----------------------------------------------------

        driver.findElement(By.xpath("//span[contains(text(),'No, I don’t want to hear about promos')]")).click();
        driver.findElement(By.xpath("//span[@class='bold btn btn-link accordion-title']")).click();
        driver.findElement(By.xpath("//*[@id=\"preferences\"]/form/button")).click();
        sleep(5000);

        driver.findElement(By.xpath("//*[@id=\"summary\"]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"terms\"]/form/div[2]/div[2]/div/div/label/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='registerAcceptTermsBtn']")).click();
        sleep(3000);


        //----------------------------------------------END OF PAGE 3--------------------------------------------------
        //----------------------------------------------CONFIRMATION PAGE----------------------------------------------

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/test/players.txt", true))) {
            // Write using the PrintWriter instance
            writer.append("The email ID is:" + email + "\n");
            System.out.println("\n");
            writer.append("The Gender is: " + gender + "\n");
            System.out.println("\n");
            writer.append("The firstname is: " + firstName + "\n");
            System.out.println("\n");
            writer.append("The middle name is: " + middleInitial + "\n");
            System.out.println("\n");
            writer.append("The Lastname is: " + lastName + "\n");
            System.out.println("\n");
            writer.append("The Address is: " + addressName + " " + city + " " + addressPostal + "\n");
            System.out.println("\n");
            writer.append("The DOB is: " + dob[4] + "\n");
            System.out.println("\n");
            writer.append("The Phone Number is: " + phoneNum + "\n");
            System.out.println("\n");
            writer.append("The username is: " + userName + "\n");
            System.out.println("\n");
            writer.append("The Password is: " + password + "\n");
            System.out.println("\n");
            //writer.append("The security question 1's answer is: " + answer1 + "\n");
            //System.out.println("\n");
            //writer.append("The Security question 2's answer is: " + answer2 + "\n");
            //System.out.println("\n");
            writer.append("The Date & time of saving the above credentials: " + dtf.format(now) + "\n");
            System.out.println("\n");
            writer.append("Line added on: " + new java.util.Date() + "\n");
            System.out.println("\n");
            writer.append("---------------------------------------------------");
            System.out.println("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.get("https://np01-playeraccountmanagement-bos.olg.bedegaming.net/");
        sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys("your_username");
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("your_password");
        driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
        sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"unifiedSearch\"]")).sendKeys(userName);
        driver.findElement(By.xpath("//*[@id=\"unifiedSubmit\"]")).click();
        sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"statusFlag\"]")).click();
        sleep(3000);
        driver.findElement(By.xpath("/html/body/modal-container/div/div/app-change-profile-status-modal/app-confirm-modal/div[2]/div/app-select-action/div/label[2]")).click();
        driver.findElement(By.xpath("/html/body/modal-container/div/div/app-change-profile-status-modal/app-confirm-modal/div[2]/div/form/app-note-input/div/input")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id=\"showConfirmViewButton\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"finalConfirmButton\"]")).click();
        sleep(8000);

        WebElement iDStatusBtn = driver.findElement(By.id("idStatus"));
        wait.until(ExpectedConditions.visibilityOf(iDStatusBtn));
        iDStatusBtn.click();

        WebElement updateStatusBtn = driver.findElement(By.xpath("/html/body/modal-container/div/div/app-id-status-modal/app-manage-status-modal/app-confirm-modal/div[2]/app-select-action/div/label[1]"));
        updateStatusBtn.click();

        driver.findElement(By.xpath("/html/body/modal-container/div/div/app-id-status-modal/app-manage-status-modal/app-confirm-modal/div[2]/app-select-action/div/label[1]")).click();
        driver.findElement(By.cssSelector("input[id='status']")).click();
        List<WebElement> elems = driver.findElements(By.cssSelector("div[class='ng-option']"));
        WebElement passedSelection = elems.get(3);
        passedSelection.click();
        driver.findElement(By.xpath("//*[@id=\"playerNote\"]")).sendKeys("anything");
        driver.findElement(By.id("showConfirmViewButton")).click();
        driver.findElement(By.xpath("//*[@id=\"finalConfirmButton\"]")).click();
        sleep(2000);

        List<WebElement> elems1 = driver.findElements(By.cssSelector("span[class='label']"));
        WebElement EmailVeri = elems1.get(5);
        EmailVeri.click();

        driver.findElement(By.xpath("/html/body/modal-container/div/div/app-email-verification-modal/app-manage-status-modal/app-confirm-modal/div[2]/app-select-action/div/label[1]")).click();
        driver.findElement(By.xpath("/html/body/modal-container/div/div/app-email-verification-modal/app-manage-status-modal/app-confirm-modal/div[2]/form/div/app-profile-status-update/form/div/div/ng-select/div/div/div[3]/input")).click();
        List<WebElement> elems2 = driver.findElements(By.cssSelector("div[class='ng-option']"));
        WebElement verifySelection = elems2.get(2);
        verifySelection.click();
        driver.findElement(By.xpath("//*[@id=\"playerNote\"]")).sendKeys("anything");
        driver.findElement(By.id("showConfirmViewButton")).click();
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"finalConfirmButton\"]")).click();
        sleep(3000);

        driver.get("https://np05-playolg.wma.bedegaming.net/en/home.html");
        sleep(2000);

        driver.findElement(By.id("login-button-sign-in-register")).click();
        driver.findElement(By.id("loginUsername_login-modal")).sendKeys(userName);
        driver.findElement(By.id("loginPassword_login-modal")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"legalCopy_login-modal\"]")).click();
        driver.findElement(By.id("login-form-submit-button_login-modal")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div/div/div/div/header/div[1]/div[2]/div[4]/div/div[1]/div/button")).click();
        driver.findElement(By.cssSelector("a[href='/en/account/view-account.html']")).click();


        //driver.findElement(By.id("loginAcceptBtn")).click();
        //Thread.sleep(10000);
        //driver.findElement(By.id("dLabel")).click();
        //driver.findElement(By.linkText("Account Information\n" +
        //        "                                   ")).click();
        //Thread.sleep(5000);
        while (driver.getCurrentUrl() == "https://np01-playolg.wma.bedegaming.net/en/account/view-account.html") {
            Thread.sleep(5000);
        }

        //driver.quit();
        }
    }


