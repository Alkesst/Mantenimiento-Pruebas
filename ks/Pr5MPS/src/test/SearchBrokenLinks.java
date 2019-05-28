package test;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchBrokenLinks {
	private static WebDriver driver;
	private static List<String> visitedElements = new ArrayList<>();
	private static int maxLevel;
	private static String domainURL;
	
	private static final String PATH = "/Users/alec/Documents/Universidad/Tercero/2Cuatri/";
	
	private static void setWebDriver(String explorer) {
		switch(explorer) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", PATH + "chromedriver");
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", PATH + "geckodriver");
				driver = new FirefoxDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", PATH + "edge");
				driver = new EdgeDriver();
				break;
			default:
				System.err.println("There's no driver to set with that explorer");
				System.exit(-1);
				break;
		}
	}
	
	private static void visitNewWebComponent(WebLevelComponent wlc) {
		if(wlc.getLevel() == maxLevel) {
			driver.get(wlc.getUrl());
			wlc.setPageName(driver.getTitle());
			return;
		}
		if(brokenLink(wlc.getUrl())) {
			wlc.setIsBroken();
			return;
		}
		driver.get(wlc.getUrl());
		visitedElements.add(wlc.getUrl());
		wlc.setPageName(driver.getTitle());
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement we : links) {
			String newURL = null;
			try {
				 newURL = we.getAttribute("href");
			} catch(StaleElementReferenceException a) {
				
			}
			if(newURL == null) {
				//we.click();
				//links.addAll(we.findElements(By.tagName("a")));
			} else {
				wlc.addChild(new WebLevelComponent(newURL, wlc.getLevel() + 1));
			}
		}
		for(WebLevelComponent wlc2 : wlc.getNextLevel()) {
			if(notVisitedAndValidURL(wlc2.getUrl())) {
				visitNewWebComponent(wlc2);
			}
		}
	}
	
	
	private static boolean notVisitedAndValidURL(String href) {
		return href.contains(domainURL) && !visitedElements.contains(href);
	}
	
	
	public static void main(String[] args) {
		if(args.length < 3) {
			System.err.println("Is required to have 3 arguments");
			System.exit(-1);
		}
		String webURL = args[0];
		String urlPattern = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		if(!webURL.matches(urlPattern)) {
			System.err.println("Not a valid web URL");
			System.exit(-1);
		}
		String[] splittedWebURL = webURL.split("[.]");
		domainURL = String.format("%s.%s", splittedWebURL[splittedWebURL.length - 2], splittedWebURL[splittedWebURL.length - 1]);
		String explorer = args[1];
		setWebDriver(explorer.toLowerCase());
		maxLevel = Integer.parseInt(args[2]);
		
		WebLevelComponent wlc = new WebLevelComponent(webURL, 0);
		visitNewWebComponent(wlc);
		System.out.println(wlc);
	}
	
	public static boolean brokenLink(String url) {
		// returns true if the link is broken
		HttpURLConnection huc;
		int respCode = -1;
		try {
			huc = (HttpURLConnection)(new URL(url).openConnection());
	        huc.setRequestMethod("HEAD");
	        
	        huc.connect();
	        
	        respCode = huc.getResponseCode();
		} catch(IOException a) {
			
		}
		return respCode == -1 || respCode >= 400;
	}
}
