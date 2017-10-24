package wellnr.com;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

public class CrawlerConfiguration extends Configuration {

    @NotEmpty
    private String template = "Hello, %s";

    @NotEmpty
    private String defaultName = "Stranger";

    @NotEmpty
    private String kafkaBootstrapServers = "kafka:9092";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @JsonProperty
    public String getKafkaBootstrapServers() {
        return kafkaBootstrapServers;
    }

    @JsonProperty
    public void setKafkaBootstrapServers(String kafkaBootstrapServers) {
        this.kafkaBootstrapServers = kafkaBootstrapServers;
    }
}
