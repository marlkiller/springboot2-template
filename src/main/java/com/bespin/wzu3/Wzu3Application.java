package com.bespin.wzu3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.bespin.wzu3.mapper")
public class Wzu3Application {

    public static void main (String[] args) {
        SpringApplication.run(Wzu3Application.class, args);
    }
    // @Primary
    // @Bean("db1SqlSessionFactory")
    // public SqlSessionFactory db1SqlSessionFactory(DataSource dataSource) throws Exception {
    //     /**
    //      * 使用 mybatis plus 配置
    //      */
    //     MybatisSqlSessionFactoryBean b1 = new MybatisSqlSessionFactoryBean();
    //     System.out.println("dataSourceLyz"+dataSource.toString());
    //     b1.setDataSource(dataSource);
    //     b1.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping-plus/*.xml"));
    //     return b1.getObject();
    // }
}
