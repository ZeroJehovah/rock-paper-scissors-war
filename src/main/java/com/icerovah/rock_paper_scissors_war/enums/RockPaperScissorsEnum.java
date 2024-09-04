package com.icerovah.rock_paper_scissors_war.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

import static com.icerovah.rock_paper_scissors_war.consts.UiConstants.*;

@Getter
@RequiredArgsConstructor
public enum RockPaperScissorsEnum {

    ROCK("石头", ROCK_IMAGE), PAPER("布", PAPER_IMAGE), SCISSORS("剪刀", SCISSORS_IMAGE);

    private final String label;
    private final Image image;
}
