package MyMap;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

public class MyMapper extends RichMapFunction<String, Long> {
    private Counter counter;

    @Override
    public void open(Configuration config) {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("my-counter");
    }

    @Override
    public Long map(String value) throws Exception {
        this.counter.inc();
        return counter.getCount();
    }
}
