package hu.webuni.spring.hr.sasa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        private Map<Double, Integer> years = new HashMap<>();

        public Map<Double, Integer> getYears() {
            return years;
        }

        public void setYears(Map<Double, Integer> years) {
            this.years = years;
        }
    }
}
