package vn.nambb.mall.config.datasource;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Validated
public class DataSourceConfig {
    @NotEmpty(message = "Missing data source username")
    private String username;
    @NotEmpty(message = "Missing data source password")
    private String password;
    @NotEmpty(message = "Missing data source url")
    private String url;
    @NotEmpty(message = "Missing data source read url")
    private String driverClassName;
    @NotNull(message = "Missing hikari config")
    private HikariConfig hikari;

    @Data
    public static class HikariConfig {
        private int connectionTimeout = 30000;
        private int idleTimeout = 30000;
        private int maxLifetime = 2000000;
        private int maximumPoolSize = 500;
        private int minimumIdle = 5;
        private String poolName = "HikariPoolMall";
    }
}
