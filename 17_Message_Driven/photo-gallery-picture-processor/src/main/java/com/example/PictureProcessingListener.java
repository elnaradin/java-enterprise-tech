package com.example;

import com.example.service.PictureService;
import com.example.service.SavedPictureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "picture.queue")
public class PictureProcessingListener {

    private final PictureService pictureService;

    @RabbitHandler
    public void onNewPicture(SavedPictureDto savedPictureDto) {
        pictureService.processPicture(savedPictureDto);
    }
}
