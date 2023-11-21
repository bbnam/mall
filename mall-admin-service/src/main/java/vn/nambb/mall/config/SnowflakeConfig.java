package vn.nambb.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.nambb.mall.util.Snowflake;


@Configuration
public class SnowflakeConfig {
    @Bean(name = "snowflake")
    Snowflake snowflake() {
        return new Snowflake();
    }
}
