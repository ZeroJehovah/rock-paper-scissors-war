package com.icerovah.rock_paper_scissors_war.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.icerovah.rock_paper_scissors_war.component.Contestant;
import com.icerovah.rock_paper_scissors_war.enums.ActionStrategyEnum;
import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;
import lombok.extern.slf4j.Slf4j;

import static com.icerovah.rock_paper_scissors_war.consts.UiConstants.CONTESTANTS;
import static com.icerovah.rock_paper_scissors_war.consts.Config.*;

@Slf4j
public class ContestantService {

    public static void move() {
        // 备份坐标
        backUpCoordinates();

        // 根据配置更新加速度方向
        updateAccelerationDirection();

        // 修正速度和方向
        updateDirectionAndVelocity();

        // 尝试移动
        doMove();

        // 碰撞处理(如果碰撞则更新方向重新移动)
        boolean ifBattled = handleColliding();

        if (ifBattled) {
            showScore();
        }
    }

    /**
     * 备份坐标
     */
    private static void backUpCoordinates() {
        CONTESTANTS.forEach(Contestant::backUpCoordinates);
    }

    /**
     * 根据配置更新加速度方向
     */
    private static void updateAccelerationDirection() {
        CONTESTANTS.forEach(ContestantService::updateAccelerationDirection);
    }

    private static void updateAccelerationDirection(Contestant oneContestant) {
        RockPaperScissorsEnum contestantCamp = oneContestant.getCamp();
        ActionStrategyEnum actionStrategy = ACTION_STRATEGY.get(contestantCamp);
        if (actionStrategy == ActionStrategyEnum.RECKLESS) {
            oneContestant.setAccelerationDirection(oneContestant.getDirection());
            return;
        }

        RockPaperScissorsEnum consumeCamp = CampService.getConsumeCamp(contestantCamp);
        RockPaperScissorsEnum beConsumedCamp = CampService.getBeConsumedCamp(contestantCamp);

        Contestant nearestConsumeContestant = null;
        double nearestConsumeDistance = Double.MAX_VALUE;
        Contestant nearestBeConsumedContestant = null;
        double nearestBeConsumedDistance = Double.MAX_VALUE;

        for (Contestant anotherContestant : CONTESTANTS) {
            RockPaperScissorsEnum anotherContestantCamp = anotherContestant.getCamp();
            if (contestantCamp == anotherContestantCamp) {
                continue;
            }

            double distance = Math.pow(anotherContestant.getPositionX() - oneContestant.getPositionX(), 2) + Math.pow(anotherContestant.getPositionY() - oneContestant.getPositionY(), 2);
            if (anotherContestantCamp == consumeCamp && distance < nearestConsumeDistance) {
                nearestConsumeContestant = anotherContestant;
                nearestConsumeDistance = distance;
            } else if (anotherContestantCamp == beConsumedCamp && distance < nearestBeConsumedDistance) {
                nearestBeConsumedContestant = anotherContestant;
                nearestBeConsumedDistance = distance;
            }
        }

        if (nearestConsumeContestant == null) {
            // 没有可消灭的对手，选择防守
            oneContestant.setAccelerationDirection(getDirectionFromOneToAnother(nearestBeConsumedContestant, oneContestant));
        } else if (nearestBeConsumedContestant == null) {
            // 没有天敌，选择进攻
            oneContestant.setAccelerationDirection(getDirectionFromOneToAnother(oneContestant, nearestConsumeContestant));
        } else {
            switch (actionStrategy) {
                case OFFENSIVE -> oneContestant.setAccelerationDirection(getDirectionFromOneToAnother(oneContestant, nearestConsumeContestant));
                case DEFENSIVE -> oneContestant.setAccelerationDirection(getDirectionFromOneToAnother(nearestBeConsumedContestant, oneContestant));
                case INTELLIGENT -> {
                    if (nearestConsumeDistance < nearestBeConsumedDistance) {
                        oneContestant.setAccelerationDirection(getDirectionFromOneToAnother(oneContestant, nearestConsumeContestant));
                    } else {
                        oneContestant.setAccelerationDirection(getDirectionFromOneToAnother(nearestBeConsumedContestant, oneContestant));
                    }
                }
            }
        }
    }

    /**
     * 修正速度和方向
     */
    private static void updateDirectionAndVelocity() {
        CONTESTANTS.forEach(Contestant::updateDirectionAndVelocity);
    }

    /**
     * 尝试移动
     */
    private static void doMove() {
        CONTESTANTS.forEach(Contestant::move);
    }

    /**
     * 碰撞处理(如果碰撞则更新方向重新移动)
     */
    private static boolean handleColliding() {
        boolean ifBattled = Boolean.FALSE;

        for (int i = 0; i < CONTESTANTS.size(); i++) {
            Contestant contestant = CONTESTANTS.get(i);
            if (handleMapColliding(contestant) || i == CONTESTANTS.size() - 1) {
                continue;
            }
            for (int j = i + 1; j < CONTESTANTS.size(); j++) {
                Contestant anotherContestant = CONTESTANTS.get(j);
                if (handleContestantsColliding(contestant, anotherContestant)) {
                    ifBattled = Boolean.TRUE;
                }
            }
        }

        return ifBattled;
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
            contestant.lockCoordinate();
        }

        return isColliding;
    }

    /**
     * 参赛者间的碰撞处理
     */
    private static boolean handleContestantsColliding(Contestant oneContestant, Contestant anotherContestant) {
        if (!ifColliding(oneContestant, anotherContestant)) {
            // 未发生碰撞
            return Boolean.FALSE;
        }

        // 战斗
        boolean ifBattled = CampService.battle(oneContestant, anotherContestant);

        // 处理碰撞对方向的改变
        handleDirectionByColliding(oneContestant, anotherContestant);

        return ifBattled;
    }

    /**
     * 处理碰撞对方向的改变
     */
    private static void handleDirectionByColliding(Contestant oneContestant, Contestant anotherContestant) {
        double directionFromOneToAnother = getDirectionFromOneToAnother(oneContestant, anotherContestant);

        oneContestant.setDirection(directionFromOneToAnother + Math.PI);
        oneContestant.move();

        if (!anotherContestant.isLockCoordinate()) {
            anotherContestant.setDirection(directionFromOneToAnother);
            anotherContestant.move();
        }
    }

    private static double getDirectionFromOneToAnother(Contestant oneContestant, Contestant anotherContestant) {
        double oneCoordinateX = oneContestant.getLastCoordinateX();
        double oneCoordinateY = oneContestant.getLastCoordinateY();
        double anotherCoordinateX = anotherContestant.getLastCoordinateX();
        double anotherCoordinateY = anotherContestant.getLastCoordinateY();

        return Math.atan2(anotherCoordinateY - oneCoordinateY, anotherCoordinateX - oneCoordinateX);
    }

    public static void initContestants() {
        log.info("初始化参赛选手");
        GameService.resetContestantCount();
        CONTESTANTS = CollUtil.newArrayList();
        for (int i = 0; i < CONTESTANT_COUNT; i += 3) {
            for (RockPaperScissorsEnum camp : RockPaperScissorsEnum.values()) {
                CONTESTANTS.add(initContestants(camp));
                GameService.add(camp);
            }
        }
    }

    private static Contestant initContestants(RockPaperScissorsEnum camp) {
        Contestant contestant = new Contestant();
        contestant.setCoordinateX(RandomUtil.randomInt(MAP_WIDTH));
        contestant.setCoordinateY(RandomUtil.randomInt(MAP_HEIGHT));

        if (ifColliding(contestant)) {
            return initContestants(camp);
        }

        contestant.setCamp(camp);
        contestant.setVelocity(CONTESTANT_VELOCITY);
        contestant.setDirection(RandomUtil.randomDouble(-Math.PI, Math.PI));
        contestant.setAcceleration(CONTESTANT_ACCELERATION);

        return contestant;
    }

    private static boolean ifColliding(Contestant contestant) {
        for (Contestant anotherContestant : CONTESTANTS) {
            if (anotherContestant == contestant) {
                continue;
            }
            if (ifColliding(contestant, anotherContestant)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 是否产生碰撞
     */
    private static boolean ifColliding(Contestant contestant, Contestant anotherContestant) {
        int contestantPositionX = contestant.getPositionX();
        int anotherContestantPositionX = anotherContestant.getPositionX();
        if (contestantPositionX >= anotherContestantPositionX + CONTESTANT_SIZE) {
            return Boolean.FALSE;
        }
        if (contestantPositionX + CONTESTANT_SIZE <= anotherContestantPositionX) {
            return Boolean.FALSE;
        }
        int contestantPositionY = contestant.getPositionY();
        int anotherContestantPositionY = anotherContestant.getPositionY();
        if (contestantPositionY >= anotherContestantPositionY + CONTESTANT_SIZE) {
            return Boolean.FALSE;
        }
        if (contestantPositionY + CONTESTANT_SIZE <= anotherContestantPositionY) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private static void showScore() {
        GameService.showScore();
    }

}
