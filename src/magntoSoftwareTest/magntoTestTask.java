package magntoSoftwareTest;

import java.util.List;
import java.util.Random;

import javax.swing.ListCellRenderer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class magntoTestTask {
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
	}

	@Test(invocationCount = 10)
	public void addOneRandomItem() throws InterruptedException {

		driver.get(URL);

		int randomIndex = rand.nextInt(6);

		WebElement contenier = driver.findElement(By.className("product-items"));

		List<WebElement> listItems = contenier.findElements(By.tagName("li"));

//		for (int i = 0; i < listItems.size(); i++) {
		listItems.get(randomIndex).click();
		Thread.sleep(1500);

		WebElement addRandomlyItem = driver.findElement(By.id("product-addtocart-button"));

		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {

			addRandomlyItem.click();
		} else {
			WebElement sizeContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

			List<WebElement> listOfColors = sizeContainer.findElements(By.tagName("div"));
			int randomSize = rand.nextInt(listOfColors.size());
			listOfColors.get(randomSize).click();

			WebElement colorsContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
			List<WebElement> listRandomColors = colorsContainer.findElements(By.tagName("div"));
			int randomColors = rand.nextInt(listRandomColors.size());
			listRandomColors.get(randomColors).click();
			addRandomlyItem.click();

		}

//		}
		;
	}

}
