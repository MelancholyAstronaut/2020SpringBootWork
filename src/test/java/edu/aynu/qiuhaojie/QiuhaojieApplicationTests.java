package edu.aynu.qiuhaojie;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QiuhaojieApplicationTests {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QiuhaojieApplicationTests.class);

    @Test
    void contextLoads() {
//        Integer.c
        log.error("error");
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
//        logger.fatle
    }

}
