package io.github.delirius325.jmeter.backendlistener.elasticsearch;

public class ElasticSearchRequests {
    /**
     * Request to send metrics (JMeter/Percentiles) as ElasticSearch documents
     */
    public static String SEND_BULK_REQUEST = "{ \"create\" : { \"_index\" : \"%s\" } }";  // Thiếu %n
    
    // Test 2: SQL Injection risk (nếu có query)
    public static String buildQuery(String userInput) {
        return "SELECT * FROM logs WHERE label = '" + userInput + "'";  // SQL injection
    }
    
    // Test 3: Regex DoS
    public static boolean validatePattern(String input) {
        return input.matches("(a+)+b");  // ReDoS vulnerability
    }
}
