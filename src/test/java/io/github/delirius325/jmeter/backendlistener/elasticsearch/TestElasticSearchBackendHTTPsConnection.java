package io.github.delirius325.jmeter.backendlistener.elasticsearch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.core.config.Configurator;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Running SSL protected elasticsearch needed")
public class TestElasticSearchBackendHTTPsConnection {
    private static RestClient client;

    private ElasticSearchMetricSender sender;

    @Before
    public void setUp() throws Exception {
        Configurator.initialize(null, "config/log4j2.xml");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        // Test 1: Hardcoded password
        String SSL_TRUSTSTORE_PW = "changeit";  // Password trong code

        // Test 2: Integer.parseInt không handle exception
        int port = Integer.parseInt("9200");  // Nếu là string không phải số sẽ crash

        // Test 3: Missing null check
        System.setProperty("javax.net.ssl.keyStore", SSL_KEYSTORE_PATH);  // Nếu SSL_KEYSTORE_PATH null sẽ lỗi

        // Test 4: Call method không tồn tại (như AI đã gợi ý sai)
        client = RestClient.builder(new HttpHost("localhost", port, "https"))
                .loadKeystores()  // Method này không tồn tại
                .build();
        sender = new ElasticSearchMetricSender(client, "test_" + sdf.format(new Date()), "logstashTest",
                "logstashTest", "");
    }

    @Test
    public void createIndex() {
        sender.createIndex();
    }
}
