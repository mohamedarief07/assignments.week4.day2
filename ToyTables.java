package week4.day2.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToyTables {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Get the count of number of rows
		WebElement table = driver.findElement(By.xpath("//table[@cellspacing='0']"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		System.out.println("Row size is :" +rows.size());
		
		//Get the count of number of columns	
		WebElement firstRow = rows.get(2);
		List<WebElement> col = firstRow.findElements(By.tagName("td"));
		System.out.println("Column size is :" +col.size());
		
		//Get the progress value of 'Learn to interact with Elements'
		WebElement ElementForLearntointeractwithElements = firstRow.findElements(By.tagName("td")).get(1);
		System.out.println("progress value of 'Learn to interact with Elements is :" +ElementForLearntointeractwithElements.getText());
		
		//Check the vital task for the least completed progress.
		List<String> list=new ArrayList <String> ();
		
		for (int i = 1; i < rows.size(); i++) {
			WebElement eachRow = rows.get(i);
			List<WebElement> eachCol = eachRow.findElements(By.tagName("td"));
			String colValue = eachCol.get(1).getText();
			System.out.println(colValue);
			list.add(i-1,colValue);
			System.out.println(list);
			
		}
		Collections.sort(list);
		System.out.println(list);
		if (list.get(1).contains("30%")) {
			firstRow.findElements(By.xpath("//input[@type='checkbox']")).get(2).click();
		}
						
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/img00001.jpg");
		FileUtils.copyFile(screenshot, image);
	}

}
