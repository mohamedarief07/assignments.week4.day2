package week4.day2.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWaitTextChange {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// open url
		driver.get("http://www.leafground.com/pages/TextChange.html");
		// maximize window
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement ClickMEelement = driver.findElement(By.id("btn"));

		File SourceFile = driver.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./snaps/week4day2assignment/beforeClick ME!.jpg");
		FileUtils.copyFile(SourceFile, targetFile);

		wait.until(ExpectedConditions.textToBePresentInElement(ClickMEelement, "Click ME!"));

		File SourceFile1 = driver.getScreenshotAs(OutputType.FILE);
		File targetFile1 = new File("./snaps/week4day2assignment/afterClick ME!.jpg");
		FileUtils.copyFile(SourceFile1, targetFile1);

		ClickMEelement.click();

		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();

		System.out.println(text);

		boolean contains = text.contains("Click ME!");
		if (contains) {
			System.out.println("PASSED");
		} else {
			System.out.println("Appearing- fAILED");
		}
		
		driver.close();
	}
}
