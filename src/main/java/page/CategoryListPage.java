package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryListPage {

	WebDriver driver;

	public CategoryListPage(WebDriver driver) {
		this.driver = driver;

	}

	// WebElement List:
	@FindBy(how = How.CSS, using = "input[type='submit'][value='Add category']")
	WebElement CATEGORY_BUTTON_ELEMENT;

	@FindBy(how = How.CSS, using = "input[name='categorydata']")
	WebElement CATEGORY_BOX_ELEMENT;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'The category is already exist!')]")
	WebElement CATEGORY_EXIST_ELEMENT;// HTML Tag exclusion for new category

	// Corresponding method:

	public List<String> getListOfCategory() {

		List<String> list = new ArrayList<String>();

		List<WebElement> categoryElement = driver.findElements(By.xpath("//a[@title='Remove this category']"));

		for (int i = 0; i < categoryElement.size(); i++) {
			list.add(i, categoryElement.get(i).getText());
		}
		return list;

	}

	public void insertCategory(String category) throws InterruptedException {

		CATEGORY_BOX_ELEMENT.sendKeys(category);

		Thread.sleep(5000);

		CATEGORY_BUTTON_ELEMENT.click();

	}

	public boolean duplicateCategoryMessageIsDisplayed() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(CATEGORY_EXIST_ELEMENT));

			return false;

		} catch (Exception e) {

		}
		return true;

	}

	public boolean dataExist(String categoryName, List<String> actualList) {

		for (int i = 0; i < actualList.size(); i++) {

			if (categoryName.equalsIgnoreCase(actualList.get(i))) {
				return true;
			}
		}
		return false;

	}

}
