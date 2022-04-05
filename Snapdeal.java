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
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		//1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//	2. Go to Mens Fashion
		driver.findElement(By.xpath("//div[@id='leftNavMenuRevamp']//li[1]//span[contains(text(),'Fashion')]")).click();
		
		//	3. Go to Sports Shoes
		driver.findElement(By.xpath("//div[@id='category1Data']//span[text()='Sports Shoes']")).click();
		
		
		//	4. Get the count of the sports shoes
		String shoesCount = driver.findElement(By.xpath("(//h1[@category='Sports Shoes for Men']//following::span)[1]")).getText();
		System.out.println(shoesCount);
		
		
		//	5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(5000);
		
		//	6. Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sort-selected' and contains(text(),'Popularity')]")).click();
		driver.findElement(By.xpath("(//div[@class='sort-selected']//following::ul//li)[2]")).click();
		Thread.sleep(5000);
		
		
		//	7. Check if the items displayed are sorted correctly
		List<WebElement> allProductPrices = driver.findElements(By.xpath("//p[@class='product-title']//following::span[2]"));
		List<Integer> prices = new ArrayList<Integer>();
		List<Integer> priceSet = new ArrayList<Integer>();
		
		  for(int i=1;i<allProductPrices.size();i++) { 
		  WebElement element1 =allProductPrices.get(i);
		  int priceList1 = Integer.parseInt(element1.getAttribute("display-price"));
		  int priceList2 = Integer.parseInt(element1.getAttribute("display-price"));
		  prices.add(priceList1);
		  priceSet.add(priceList2);
		  Integer.parseInt(element1.getAttribute("display-price"));
		 }
		  System.out.println(prices);
		  
		  Collections.sort(priceSet);
		  System.out.println(priceSet);
		  
		  if(prices.equals(priceSet)) {
			  System.out.println("Products are sorted from Low to High price");
		  }
		  else {
			  System.out.println("Products are NOT sorted from Low to High price");
		  }
		  
		//	8.Select the price range (900-1200)
		  WebElement fromPriceRange = driver.findElement(By.xpath("//input[@name='fromVal']"));
			WebElement toPriceRange = driver.findElement(By.xpath("//input[@name='toVal']"));
			fromPriceRange.clear();
			fromPriceRange.sendKeys("900");
			toPriceRange.clear();
			toPriceRange.sendKeys("1200");
			driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
			Thread.sleep(5000);
			
		//	9.Filter with color Navy 
			driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click();
			
		//	10 verify the all applied filters \
			boolean filterPrice = driver.findElement(By.xpath("(//div[@class='filters']//a[contains(text(),'900') and contains(text(),'1200')])[1]")).isDisplayed();
			boolean filterColor = driver.findElement(By.xpath("(//div[@class='filters']//a[contains(text(),'Black')])[1]")).isDisplayed();
			if(filterPrice && filterColor) {
				System.out.println("filters are applied");
			}
			
		//	11. Mouse Hover on first resulting Training shoes
			WebElement firstResult = driver.findElement(By.xpath("(//div[@class='product-tuple-image '])[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(firstResult).perform();
			
		//	12. click QuickView button
			driver.findElement(By.xpath("(//div[@class='product-tuple-image ']//child::div)[2]/div")).click();
			
		//	13. Print the cost and the discount percentage
			String price = driver.findElement(By.xpath("((//a[contains(text(),'view details')]//preceding-sibling::div)[3]//following-sibling::span)[2]")).getText();
			System.out.println("Price of the product is : "+price);
			String discount = driver.findElement(By.xpath("((//a[contains(text(),'view details')]//preceding-sibling::div)[3]//following-sibling::span)[3]")).getText();
			System.out.println("Discount applied : "+discount);
			
		//	14. Take the snapshot of the shoes.
			File Source = driver.getScreenshotAs(OutputType.FILE);
			File target = new File("./snaps/week4day2assignment/AfterElementAppears.jpg");
			FileUtils.copyFile(Source, target);
			
			
			//15. Close the current window
			driver.findElement(By.className                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ("(//div[@id='sidebar_modal_right']//following::div[contains(@class,'close')]/i)[1]")).click();
			
//			//16. Close the main window
			driver.close();
		
		      
		
		
		
	}
	
	


}
