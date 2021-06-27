package hu.webuni.spring.hr.sasa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {
    private Year percent = new Year();

    public Year getPercent() {
        return percent;
    }

    public void setPercent(Year percent) {
        this.percent = percent;
    }

    public static class Year {
        private Map<Double, Integer> years = new TreeMap<>();

        public Map<Double, Integer> getYears() {
            return years;
        }

        public void setYears(Map<String, String> years) {
            Map<Double, Integer> tmp = new TreeMap<>(Collections.reverseOrder());

            years.forEach((key, value) -> {
                tmp.put(Double.parseDouble(key), Integer.parseInt(value));
            });
            this.years = tmp;
        }
    }
}
