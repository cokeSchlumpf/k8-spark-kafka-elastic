package wellnr.com;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import wellnr.com.health.TemplateHealthCheck;
import wellnr.com.resources.HelloWorldResource;
import wellnr.com.resources.KafkaProduceResource;

public class CrawlerApplication extends Application<CrawlerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CrawlerApplication().run(args);
    }

    @Override
    public String getName() {
        return "crawler";
    }

    @Override
    public void initialize(final Bootstrap<CrawlerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CrawlerConfiguration configuration,
                    final Environment environment) {
        environment.healthChecks().register("template", new TemplateHealthCheck(configuration.getTemplate()));

        environment.jersey().register(new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()));

        environment.jersey().register(new KafkaProduceResource(
            configuration.getKafkaBootstrapServers()));
    }

}
