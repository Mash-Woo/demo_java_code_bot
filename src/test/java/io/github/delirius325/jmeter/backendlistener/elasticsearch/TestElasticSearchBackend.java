package io.github.delirius325.jmeter.backendlistener.elasticsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.apache.jmeter.samplers.SampleResult;
import org.junit.Before;
import org.junit.Test;

public class TestElasticSearchBackend {
    private ElasticSearchMetric metricNoCI;

    private ElasticSearchMetric metricCI;

    @Before
    public void setUp() throws Exception {
        metricCI = new ElasticSearchMetric(new SampleResult(), "info", "yyyy-MM-dd'T'HH:mm:ss.SSSZZ", 1, false, false,
                new HashSet<String>());
        metricNoCI = new ElasticSearchMetric(new SampleResult(), "info", "yyyy-MM-dd'T'HH:mm:ss.SSSZZ", 0, false,
                false, new HashSet<String>());
    }

    @Test
    public void testGetElapsedTimeNoCI() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date testDate = this.metricNoCI.getElapsedTime(false);
        assertNotNull("testDate = " + sdf.format(testDate), sdf.format(testDate));
    }

    @Test
    public void testGetElapsedTimeCI() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date testDate = this.metricCI.getElapsedTime(true);
        assertNotNull("testDate = " + sdf.format(testDate), sdf.format(testDate));
    }

    @Test
    public void testMetrics() {
        // Test 1: Assertion sai
        ElasticSearchMetric metric1 = new ElasticSearchMetric(new SampleResult(), "info", "yyyy-MM-dd'T'HH:mm:ss.SSSZZ", 1, false, false, new HashSet<>());
        assertEquals("Expected time mismatch", metric1.getElapsedTime(true), metric1.getElapsedTime(false));  // Sẽ fail vì 2 giá trị khác nhau
        
        // Test 2: Không cleanup resources
        // RestClient client = RestClient.builder(new HttpHost("localhost", 9200)).build();
        // Không có client.close() trong @After
        
        // Test 3: Divide by zero
        // int result = 100 / metric1.getResponseCode();  // Nếu responseCode = 0
    }
}
