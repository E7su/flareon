import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

import MyMap.MyMapper;

public class Streaming {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "test");
        DataStreamSource<String> stream = env
                .addSource(new FlinkKafkaConsumer010<String>("fast-messages", new SimpleStringSchema(), properties))
                .map(new MyMapper());
//        DataStream<Integer> mnstream = stream.timeWindowAll(Time.minutes(1)).fold(0, new FoldFunction<String, Integer>() {
//
//            public Integer fold(Integer integer, String o) throws Exception {
//                return (integer + 1);
//            }
//        });
//        mnstream.print();
        stream.print();
        env.execute();
    }
}
