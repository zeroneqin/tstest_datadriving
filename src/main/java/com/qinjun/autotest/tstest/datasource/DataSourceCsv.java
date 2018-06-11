package com.qinjun.autotest.tstest.datasource;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.util.List;

public class DataSourceCsv implements IDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceCsv.class);
    String csvPath;

    public DataSourceCsv(String csvPath) {
        this.csvPath = csvPath;
    }

    public Object[][] getData() {
        String[][] data=null;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvPath));
            List<String[]> csvContent = csvReader.readAll();
            int x = csvContent.size();
            int y = csvContent.get(0).length;
            data = new String[x][y];
            for (int i=0;i<x;i++) {
                for (int j=0;j<y;j++) {
                    data[i][j] = csvContent.get(i)[j];
                }
            }
            csvReader.close();
        } catch(Exception e) {
            logger.error("Get exception when read csv:"+e);
        }
        return data;
    }

}
