package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class MonthListPage {

	WebDriver driver;

	public MonthListPage(WebDriver driver) {

		this.driver = driver;

	}

	// WebElement List:
	@FindBy(how = How.CSS, using = "select[name='due_month']")
	WebElement MONTH_DROPDOWN_ELEMENT;

	// Methods:

	public List<String> getMonthList() {

		MONTH_DROPDOWN_ELEMENT.click();

		List<String> actualDropDownValues = new ArrayList<String>();

		Select dropDown = new Select(MONTH_DROPDOWN_ELEMENT);

		List<WebElement> dropDownValues = dropDown.getOptions();

		for (int i = 0; i < dropDownValues.size(); i++) {

			actualDropDownValues.add(i, dropDownValues.get(i).getText());

		}
		return actualDropDownValues;

	}

	public boolean doesDataMatch(List<String> months, List<String> dropDownList) {

		boolean dataMatch = false;

		for (int i = 0; i < dropDownList.size(); i++) {

			if (dropDownList.containsAll(months) && months.containsAll(dropDownList)) {

				dataMatch = true;

			}

		}
		return dataMatch;

	}

}
