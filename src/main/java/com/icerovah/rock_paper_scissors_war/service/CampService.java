package com.icerovah.rock_paper_scissors_war.service;

import com.icerovah.rock_paper_scissors_war.component.Contestant;
import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;

import java.util.Map;

public class CampService {

    private static final Map<RockPaperScissorsEnum, RockPaperScissorsEnum> CONSUME_REALATION = Map.of(
            RockPaperScissorsEnum.ROCK, RockPaperScissorsEnum.SCISSORS,
            RockPaperScissorsEnum.PAPER, RockPaperScissorsEnum.ROCK,
            RockPaperScissorsEnum.SCISSORS, RockPaperScissorsEnum.PAPER
    );

    private static final Map<RockPaperScissorsEnum, RockPaperScissorsEnum> BE_CONSUMED_REALATION = Map.of(
            RockPaperScissorsEnum.ROCK, RockPaperScissorsEnum.PAPER,
            RockPaperScissorsEnum.PAPER, RockPaperScissorsEnum.SCISSORS,
            RockPaperScissorsEnum.SCISSORS, RockPaperScissorsEnum.ROCK
    );

    /**
     * 战斗
     * @return 是否发生战斗
     */
    public static boolean battle(Contestant oneContestant, Contestant anotherContestant) {
        RockPaperScissorsEnum oneContestantCamp = oneContestant.getCamp();
        RockPaperScissorsEnum anotherContestantCamp = anotherContestant.getCamp();

        if (oneContestantCamp == anotherContestantCamp) {
            return Boolean.FALSE;
        }

        if (CONSUME_REALATION.get(oneContestantCamp) == anotherContestantCamp) {
            GameService.add(oneContestantCamp);
            GameService.sub(anotherContestantCamp);
            anotherContestant.setCamp(oneContestantCamp);
        } else {
            GameService.add(anotherContestantCamp);
            GameService.sub(oneContestantCamp);
            oneContestant.setCamp(anotherContestantCamp);
        }

        return Boolean.TRUE;
    }

    public static RockPaperScissorsEnum getConsumeCamp(RockPaperScissorsEnum camp) {
        return CONSUME_REALATION.get(camp);
    }

    public static RockPaperScissorsEnum getBeConsumedCamp(RockPaperScissorsEnum camp) {
        return BE_CONSUMED_REALATION.get(camp);
    }

}
