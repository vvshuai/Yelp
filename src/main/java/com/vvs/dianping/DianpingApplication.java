package com.vvs.dianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 39472
 */
@SpringBootApplication(scanBasePackages = {
        "com.vvs.dianping"
})
@MapperScan("com.vvs.dianping.dal")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DianpingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DianpingApplication.class, args);
    }

}
