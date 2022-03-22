package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
						
		WebElement select = driver.findElement(By.id("selectable"));
		WebElement item2 = driver.findElement(By.xpath("//ol[@id='selectable']/li[2]"));
		WebElement item4 = driver.findElement(By.xpath("//ol[@id='selectable']/li[4]"));
		WebElement item6 = driver.findElement(By.xpath("//ol[@id='selectable']/li[6]"));
		
		
	
		
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(item2).click(item4).click(item6).keyUp(Keys.CONTROL).perform();
		
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/week4day2assignment/Selectable.jpg");
		FileUtils.copyFile(screenshot, image);

	}

}
