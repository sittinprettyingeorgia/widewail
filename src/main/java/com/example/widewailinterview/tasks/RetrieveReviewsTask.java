package com.example.widewailinterview.tasks;

import com.example.widewailinterview.service.ReviewService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RetrieveReviewsTask {

    private ReviewService service;

    public RetrieveReviewsTask(ReviewService service) {
        this.service = service;
    }

    @PostConstruct
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void runTask() {
        // This task will run only once at application startup
        try{
            service.importData();
        } catch(Exception e){
            log.error("Unable to import reviews", e);
        }
    }
}
