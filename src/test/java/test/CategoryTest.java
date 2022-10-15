package test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.CategoryListPage;
import util.BrowserFactory;

public class CategoryTest {

	WebDriver driver;
	CategoryListPage categoryListPage;

	Random rnd = new Random();
	String categoryName = "Vijaya" + rnd.nextInt(100);
	String duplicateCategoryName = "Duplicate" + rnd.nextInt(100);

	@BeforeMethod
	public void runCategortTest() {

		driver = BrowserFactory.init();
		categoryListPage = PageFactory.initElements(driver, CategoryListPage.class);

	}

	@Test
	public void userShouldAbleToAddCategory() throws InterruptedException {

		categoryListPage.insertCategory(categoryName);
		List<String> actualList = categoryListPage.getListOfCategory();
		Assert.assertTrue(categoryListPage.dataExist(categoryName, actualList), "New category does not exist! ");

	}

	@Test
	public void userShouldNotBeAbleToAddDuplicate() throws InterruptedException {

		categoryListPage.insertCategory(duplicateCategoryName);
		categoryListPage.insertCategory(duplicateCategoryName);
		Assert.assertTrue(categoryListPage.duplicateCategoryMessageIsDisplayed(), "Duplicate category is not created!");

	}
	@AfterMethod
	public void tearDown() {
		BrowserFactory.tearDown();
		
	}
}