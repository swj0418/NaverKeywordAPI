package pro.jeong.naversearch.filereader;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NaverTrendFileReader {
    String trendFilePath = "";
    XSSFReader reader = null;
    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;

    LocalDate from = null;
    LocalDate to = null;

    public NaverTrendFileReader() {

    }

    public void setTrendFilePath(String trendFilePath) {
        this.trendFilePath = trendFilePath;
    }

    public ArrayList<Float> readFile() {
        ArrayList<Float> retArray = new ArrayList<>();
        File trendFile = new File(this.trendFilePath);
        try {
            InputStream in = new FileInputStream(trendFilePath);
            workbook = new XSSFWorkbook(in);
            sheet = workbook.getSheetAt(0);
        } catch(IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            for(int row = sheet.getFirstRowNum(); row < sheet.getLastRowNum(); row++) {

                if(row >= 7) {
                    XSSFRow xssfRow = sheet.getRow(row);
                    for(int col = xssfRow.getFirstCellNum(); col < xssfRow.getLastCellNum(); col++) {
                        if(col != 0) {
                            retArray.add(Float.parseFloat(xssfRow.getCell(col).getStringCellValue()));
                        }

                        if(row == 7) {
                            this.from = LocalDate.parse(xssfRow.getCell(0).getStringCellValue(), dateTimeFormatter);
                        }

                        if(row == sheet.getLastRowNum() - 1) {
                            this.to = LocalDate.parse(xssfRow.getCell(0).getStringCellValue(), dateTimeFormatter);
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        trendFile.delete();

        return retArray;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}
