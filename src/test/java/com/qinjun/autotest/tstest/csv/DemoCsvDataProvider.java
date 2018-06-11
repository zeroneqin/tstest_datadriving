package com.qinjun.autotest.tstest.csv;

import com.qinjun.autotest.tstest.datasource.DataSourceCsv;
import com.qinjun.autotest.tstest.datasource.IDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DemoCsvDataProvider {
    private static Logger logger = LoggerFactory.getLogger(DemoCsvDataProvider.class);

    @DataProvider(name = "DemoCsvDataProvider")
    public static Object[][] getData(ITestContext context) {
        Object[][] data = null;
        logger.info("----------[Start Read Data]----------");
        String csvPath = context.getCurrentXmlTest().getParameter("csv_path");

        IDataSource dataSource = new DataSourceCsv(csvPath);
        data = dataSource.getData();
        logger.info("----------[End Read Data]----------");
        return data;

    }
}
