package com.secdn.secdnrapid;

import com.secdn.secdnrapid.datasources.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@MapperScan("com.secdn.secdnrapid.modules.*.mapper")
public class SecdnRapidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecdnRapidApplication.class, args);
    }

}
