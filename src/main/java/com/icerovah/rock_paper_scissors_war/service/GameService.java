package com.icerovah.rock_paper_scissors_war.service;

import com.icerovah.rock_paper_scissors_war.timer.GameTimer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static com.icerovah.rock_paper_scissors_war.consts.Config.DELAY;
import static com.icerovah.rock_paper_scissors_war.consts.Schedule.SCHEDULE;

@Slf4j
public class GameService {

    public synchronized static void newGame() {
        ScheduleService.reNew();
        ContestantService.initContestants();
        CanvasService.draw();
    }

    public synchronized static void startGame() {
        try {
            SCHEDULE.scheduleAtFixedRate(new GameTimer(), 0, DELAY, TimeUnit.MICROSECONDS);
        } catch (Exception ignored) {
        }
    }

}
