package wellnr.com.resources;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by michael on 19/10/2017.
 */
@Path("kafka-produce")
@Produces(MediaType.APPLICATION_JSON)
public class KafkaProduceResource {

    private static final Logger LOG = Logger.getLogger(KafkaProduceResource.class.getName());

    private final String kafkaBootstrapServers;

    public KafkaProduceResource(String kafkaBootstrapServers) {
        this.kafkaBootstrapServers = kafkaBootstrapServers;
    }

    @GET
    public String produceMessage(
            @QueryParam("message") Optional<String> message,
            @QueryParam("topic") Optional<String> topic) {

        String msg = message.orElse("Empty message");
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", kafkaBootstrapServers);
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<>(topic.orElse("test"), msg));
        producer.flush();

        return "Sent message " + msg;
    }

}
