package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrmsSubmittingRequestPage {
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
	
	public TrmsSubmittingRequestPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToIndex() {
		driver.get("");
	}

	public void navigateTosubRequests() {
		driver.get("file:///Users/raquelsmoura/p1-trms-RaquelCode/trms-back/src/test/resources/getRequests.feature:7");
	}
	
	
	
	
	
	
	
	

	
}
