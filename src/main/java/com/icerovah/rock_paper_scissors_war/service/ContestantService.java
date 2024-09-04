package com.icerovah.rock_paper_scissors_war.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.icerovah.rock_paper_scissors_war.component.Contestant;
import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;
import lombok.extern.slf4j.Slf4j;

import static com.icerovah.rock_paper_scissors_war.consts.Components.CONTESTANTS;
import static com.icerovah.rock_paper_scissors_war.consts.Config.*;

@Slf4j
public class ContestantService {

    public static void move() {
        // 修正方向
        correctionDirection();

        for (Contestant contestant : CONTESTANTS) {
            contestant.backUpCoordinates();
            contestant.move();
        }

        // 碰撞处理
        handleColliding();
    }

    /**
     * 修正方向
     */
    private static void correctionDirection() {
    }

    /**
     * 碰撞处理
     */
    private static void handleColliding() {
        for (int i = 0; i < CONTESTANT_COUNT; i++) {
            Contestant contestant = CONTESTANTS.get(i);
            if (handleMapColliding(contestant) || i == CONTESTANT_COUNT - 1) {
                continue;
            }
            for (int j = i + 1; j < CONTESTANT_COUNT; j++) {
                Contestant anotherContestant = CONTESTANTS.get(j);
                handleContestantsColliding(contestant, anotherContestant);
            }
        }
    }

    /**
     * 地图边界碰撞处理
     */
    private static boolean handleMapColliding(Contestant contestant) {
        boolean isColliding = Boolean.FALSE;
        double direction = contestant.getDirection();

        int positionX = contestant.getPositionX();
        int positionY = contestant.getPositionY();
        if (positionX <= 0 || positionX >= MAP_WIDTH) {
            isColliding = Boolean.TRUE;
            direction = Math.PI - direction;
        }
        if (positionY <= 0 || positionY >= MAP_HEIGHT) {
            isColliding = Boolean.TRUE;
            direction = -direction;
        }

        if (isColliding) {
            contestant.setDirection(direction);
            contestant.move();
        }

        return isColliding;
    }

    /**
     * 参赛者间的碰撞处理
     */
    private static void handleContestantsColliding(Contestant contestant, Contestant anotherContestant) {
        int contestantPositionX = contestant.getPositionX();
        int anotherContestantPositionX = anotherContestant.getPositionX();
        if (contestantPositionX >= anotherContestantPositionX + CONTESTANT_SIZE) {
            return;
        }
        if (contestantPositionX + CONTESTANT_SIZE <= anotherContestantPositionX) {
            return;
        }
        int contestantPositionY = contestant.getPositionY();
        int anotherContestantPositionY = anotherContestant.getPositionY();
        if (contestantPositionY >= anotherContestantPositionY + CONTESTANT_SIZE) {
            return;
        }
        if (contestantPositionY + CONTESTANT_SIZE <= anotherContestantPositionY) {
            return;
        }

        // 发生了碰撞

        // 战斗
        RockPaperScissorsEnum battleResult = RockPaperScissorsEnum.battle(contestant.getType(), anotherContestant.getType());
        contestant.setType(battleResult);
        anotherContestant.setType(battleResult);

        // 获取新方向
        double contestantDirection = getContestantDirection(contestant, anotherContestant);

        contestant.setDirection(contestantDirection);
        anotherContestant.setDirection(contestantDirection + Math.PI);

        contestant.move();
        anotherContestant.move();
    }

    /**
     * 获取当前参赛选手应该移动的方向
     */
    private static double getContestantDirection(Contestant contestant, Contestant anotherContestant) {
        double contestantLastCoordinateX = contestant.getLastCoordinateX();
        double contestantLastCoordinateY = contestant.getLastCoordinateY();
        double anotherContestantLastCoordinateX = anotherContestant.getLastCoordinateX();
        double anotherContestantLastCoordinateY = anotherContestant.getLastCoordinateY();

        // 从参赛选手2看参赛选手1的方向，即参赛选手1远离参赛选手2的方向
        return Math.atan2(contestantLastCoordinateY - anotherContestantLastCoordinateY, contestantLastCoordinateX - anotherContestantLastCoordinateX);
    }

    public static void initContestants() {
        CONTESTANTS = CollUtil.newArrayList();
        for (int i = 0; i < CONTESTANT_COUNT; i++) {
            Contestant contestant = new Contestant();
            contestant.setType(RockPaperScissorsEnum.random());
            contestant.setCoordinateX(RandomUtil.randomInt(MAP_WIDTH));
            contestant.setCoordinateY(RandomUtil.randomInt(MAP_HEIGHT));
            contestant.setVelocity(CONTESTANT_VELOCITY);
            contestant.setDirection(RandomUtil.randomDouble(-Math.PI, Math.PI));
            CONTESTANTS.add(contestant);
        }
    }

    public static boolean isGameOver() {
        return CONTESTANTS.stream().map(Contestant::getType).distinct().count() == 1;
    }

}
