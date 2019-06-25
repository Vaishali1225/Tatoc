import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

 

public class tatoc { 

public static void main(String[] args) throws InterruptedException { 
System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.yadav\\Downloads\\chromedriver_win32\\chromedriver.exe"); 
WebDriver driver=new ChromeDriver(); 
//page1
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

} 
}
