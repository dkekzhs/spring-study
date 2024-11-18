package com.example.nettyrealtimeexcutionprice;

import com.example.nettyrealtimeexcutionprice.config.NettyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
public class NettyRealTimeExcutionPriceApplication {
    @Autowired
    private NettyConfig nettyConfig;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(NettyRealTimeExcutionPriceApplication.class, args);

    }
    @Bean
    public ApplicationRunner runNettyServer() {
        return args -> {
            nettyConfig.run();
        };
    }
}
