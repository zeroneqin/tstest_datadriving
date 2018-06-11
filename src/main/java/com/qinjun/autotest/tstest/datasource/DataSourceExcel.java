package com.qinjun.autotest.tstest.datasource;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

public class DataSourceExcel implements IDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceExcel.class);
    String excelPath;
    String excelSheet;

    public DataSourceExcel(String excelPath, String excelSheet) {
        this.excelPath=excelPath;
        this.excelSheet = excelSheet;
    }


    public Object[][] getData() {
        Object[][] data = null;
        try {
            InputStream fs = new FileInputStream(excelPath);
            Workbook workbook = WorkbookFactory.create(fs);
            Sheet sheet = workbook.getSheet(excelSheet);
            data = readSheet(sheet);
        } catch(Exception e) {
            logger.error("Get exception when read excel:"+e);
        }
        return data;
    }

    private Object[][] readSheet(Sheet sheet) {
        Object[][] data=null;
        int rowNum = sheet.getLastRowNum();
        data = new Object[rowNum][];

        for (int i=0;i<rowNum;i++) {
            Row row = sheet.getRow(i);
            int colNum = ((Row) row).getLastCellNum();
            data[i] = new Object[colNum];
            for (int j=0;j<colNum;j++) {
                Cell cell = row.getCell(j);
                String value = getValue(cell);
                data[i][j] = value;
            }
        }
        return data;
    }

    private String getValue(Cell cell) {
        String value="";
        final DecimalFormat df = new DecimalFormat("#");
        switch(cell.getCellTypeEnum()) {
            case STRING:
                value = (cell==null ? "": cell.getStringCellValue());
                break;
            case NUMERIC:
                Double dvalue = (cell==null?0:cell.getNumericCellValue());
                value = String.valueOf(dvalue);
                if (value.matches("\\d+.[0]*")) {
                    int endIndex = value.indexOf(".");
                    value = value.substring(0,endIndex);
                }
                if (value.matches("^((-?\\d+.?\\d*)[Ee]{1}(\\d+))$")) {
                    value = df.format(dvalue);
                }
                break;
            case BOOLEAN:
                Boolean bool = (cell==null? false:cell.getBooleanCellValue());
                value=bool.toString();
                break;
            case FORMULA:
                value=(cell==null?"":cell.getCellFormula());
                break;
            case ERROR:
                value =(cell==null?"":Byte.toString(cell.getErrorCellValue()));
                break;
            case BLANK:
                value="";
                break;
        }
        return value;
    }
}
