package org.asuki.springboot;

import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static java.lang.System.out;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

public class ElasticsearchClientTest {

    /*
        Elasticsearch server is required
     */

//    @Test
    public void testNodeClient() throws ExecutionException, InterruptedException {

        try (Node node = nodeBuilder()
                .client(true)
                .settings(ImmutableSettings.settingsBuilder().put("http.enabled", false))
                .node();
             Client client = node.client()) {

            printStats(client);
        }
    }

//    @Test
    public void testTransportClient() throws ExecutionException, InterruptedException {

        try (Client client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300))) {

            printStats(client);
        }
    }

    private static void printStats(Client client) throws ExecutionException, InterruptedException {
        ClusterStatsResponse stats = client.admin().cluster().prepareClusterStats().execute().get();
        out.println(stats);
    }

}
