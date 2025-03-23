package market.jpminkabu;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", Config.CHROME_DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		
		MinkabuScraper scraper = new MinkabuScraper(driver);
		scraper.setPurchasePriceCondition(null, 29.0);
		scraper.setMarketCapCondition(50.0, null);
		scraper.setPbrCondition(null, 1.0);
		scraper.setDividendYieldCondition(3.0, null);
		
		
		ArrayList<StockData> stocks;
		// stocks = scraper.fetchScreenedStocks(1, "dividend_yield", "desc");
		// stocks = scraper.fetchScreenedStocks(1, "market_capitalization", "desc");
		stocks = scraper.fetchScreenedStocks(1, "pbr", "asc");
		
		for (StockData stock: stocks) {
			System.out.println(stock.toString());
		}
		
		DisplayTablePage(stocks);
	}
	
	public static void DisplayTablePage(ArrayList<StockData> stocks) {
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 800);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);
                
        // 登録銘柄テーブル
        JTable stockTable = createStockTable(stocks);
        
        JScrollPane StockScrollPane = new JScrollPane(stockTable);
        mainPanel.add(StockScrollPane, BorderLayout.NORTH);

        // フレームの表示
        frame.setVisible(true);
    }
	
	private static JTable createStockTable(ArrayList<StockData> stocks) {
        final String[] COLUMNS = {"銘柄コード", "銘柄名", "株価", "前日比", "目標株価", "方向性", "時価総額", "PBR", "配当利回り"};

        DefaultTableModel stockTableModel = new DefaultTableModel(COLUMNS, 0);
                
        for (StockData stock: stocks) {

        	String stockCode     = stock.getStockCode();
        	String companyName   = stock.getCompanyName();
			String close         = stock.getClose().toString() + "円";
			String prevRatio     = stock.getPrevRatio().toString() + "％";
			String targetPrice   = stock.getTargetPrice().toString() + "円";
			String barometer     = stock.getBarometer();
			String marketCap     = stock.getMarketCap();
			String pbr           = stock.getPBR().toString() + "倍";
			String dividendYield = stock.getDividendYield() + "％";
						
			String[] row = {stockCode, companyName, close, prevRatio, targetPrice, barometer, marketCap, pbr, dividendYield};
			stockTableModel.addRow(row);
		}
		
		return new JTable(stockTableModel);
	}
}
