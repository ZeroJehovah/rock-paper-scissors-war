package com.icerovah.rock_paper_scissors_war.enums;

import cn.hutool.core.util.RandomUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

import static com.icerovah.rock_paper_scissors_war.consts.Components.*;

@Getter
@RequiredArgsConstructor
public enum RockPaperScissorsEnum {

    ROCK(ROCK_IMAGE), PAPER(PAPER_IMAGE), SCISSORS(SCISSORS_IMAGE);

    private final Image image;

    public static RockPaperScissorsEnum random() {
        return RandomUtil.randomEle(values());
    }

    public static RockPaperScissorsEnum battle(RockPaperScissorsEnum a, RockPaperScissorsEnum b) {
        if (a == b) {
            return a;
        }
        if (a != ROCK && b != ROCK) {
            return SCISSORS;
        }
        if (a != PAPER && b != PAPER) {
            return ROCK;
        }
        return PAPER;
    }

}
