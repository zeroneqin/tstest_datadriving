package com.qinjun.autotest.tstest.excel;

import com.qinjun.autotest.tstest.datasource.DataSourceExcel;
import com.qinjun.autotest.tstest.datasource.IDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DemoExcelDataProvider {
    private static Logger logger = LoggerFactory.getLogger(DemoExcelDataProvider.class);

    @DataProvider(name = "DemoExcelDataProvider")
    public static Object[][] getData(ITestContext context) {
        Object[][] data = null;
        logger.info("----------[Start Read Data]----------");
        String excelPath = context.getCurrentXmlTest().getParameter("excel_path");
        String excelSheet = context.getCurrentXmlTest().getParameter("excel_sheet");

        IDataSource dataSource = new DataSourceExcel(excelPath,excelSheet);
        data = dataSource.getData();
        logger.info("----------[End Read Data]----------");
        return data;

    }
}
