package FirstTestNG;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

//@Ignore("Throws Null Pointer Exception")

public class SeleniumWebDriverTest {
	public WebDriver driver;
	//public WebDriver driver;
	//public WebDriverWait myWaitVar = new WebDriverWait(driver, 30);
	
	@BeforeClass
	public void beforeTest() {
		//WebDriverWait myWaitVar = new WebDriverWait(driver, 30);
		System.setProperty("webdriver.chrome.driver","C:\\Users\\vaishali.yadav\\Downloads\\chromedriver_win32\\chromedriver.exe");
	    driver=new ChromeDriver();
	    driver.manage().window().maximize();
	    //myWaitVar = new WebDriverWait(driver, 30);
	} 
	

	@Test
	public void MyFirstTestNGTestCase() throws InterruptedException {


driver.navigate().to("http://10.0.1.86/tatoc"); 
Thread.sleep(2000);

//page2
driver.findElement(By.partialLinkText("Basic")).click();
Thread.sleep(1000);
//page3
driver.findElement(By.className(("greenbox"))).click();
Thread.sleep(1000);
//page4
WebElement ele = driver.findElement(By.id("main"));
driver.switchTo().frame(ele);
String expAnswer = driver.findElement(By.id("answer")).getAttribute("class");		
Boolean cond=true;

while(cond)
{   driver.findElement(By.linkText("Repaint Box 2")).click();

	WebElement childele = driver.findElement(By.id("child"));
	driver.switchTo().frame(childele);	
	String actualAnswer = driver.findElement(By.id("answer")).getAttribute("class");		
	
	if(actualAnswer.equals(expAnswer)) {
		cond = false;
		
	}
	driver.switchTo().parentFrame();
	
}

driver.findElement(By.linkText("Proceed")).click();
Thread.sleep(1000);
//page5
Actions act=new Actions(driver);					
	act.dragAndDrop(driver.findElement(By.id("dragbox")), driver.findElement(By.id("dropbox"))).build().perform();
driver.findElement(By.linkText("Proceed")).click();
driver.findElement(By.linkText("Launch Popup Window")).click();

String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
String subWindowHandler = null;

Set<String> handles = driver.getWindowHandles(); // get all window handles
Iterator<String> iterator = handles.iterator();
while (iterator.hasNext()){
    subWindowHandler = iterator.next();
}
driver.switchTo().window(subWindowHandler); // switch to popup window

// Now you are in the popup window, perform necessary actions here

Thread.sleep(1000);
driver.findElement(By.id("name")).click();
Thread.sleep(1000);
driver.findElement(By.id("name")).sendKeys("Test");
Thread.sleep(1000);
driver.findElement(By.id("submit")).click();
Thread.sleep(1000);
driver.switchTo().window(parentWindowHandler);
Thread.sleep(1000);
driver.findElement(By.linkText("Proceed")).click();

driver.findElement(By.linkText("Generate Token")).click();
String token=driver.findElement(By.id("token")).getText();
System.out.println("token : "+ token);

String []ss=token.split("\\s+");
System.out.println(ss[1]);

Cookie name=new Cookie("mycookie",ss[1]);
driver.manage().addCookie(name);
driver.manage().getCookies(); 
Thread.sleep(1000);

driver.findElement(By.linkText("Proceed")).click();
Thread.sleep(3000);

        System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
}


