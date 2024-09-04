package com.icerovah.rock_paper_scissors_war.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ButtonService {

    public static void startButtonEvent() {
        log.info("点击开始按钮");
        GameService.startGame();
    }

    public static void restartButtonEvent() {
        log.info("点击重新开始按钮");
        GameService.newGame();
    }

}
