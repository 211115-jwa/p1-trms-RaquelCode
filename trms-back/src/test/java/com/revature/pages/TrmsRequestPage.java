package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrmsRequestPage {
private WebDriver driver;
	
	@FindBy(id="username")
	private WebElement usernameInput;
	@FindBy(id="password")
	private WebElement passwordInput;
	@FindBy(id="loginBtn")
	private WebElement loginBtn;

	@FindBy(id="dataInput")
	private WebElement empInput;
	@FindBy(id="reqbutton")
	private WebElement reqBtn;
	
	public TrmsRequestPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToIndex() {
		driver.get("");
	}

	public void navigateToRequests() {
		driver.get("file:///Users/raquelsmoura/p1-trms-RaquelCode/trms-back/src/test/resources/getRequests.feature:7");
		
	}
	
	public void submitLogin(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}
	public String getErrorMessage() {
		WebElement errorMsg = driver.findElement(By.tagName("h3"));
		return errorMsg.getText();
	}

	public void submitRequestorId(int empId) {
		String empid = String.valueOf(empId);
		empInput.sendKeys(empid);
		reqBtn.click();
	}
	
	
}
