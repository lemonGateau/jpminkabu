package market.jpminkabu;

public class StockData {
	private String stockCode;
	private String companyName;

	private Double close;
	private Double prevRatio;
	private Double targetPrice;
	private String barometer;
	
	// Fundamentals
	private String marketCap;
	private Double per;
	private Double pbr;
	private Double dividendYield;
	private Double dividendRate;
	
	public StockData(String stockCode) {
		this.stockCode = stockCode;
		this.companyName = null;
		this.close = null;
		this.prevRatio = null;
		this.targetPrice = null;
		this.barometer = null;
		this.marketCap = null;
		this.per  = null;
		this.pbr  = null;
		this.dividendYield = null;
		this.dividendRate  = null;
	}

	// setter
	public void setCompanyName(String name) {
		this.companyName = name;
	}
	
	public void setClose(Double close) {
		this.close = close;
	}
	
	public void setPrevRatio(Double ratio) {
		this.prevRatio = ratio;
	}
	
	public void setTargerPrice(Double targetPrice) {
		this.targetPrice = targetPrice;
	}
	
	public void setBarometer(String barometer) {
		this.barometer = barometer;
	}
	
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	
	public void setPER(Double per) {
		this.per = per;
	}
	
	public void setPBR(Double pbr) {
		this.pbr = pbr;
	}
	
	public void setDividendYield(Double dividendYield) {
		this.dividendYield = dividendYield;
	}
	
	public void setDividendRate(Double dividendRate) {
		this.dividendRate = dividendRate;
	}
	
	// getter
	public String getStockCode() {
		return stockCode;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public Double getClose() {
		return close;
	}
	
	public Double getPrevRatio() {
		return prevRatio;
	}
	
	public Double getTargetPrice() {
		return targetPrice;
	}
	
	public String getBarometer() {
		return barometer;
	}
	
	public String getMarketCap() {
		return marketCap;
	}
	
	public Double getPER() {
		return per;
	}
	
	public Double getPBR() {
		return pbr;
	}
	
	public Double getDividendYield() {
		return dividendYield;
	}
	
	public Double getDividendRate() {
		return dividendRate;
	}
	
	@Override
	public String toString() {
	    return  "stockCode=" + stockCode +
	            ", companyName=" + companyName +
	            ", close=" + close.toString() + "円" +
	            ", prevRatio=" + prevRatio.toString() + "%" + 
	            ", targetPrice=" + targetPrice.toString() + "円" +
	            ", barometer=" + barometer +
	            ", marketCap=" + marketCap +
	            ", pbr=" + pbr.toString() +
	            ", dividendYield=" + dividendYield.toString() +
	            '}';
	}
}
