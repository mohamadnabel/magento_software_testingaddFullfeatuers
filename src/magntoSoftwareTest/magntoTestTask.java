package magntoSoftwareTest;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.swing.ListCellRenderer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
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
		String expectedMsg = "Thank you for registering with Main Website Store.";

		WebElement email = driver.findElement(By.id("customer-email"));
		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement streetAddress = driver.findElement(By.name("street[0]"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("region_id"));
		WebElement zipPostalCode = driver.findElement(By.name("postcode"));
		WebElement country = driver.findElement(By.name("country_id"));
		WebElement phoneNumber = driver.findElement(By.name("telephone"));

		String pass = "mohammadN@123";

		email.sendKeys("mohammadnabeel4@gmail.com");
		firstName.sendKeys("mohammad");
		lastName.sendKeys("hussein");
		streetAddress.sendKeys("marka");
		city.sendKeys("amman");
		state.sendKeys("jordan");
//		Select selectState = new Select(state);
//		int selectIndexSize =
//		Thread.sleep(2000);
//		selectState.selectByIndex(2);
		zipPostalCode.sendKeys("001548");
//		country.sendKeys("jordan");
		Select selectCountry = new Select(country);
//		selectCountry.selectByIndex(4);
//		selectCountry.selectByValue("AF");
		selectCountry.selectByVisibleText("Afghanistan");
		phoneNumber.sendKeys("07914253688");

		Thread.sleep(3000);

		driver.findElement(By.cssSelector("button.action.continue.primary")).click();
		Thread.sleep(4000);

		WebElement placeOrder = driver.findElement(By.cssSelector(".action.primary.checkout"));
		placeOrder.click();
		Thread.sleep(4000);

		WebElement continueToCreateAccount = driver.findElement(
				By.xpath("//a[@href=\'https://magento.softwaretestingboard.com/checkout/account/delegateCreate/\']"));
		continueToCreateAccount.click();
		Thread.sleep(2000);

		WebElement password = driver.findElement(By.id("password"));
		WebElement confirmPass = driver.findElement(By.id("password-confirmation"));

		password.sendKeys(pass);
		confirmPass.sendKeys(pass);

		WebElement submitCreatAccount = driver.findElement(By.cssSelector(".action.submit.primary"));
		submitCreatAccount.click();

		Thread.sleep(2000);

		WebElement sucsesfulyAddAccountMsg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));

		String actualMsg = sucsesfulyAddAccountMsg.getText();

		assertEquals(actualMsg, expectedMsg);
	}
}
