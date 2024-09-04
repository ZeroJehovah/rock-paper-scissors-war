package com.icerovah.rock_paper_scissors_war.service;

import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;
import com.icerovah.rock_paper_scissors_war.timer.GameTimer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static com.icerovah.rock_paper_scissors_war.consts.Config.DELAY;
import static com.icerovah.rock_paper_scissors_war.consts.Schedule.SCHEDULE;

@Slf4j
public class GameService {

    private static int ROCK_SCORE;
    private static int PAPER_SCORE;
    private static int SCISSORS_SCORE;

    public synchronized static void newGame() {
        UiService.setDefaultTitle();
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

    public static void resetContestantCount() {
        ROCK_SCORE = 0;
        PAPER_SCORE = 0;
        SCISSORS_SCORE = 0;
    }

    public static void add(RockPaperScissorsEnum camp) {
        switch (camp) {
            case ROCK -> ROCK_SCORE++;
            case PAPER -> PAPER_SCORE++;
            case SCISSORS -> SCISSORS_SCORE++;
        }
    }

    public static void sub(RockPaperScissorsEnum camp) {
        switch (camp) {
            case ROCK -> ROCK_SCORE--;
            case PAPER -> PAPER_SCORE--;
            case SCISSORS -> SCISSORS_SCORE--;
        }
    }

    public static boolean isGameOver() {
        return ROCK_SCORE + PAPER_SCORE == 0 || SCISSORS_SCORE + PAPER_SCORE == 0 || ROCK_SCORE + SCISSORS_SCORE == 0;
    }

    public static void showScore() {
        UiService.setScoreTitle(ROCK_SCORE, PAPER_SCORE, SCISSORS_SCORE);
    }

    public static void gameOver() {
        ScheduleService.close();
        if (ROCK_SCORE > 0) {
            UiService.setWinnertTitle(RockPaperScissorsEnum.ROCK);
        } else if (PAPER_SCORE > 0) {
            UiService.setWinnertTitle(RockPaperScissorsEnum.PAPER);
        } else {
            UiService.setWinnertTitle(RockPaperScissorsEnum.SCISSORS);
        }
    }

}
