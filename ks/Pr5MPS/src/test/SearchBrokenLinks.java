package test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
		if(brokenLink(wlc.getUrl())) {
			wlc.setIsBroken();
			return;
		}
		driver.get(wlc.getUrl());
		visitedElements.add(cleanUpURL(wlc.getUrl()));
		wlc.setPageName(driver.getTitle());
		if(wlc.getLevel() == maxLevel) {
			return;
		}
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement we : links) {
			String newURL = null;
			try {
				 newURL = we.getAttribute("href");
			} catch(StaleElementReferenceException a) {
				System.err.println("Error en el link actual.");
			}
			if((newURL != null) && notVisitedAndValidURL(newURL)) {
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
		String temp = cleanUpURL(href);
		return !temp.contains("mailto:") && 
				temp.contains(domainURL) && 
				!visitedElements.contains(temp) && 
				!href.contains("mailto:") && !href.contains("javascript") 
				&& !href.contains("tel:");
	}
	
	
	public static void main(String[] args) {
		if(args.length < 3) {
			System.err.println("Is required to have 3 arguments");
			System.exit(-1);
		}
		String webURL = args[0];
		String urlPattern = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		if(!webURL.matches(urlPattern)) {
			System.err.println("Malformed URL.");
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
		driver.quit();
		dumpToFile(domainURL, explorer, maxLevel, wlc);
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
	
	private static String cleanUpURL(String href) {
		String temp = href.replace("https://", "");
		temp = temp.replace("http://", "");
		String[] tempArray = temp.split("\\?");
		temp = tempArray[0];
		temp = temp.replace("#", "");
		tempArray = temp.split(";");
		temp = tempArray[0];
		if(temp.length() != 0 && temp.charAt(temp.length()-1) == '/') {
			temp = temp.substring(0, temp.length() - 1);
		}
		System.out.println(temp);
		return temp;
	}
	
	private static void dumpToFile(String url, String explorer, int deep ,WebLevelComponent wlc) {
		try {
		PrintWriter out = new PrintWriter("output.txt");
		out.write(url + " " + explorer + " " + Integer.toString(deep) + "\n");
		out.write(wlc.toString());
		} catch(FileNotFoundException a) {
			System.err.println("The file does not exist");
		}
	}
}
