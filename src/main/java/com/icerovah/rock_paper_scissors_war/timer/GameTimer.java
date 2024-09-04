package com.icerovah.rock_paper_scissors_war.timer;

import com.icerovah.rock_paper_scissors_war.service.CanvasService;
import com.icerovah.rock_paper_scissors_war.service.ContestantService;
import com.icerovah.rock_paper_scissors_war.service.GameService;

public class GameTimer implements Runnable {

    @Override
    public void run() {
        ContestantService.move();
        CanvasService.draw();

        if (GameService.isGameOver()) {
            GameService.gameOver();
        }
    }

}
