package week4.day2.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWaitAppear {
	
	
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//open url
		driver.get("http://www.leafground.com/pages/appear.html");
		//maximize window
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		File SourceFile = driver.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./snaps/week4day2assignment/beforeElementAppears.jpg");
		FileUtils.copyFile(SourceFile, targetFile);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex']//button[3]")));
		
		
		String text = driver.findElement(By.xpath("//div[@class='flex']//button[3]")).getText();
		boolean contains = text.contains("Voila! I'm here Guys");
		if(contains) {
			System.out.println("Appearing-passed");
		}else {
			System.out.println("Appearing- fAILED");
		}
		
		File SourceFile1 = driver.getScreenshotAs(OutputType.FILE);
		File targetFile1 = new File("./snaps/week4day2assignment/AfterElementAppears.jpg");
		FileUtils.copyFile(SourceFile1, targetFile1);
		
		driver.close();
	}

}
