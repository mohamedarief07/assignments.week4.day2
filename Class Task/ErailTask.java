package week4.day2;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailTask {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//input[@id='txtStationFrom']")).clear();
		driver.findElement(By.xpath("//input[@id='txtStationFrom']")).sendKeys("TPJ", Keys.TAB);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='txtStationTo']")).clear();
		driver.findElement(By.xpath("//input[@id='txtStationTo']")).sendKeys("MS", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@checked='checked']")).click();

		WebElement Table = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']"));

		List<WebElement> tableElements = Table.findElements(By.tagName("tr"));
		System.out.println("No of rows : " + tableElements.size());

		WebElement webElement = tableElements.get(1);
		List<WebElement> Column = webElement.findElements(By.tagName("td"));
		System.out.println("No of Columns : " + Column.size());

		for (int i = 0; i < tableElements.size(); i++) {
			WebElement fullTable = tableElements.get(i);
			List<WebElement> Columns = fullTable.findElements(By.tagName("td"));
			for (int j = 0; j < Columns.size(); j++) {
				WebElement fullColumn = Columns.get(j);
				System.out.println(fullColumn.getText());
				System.out.println("==================");
			}

		}

		for (int i = 0; i < tableElements.size(); i++) {
			WebElement fullTable = tableElements.get(i);
			WebElement trainName = fullTable.findElements(By.tagName("td")).get(1);
			if (trainName.getText().contains("CHENNAI")) {
				WebElement trainNo = fullTable.findElements(By.tagName("td")).get(0);
				WebElement trainNameCL = fullTable.findElements(By.tagName("td")).get(1);
				System.out.println(trainNo.getText() + "--> " + trainNameCL.getText());

			}

		}
	}

}