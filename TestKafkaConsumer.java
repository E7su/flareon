import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.disableOperatorChaining();


        Properties kafka_props = new Properties();
        kafka_props.setProperty("bootstrap.servers", "localhost:9092");
        kafka_props.setProperty("zookeeper.connect", "localhost:2181");
        kafka_props.setProperty("group.id", "awesome_test");
        kafka_props.setProperty("auto.commit.interval.ms", "1");
        kafka_props.setProperty("enable.auto.commit", "true");
        kafka_props.setProperty("auto.offset.reset", "earliest");
        kafka_props.setProperty("retries", "20");
        kafka_props.setProperty("acks", "all");
        kafka_props.setProperty("offsets.storage", "zookeeper");

        FlinkKafkaConsumer010 events_consumer =
                new FlinkKafkaConsumer010<>("test_e7su", new SimpleStringSchema(), kafka_props);

        DataStreamSource x = env.addSource(events_consumer);
        x.print();
        env.execute();
    }
}
