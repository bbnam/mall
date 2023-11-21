package vn.nambb.mall.common.contant;

public class DataSourceConstant {
    private DataSourceConstant() {}
    public static final String BASE_PACKAGES = "vn.nambb.mall";
    public static final String HIBERNATE_PHYSICAL_NAMING_STRATEGY_VALUE = "vn.nambb.mall.config.hibernate.CustomPhysicalNamingStrategy";
    public static final String HIBERNATE_PHYSICAL_NAMING_STRATEGY = "hibernate.physical_naming_strategy";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_DIALECT_VALUE = "org.hibernate.dialect.MySQL8Dialect";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT = "spring.jpa.properties.hibernate.dialect";
    public static final String SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT_VALUE = "org.hibernate.dialect.MySQL8Dialect";
}
