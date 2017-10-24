package wellnr.com;

import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        AppConfiguration configuration = new AppConfiguration();

        SparkConf conf = new SparkConf().setAppName("KafkaWordCount");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(10));

        JavaDStream<Integer> messages =
                KafkaUtils.createDirectStream(
                        jssc,
                        String.class,
                        String.class,
                        StringDecoder.class,
                        StringDecoder.class,
                        configuration.kafka$getParams(),
                        configuration.kafka$getTopics())
                        .map(t -> t._2())
                        .map(s -> Integer.valueOf(s.split(" ").length));

        messages.print();
        jssc.start();
        jssc.awaitTermination();
    }
}
