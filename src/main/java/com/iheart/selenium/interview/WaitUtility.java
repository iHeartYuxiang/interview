package com.iheart.selenium.interview;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class WaitUtility {

	public static void sleep(long milliSecond)
	{
		try{
			Thread.sleep(milliSecond);
		}catch(Exception e)
		{
			
		}
	}
	
	
	public static void waitForPageToLoad(WebDriver driver) {

	     ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,1000);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	              System.out.println("Timeout waiting for Page Load Request to complete.");
	      }
	 } 
	/*
	public static void waitForAjax(WebDriver driver)
	{    injectJQuery(driver);
		//Check: how many on-going ajax call on this page?
		long ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
	//	System.out.println("Ajax call count:" + ajaxCallCount);
	    while (true) // Handle timeout somewhere
	    {
	        boolean ajaxIsComplete =(Boolean) ((JavascriptExecutor)driver ).executeScript("return jQuery.active == 0");
	        if (ajaxIsComplete)
	            break;
	        sleep(1000);
	    }
	   
	    ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
		System.out.println("Active Ajax call count after waiting:" + ajaxCallCount);
	}

	*/
	
	/** dynamically load jQuery */
	public static void injectJQuery(WebDriver driver){
	    String LoadJQuery = "(function(jqueryUrl, callback) {\n" +
	            "if (typeof jqueryUrl != 'string') {" +
	            "jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js';\n" +
	            "}\n" +
	            "if (typeof jQuery == 'undefined') {\n" +
	            "var script = document.createElement('script');\n" +
	            "var head = document.getElementsByTagName('head')[0];\n" +
	            "var done = false;\n" +
	            "script.onload = script.onreadystatechange = (function() {\n" +
	            "if (!done && (!this.readyState || this.readyState == 'loaded'\n" +
	            "|| this.readyState == 'complete')) {\n" +
	            "done = true;\n" +
	            "script.onload = script.onreadystatechange = null;\n" +
	            "head.removeChild(script);\n" +
	            "callback();\n" +
	            "}\n" +
	            "});\n" +
	            "script.src = jqueryUrl;\n" +
	            "head.appendChild(script);\n" +
	            "}\n" +
	            "else {\n" +
	            "callback();\n" +
	            "}\n" +
	            "})(arguments[0], arguments[arguments.length - 1]);\n";
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	   // give jQuery time to load asynchronously
	   driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	   js.executeAsyncScript(LoadJQuery);
	    System.out.println("Jquery is loaded.");
	}	
	
	
}
