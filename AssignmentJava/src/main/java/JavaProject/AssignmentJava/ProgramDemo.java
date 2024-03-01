package JavaProject.AssignmentJava;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProgramDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		
		try {
			
			System.out.println("Using FOR-EACH loop");
			List<WebElement> links = driver.findElements(By.tagName("a"));
			for(WebElement ele:links)
			{
				System.out.println(ele.getAttribute("href"));			
								
			}
	
			System.out.println("Using Stream");
			
			List<WebElement> list_links = driver.findElements(By.tagName("a"));
			List<String> stream_Links=list_links.stream().map(WebElement::getText).collect(Collectors.toList());
			stream_Links.stream().forEach(System.out::println);
			
			
			System.out.println("Using Parallel Stream");
			List<WebElement> list_links2 = driver.findElements(By.tagName("a"));
			List<String> stream_Links2=list_links2.parallelStream().map(WebElement::getText).collect(Collectors.toList());
			stream_Links2.parallelStream().forEach(System.out::println);
			
			System.out.println("Using Lambda Exp");
			List<WebElement> list_links3 = driver.findElements(By.tagName("a"));
			list_links3.forEach(link->System.out.println(link.getAttribute("href")));
			
			driver.quit();
		}		
		catch(Exception e)
		{
		e.getMessage();
		}

	}

}
