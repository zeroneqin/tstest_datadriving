package com.qinjun.autotest.tstest.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoCsvCase {
    private static final Logger logger = LoggerFactory.getLogger(DemoCsvCase.class);

    @BeforeClass
    public void beforeTest() {
        logger.info("==========[Start Test]==========");
    }

    @Test(dataProvider = "DemoCsvDataProvider", dataProviderClass = DemoCsvDataProvider.class)
    public void test(String name, String value) {

    }

    @AfterClass
    public void afterTest() {
        logger.info("==========[End Test]==========");
    }
}
