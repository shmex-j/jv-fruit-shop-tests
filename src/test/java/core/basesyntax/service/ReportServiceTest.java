package core.basesyntax.service;

import core.basesyntax.service.impl.FruitReportService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReportServiceTest {
    private static final String FIRST_FRUIT = "fruit1";

    private final ReportService reportService = new FruitReportService();
    private Map<String, Integer> testData;

    @Before
    public void setUp() {
        testData = new HashMap<>();
        testData.put(FIRST_FRUIT, 100);
    }

    @Test
    public void calculateReport_validData_validReport_ok() {
        String expected = "fruit,quantity" + System.lineSeparator()
                + FIRST_FRUIT + ",100";
        String actual = reportService.calculateReport(testData);
        assertEquals("Test failed! Expected report for data: "
                + testData + " -> " + expected
                + ", but was: " + actual,
                expected, actual);
    }

    @Test
    public void calculateReport_emptyData_onlyHeader() {
        String expected = "fruit,quantity";
        testData = Collections.emptyMap();
        String actual = reportService.calculateReport(testData);
        assertEquals("Test failed! Expected report for data: "
                        + testData + " -> " + expected
                        + ", but was: " + actual,
                expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void calculateReport_null_notOk() {
        reportService.calculateReport(null);
    }

}