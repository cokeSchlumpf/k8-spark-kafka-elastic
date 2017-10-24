package wellnr.com;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import org.apache.commons.collections.ListUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by michael on 23/10/2017.
 */
public class AppConfiguration {

    final Config conf;

    {
        conf = ConfigFactory.load();
        for (Map.Entry<String, ConfigValue> entry : conf.entrySet()) {
            if (entry.getKey().startsWith("kafka")) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        };
    }

    public Set<String> kafka$getBrokers() {
        return Sets.newHashSet(conf.getStringList("kafka.brokers"));
    }

    public Set<String> kafka$getTopics() {
        return Sets.newHashSet(conf.getStringList("kafka.topics"));
    }

    public Map<String, String> kafka$getParams() {
        Map<String,String> params = Maps.newHashMap();
        params.put("metadata.broker.list", String.join(",", kafka$getBrokers()));
        return params;
    }

}
