package week4.day2.assignments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) {
//		1) Go to https://www.nykaa.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
//		2) Mouseover on Brands and Search L'Oreal Paris
		WebElement brands = driver.findElement(By.xpath("//div[@id='headerMenu']//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).build().perform();
		
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		
//		3) Click L'Oreal Paris
		driver.findElement(By.xpath("(//a[contains(text(),'Paris')])[1]")).click();
		
//		4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		System.out.println("Title : "+driver.getTitle());
		
//		5) Click sort By and select customer top rated
		driver.findElement(By.xpath("(//div[@id='list-wrapper']//child::span)[1]")).click();
		driver.findElement(By.xpath("//div[@id='list-wrapper']//child::ul//span[text()='customer top rated'][1]")).click();	
		
//		6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("(//div[@id='list-wrapper']//child::span)[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class,'filter-name') and contains(text(),'Hair')]")).click();
		driver.findElement(By.xpath("//div[@id='list-wrapper']//child::ul//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//div[@id='list-wrapper']//child::ul//span[text()='Shampoo']")).click();
		
//		7) Click->Concern->Color Protection
		driver.findElement(By.xpath("(//div[@id='list-wrapper']//child::span[text()='Concern'])[1]")).click();
		driver.findElement(By.xpath("//div[@id='list-wrapper']//child::ul//span[text()='Color Protection']")).click();
		
//		8)check whether the Filter is applied with Shampoo
		String filterValue = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		Assert.assertEquals(filterValue, "Shampoo");
		
//		9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("(//div[contains(text(),'Colour Protect Shampoo')])[1]")).click();
		
//		10) GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		WebElement selectSize = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select select = new Select(selectSize);
		select.selectByValue("0");
		
//		11) Print the MRP of the product
		WebElement mrp = driver.findElement(By.xpath("(//span[contains(text(),'MRP')]//following-sibling::span)[1]"));
		System.out.println("MRP of the product is "+mrp.getText());
		
//		12) Click on ADD to BAG
		driver.findElement(By.xpath("(//button//span[text()='ADD TO BAG'])[1]")).click();
		
//		13) Go to Shopping Bag 
		driver.findElement(By.xpath("(//span[text()='Account']//following::button)[1]")).click();
		
//		14) Print the Grand Total amount
		driver.switchTo().frame(0);
		String grandTotalCart = driver.findElement(By.xpath("(//span[text()='Grand Total']//following::div)[1]")).getText();
		System.out.println("Total price in the cart is "+grandTotalCart);
		
//		15) Click Proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		
//		16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[contains(text(),'CONTINUE AS GUEST')]")).click();
		
//		17) Check if this grand total is the same in step 14
		String checkoutGranTotal = driver.findElement(By.xpath("(//div[contains(text(),'Grand Total')]//following::div)[1]")).getText();
		Assert.assertEquals(checkoutGranTotal, grandTotalCart);
		
//		18) Close all windows
		driver.quit();


}

}