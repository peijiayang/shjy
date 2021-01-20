package tech.binaryer.shjy.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"tech.binaryer.shjy"}, exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableScheduling
@MapperScan("tech.binaryer.shjy.biz.mapper")
public class ShjySpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShjySpringbootApplication.class, args);
    }

}
