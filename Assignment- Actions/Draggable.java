package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draggable {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://www.leafground.com/pages/drag.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		
		WebElement element = driver.findElement(By.id("draggable"));
		Point location = element.getLocation();
		int x = location.getX();
		int y = location.getY();

		Actions builder = new Actions(driver);
		builder.dragAndDropBy(element, x + 89, y).perform();
		
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/week4day2assignment/Draggable.jpg");
		FileUtils.copyFile(screenshot, image);


	}

}
