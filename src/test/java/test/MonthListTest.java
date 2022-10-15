package test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.MonthListPage;
import util.BrowserFactory;

public class MonthListTest {

	WebDriver driver;
	MonthListPage monthListPage;

	@BeforeMethod
	public void runMonthListTest() {

		driver = BrowserFactory.init();
		monthListPage = PageFactory.initElements(driver, MonthListPage.class);
	}

	@Test
	public void dropDownShouldHaveAllMonths() {

		List<String> months = new ArrayList<String>();

		months.add("None");
		months.add("Jan");
		months.add("Feb");
		months.add("Mar");
		months.add("Apr");
		months.add("May");
		months.add("Jun");
		months.add("Jul");
		months.add("Aug");
		months.add("Sep");
		months.add("Oct");
		months.add("Nov");
		months.add("Dec");

		List<String> dropDownList = monthListPage.getMonthList();

		Assert.assertTrue(monthListPage.doesDataMatch(months, dropDownList), "Values do not match!");

	}
	@AfterMethod
	public void tearDown() {
		BrowserFactory.tearDown();
		
	}
}
