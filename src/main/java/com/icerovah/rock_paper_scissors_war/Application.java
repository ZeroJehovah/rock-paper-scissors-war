package com.icerovah.rock_paper_scissors_war;

import com.icerovah.rock_paper_scissors_war.service.InitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) {
        InitService.init();
        log.info("Application started");
    }

}
