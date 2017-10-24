package wellnr.com.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by michael on 19/10/2017.
 */
public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }

        return Result.healthy();
    }

}
