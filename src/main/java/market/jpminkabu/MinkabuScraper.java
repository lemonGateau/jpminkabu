package market.jpminkabu;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MinkabuScraper {
	private final WebDriver driver;
	private static final String endPoint = "https://minkabu.jp/";
	private Double minPurchasePrice;
	private Double maxPurchasePrice;
	private Double minMarketCap;
	private Double maxMarketCap;
	private Double minPBR;
	private Double maxPBR;
	private Double minDividendYield;
	private Double maxDividendYield;

	public MinkabuScraper(WebDriver driver) {
		this.driver = driver;
	}
		
	public void setPurchasePriceCondition(Double min, Double max) {
		// 万円
		this.minPurchasePrice = min;
		this.maxPurchasePrice = max;
	}

	public void setMarketCapCondition(Double min, Double max) {
		// 億円
		this.minMarketCap = min;
		this.maxMarketCap = max;
	}
	
	public void setPbrCondition(Double min, Double max) {
		// 倍
		this.minPBR = min;
		this.maxPBR = max;
	}
	
	public void setDividendYieldCondition(Double min, Double max) {
		// ％
		this.minDividendYield = min;
		this.maxDividendYield = max;
	}
	
	
	public ArrayList<StockData> fetchScreenedStocks(Integer pageNum, String sortKey, String sortBy) {
		String url = generateUrl(pageNum, sortKey, sortBy);
		ArrayList<StockData> stocks = new ArrayList<StockData>();
		
        try {
            driver.get(url);
            ((ChromeDriver) driver).executeScript("document.body.style.zoom='75%'");
            Thread.sleep(2);
            
            WebElement zoomOutButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/main/div[1]/div[2]/div/section[2]/div[1]/div/div[5]/div[1]/div/button"));
            zoomOutButton.click();
            			
			WebElement element = driver.findElement(By.cssSelector("#js_stockSearch_fs_wrapper > div.py-4 > div > div.mt-2.bg-white.p-4.rounded-lg"));
			String[] values = element.getText().replace(",", "").replace("円", "").replace("%", "").replace("倍", "").split("\n");
						
			final int HEADER = 13, FOOTER = values.length - 2;
			for (int i=HEADER; i<FOOTER; i+=13) {
				StockData stock = new StockData(values[i+1].substring(0, 4));
				
				stock.setCompanyName(values[i+2]);
				stock.setClose(Double.parseDouble(values[i+3]));
				stock.setPrevRatio(Double.parseDouble(values[i+5]));
				stock.setTargerPrice(Double.parseDouble(values[i+6]));
				stock.setBarometer(values[i+7]);
				stock.setMarketCap(values[i+8]);
				stock.setPBR(Double.parseDouble(values[i+9]));
				stock.setDividendYield(Double.parseDouble(values[i+10]));
				
				stocks.add(stock);
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	driver.close();
        }
        
        return stocks;

	}
	
	private String generateUrl(Integer pageNum, String sortKey, String sortBy) {
		String url = endPoint + "/stock/search?view=result&page=" + pageNum.toString() + "&sort_key=" + sortKey + "&order=" + sortBy;
		
		if (minPurchasePrice == null) {
			url += "&minimum_purchase_price[0]=min";
		} else {
			url += "&minimum_purchase_price[0]=" + minPurchasePrice.toString();
		}
		
		if (maxPurchasePrice == null) {
			url += "&maximum_purchase_price[1]=max";
		} else {
			url += "&maximum_purchase_price[1]=" + maxPurchasePrice.toString();
		}
		
		if (minMarketCap == null) {
			url += "&market_capitalization[0]=min";
		} else {
			url += "&market_capitalization[0]=" + minMarketCap.toString();
		}
		
		if (maxMarketCap == null) {
			url += "&market_capitalization[1]=max";
		} else {
			url += "&market_capitalization[1]=" + maxMarketCap.toString();
		}
		
		if (minPBR == null) {
			url += "&pbr[0]=min";
		} else {
			url += "&pbr[0]=" + minPBR.toString();
		}
		
		if (maxPBR == null) {
			url += "&pbr[1]=max";
		} else {
			url += "&pbr[1]=" + maxPBR.toString();
		}
		
		if (minDividendYield == null) {
			url += "&dividend_yield[0]=min";
		} else {
			url += "&dividend_yield[0]=" + minDividendYield.toString();
		}
		
		if (maxDividendYield == null) {
			url += "&dividend_yield[1]=max";
		} else {
			url += "&dividend_yield[1]=" + maxDividendYield.toString();
		}
		
		return url;
	}
}
