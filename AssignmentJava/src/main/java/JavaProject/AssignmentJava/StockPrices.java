package JavaProject.AssignmentJava;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
public class StockPrices {
 
	private static Map<String, Double> readExcel(String fileP) throws IOException {
    	Map<String, Double> excelData = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(new File(fileP));
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
 
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            String stockName = row.getCell(0).getStringCellValue();
            double stockPrice = row.getCell(1).getNumericCellValue();
            excelData.put(stockName, stockPrice);
        }
 
        workbook.close();
        fileInputStream.close();
 
        return excelData;
	}
	public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
 
        WebDriver driver = new ChromeDriver();
 
        // Read data from the Excel file and store it in HashMap1
        Map<String, Double> excelData = readExcel("./Excelfile/StockDetails.xlsx");
 
        driver.get("https://money.rediff.com/losers/bse/daily/groupall");
 
        // Store the data from the webpage in HashMap2
        Map<String, Double> webData = new HashMap<String, Double>();
        WebElement table = driver.findElement(By.xpath("//table[@class='dataTable']"));
        for (WebElement row : table.findElements(By.xpath(".//tr[position()>1]"))) {
            String stockName = row.findElement(By.xpath(".//td[1]")).getText();
            double stockPrice = Double.parseDouble(row.findElement(By.xpath(".//td[4]")).getText().replace(",", ""));
            webData.put(stockName, stockPrice);
        }
 
       //  Compare the values stored in the two HashMaps
        for (String stockName : excelData.keySet()) {
            if (webData.containsKey(stockName)) {
                double excelPrice = excelData.get(stockName);
                double webPrice = webData.get(stockName);
                if (excelPrice == webPrice) {
                	System.out.println("Passed" + stockName);
                } else {
                    System.out.println("Failed" + stockName);
                }
            } else {
                System.out.println("Stock details not found: " + stockName);
            }
        }
 
        driver.quit();
    }
 
}