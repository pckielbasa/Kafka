package org.orderservice.infrastructure.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
       int numPartitions = cluster.partitionsForTopic(topic).size();
        return (Utils.murmur2(keyBytes) & 0x7fffffff) % numPartitions;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
