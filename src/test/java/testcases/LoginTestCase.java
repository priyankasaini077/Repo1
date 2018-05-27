package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.facebook.Login;

public class LoginTestCase extends Login{
	
@Test
public void loginTest() {
	
 type("username_xpath","username");
 type("password_xpath","password");
 click("login_xpath");
 Assert.assertTrue(isElementPresent("elementpresent_xpath"), "Successful Login!!");
 type("status_xpath","Hello World!");
 
 
}
}
