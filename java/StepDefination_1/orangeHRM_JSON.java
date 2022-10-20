package StepDefination_1;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class orangeHRM_JSON {

	WebDriver driver;
	
@BeforeTest
public void setUp() 
{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sneha_demanna\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
}
@Test(dataProvider = "data")

 void login(String data)
	{
		String users[] = data.split(",");	
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");	
		driver.findElement(By.name("username")).sendKeys(users[0]);
		driver.findElement(By.name("password")).sendKeys(users[1]);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]")).click();
	}
	@AfterTest
	public void tearDown() 
	{
		driver.close();
		driver.quit();
	}

	@DataProvider(name = "data")
	public String[] readJson() throws IOException, ParseException
	{
	        JSONParser jsonParser = new JSONParser();	
	        FileReader reader = new FileReader("C:\\Users\\sneha_demanna\\eclipse-workspace\\cucumber_java_1\\JSON_files\\testdata.json"); 
	        
	        Object obj = jsonParser.parse(reader);
	        
	        JSONObject userLoginJsonObj = (JSONObject)obj;
	        
	        JSONArray userLoginArray =(JSONArray) userLoginJsonObj.get("userlogins");
	        
	        String arr[] = new String[userLoginArray.size()];
	        
	        for(int i=0 ;i<userLoginArray.size(); i++)
	        {
	        	JSONObject users = (JSONObject)userLoginArray.get(i);
	        	String user = (String) users.get("username");
	        	String pass = (String) users.get("password");
	        	
	        	arr[i] = user+","+pass;
	        }
	   return arr;     
	}

}




