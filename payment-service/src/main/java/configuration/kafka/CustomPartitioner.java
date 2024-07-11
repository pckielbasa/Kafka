package configuration.kafka;

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
       int partition = (Utils.murmur2(keyBytes) & 0x7fffffff) % numPartitions;
       System.out.println("Partition number is " + partition);
       return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
