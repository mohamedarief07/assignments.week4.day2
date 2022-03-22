package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.switchTo().frame(0);

		WebElement handle1 = driver.findElement(By.xpath("//div[@id='resizable']//div[3]"));
		WebElement handle2 = driver.findElement(By.xpath("//div[@id='resizable']//div[2]"));
		WebElement handle3 = driver.findElement(By.xpath("//div[@id='resizable']//div[1]"));

		Actions builder = new Actions(driver);
		builder.moveToElement(handle1).clickAndHold(handle1).moveByOffset(20, 15).release(handle1).build().perform();
		builder.moveToElement(handle2).clickAndHold(handle2).moveByOffset(0, 50).release(handle2).build().perform();
		builder.moveToElement(handle3).clickAndHold(handle3).moveByOffset(10, 0).release(handle3).build().perform();
		
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/week4day2assignment/Resizable.jpg");
		FileUtils.copyFile(screenshot, image);

}
}