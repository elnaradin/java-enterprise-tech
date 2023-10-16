package com.example;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataGenerator {
    private static final Logger logger = LoggerFactory.getLogger(DataGenerator.class);
    private final Random rnd = new Random();
    private final SimpMessagingTemplate template;
    @Scheduled(fixedRate = 3000)
    public void newRecord (){
        DataRecord dataRecord = new DataRecord(System.currentTimeMillis(), 50.0 + rnd.nextInt(15));
        logger.info("new data {}", dataRecord);
        template.convertAndSend("/data_out/new_data", dataRecord);
    }
}
