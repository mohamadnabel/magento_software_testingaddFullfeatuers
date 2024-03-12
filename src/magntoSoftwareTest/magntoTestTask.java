package magntoSoftwareTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.swing.ListCellRenderer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class magntoTestTask {
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));// even if the problem solve the implicitly
																			// stop waiting
	}

	@Test(invocationCount = 1, priority = 1)
	public void addOneRandomItem() throws InterruptedException {

		driver.get(URL);

		int randomIndex = rand.nextInt(4);

		WebElement contenier = driver.findElement(By.className("product-items"));

		List<WebElement> listItems = contenier.findElements(By.tagName("li"));

//		for (int i = 0; i < listItems.size(); i++) {
		listItems.get(randomIndex).click();
		Thread.sleep(1500);

		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
			WebElement addRandomlyItem = driver.findElement(By.name("product-addtocart-button"));

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
			WebElement addRandomlyItem = driver.findElement(By.id("product-addtocart-button"));

			addRandomlyItem.click();
			Thread.sleep(3000);

		}

//		}
		;
	}

	@Test(priority = 2, description = "this is check out test")
	public void checkOut() throws InterruptedException {

//		System.out.println("Test num 2 it's done ");
		String URLcheckOut = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(URLcheckOut);
		WebElement buttonToCheckOut = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
		buttonToCheckOut.click();
		Thread.sleep(4000);
	};

	@Test(priority = 3)
	public void signupProcess() throws InterruptedException {

		WebElement email = driver.findElement(By.id("customer-email"));
		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement streetAddress = driver.findElement(By.name("street[0]"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("region_id"));
		WebElement zipPostalCode = driver.findElement(By.name("postcode"));
		WebElement country = driver.findElement(By.name("country_id"));
		WebElement phoneNumber = driver.findElement(By.name("telephone"));

		email.sendKeys("mohammadnabeel@gmail.com");
		firstName.sendKeys("mohammad");
		lastName.sendKeys("hussein");
		streetAddress.sendKeys("marka");
		city.sendKeys("amman");
		state.sendKeys("jordan");
		zipPostalCode.sendKeys("001548");
		country.sendKeys("jordan");
		phoneNumber.sendKeys("07914253688");

		Thread.sleep(3000);

		driver.findElement(By.cssSelector("button.action.continue.primary")).click();

	}
}
