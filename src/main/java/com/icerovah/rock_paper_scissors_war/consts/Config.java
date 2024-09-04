package com.icerovah.rock_paper_scissors_war.consts;

import com.icerovah.rock_paper_scissors_war.enums.ActionStrategyEnum;
import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;

import java.util.Map;

public class Config {

    // 行动策略
    public static Map<RockPaperScissorsEnum, ActionStrategyEnum> ACTION_STRATEGY = Map.of(
            RockPaperScissorsEnum.ROCK, ActionStrategyEnum.OFFENSIVE,
            RockPaperScissorsEnum.PAPER, ActionStrategyEnum.OFFENSIVE,
            RockPaperScissorsEnum.SCISSORS, ActionStrategyEnum.OFFENSIVE
    );

    // 参赛选手数量
    public static final int CONTESTANT_COUNT = 60;
    // 参赛选手速度
    public static final double CONTESTANT_VELOCITY = 0.5;
    // 参赛选手加速度
    public static final double CONTESTANT_ACCELERATION = 0.01;
    // 参赛选手大小
    public static final int CONTESTANT_SIZE = 10;
    // 地图宽度
    public static final int CANVAS_WIDTH = 480;
    // 地图高度
    public static final int CANVAS_HEIGHT = 270;

    /* 以上的配置会影响游戏 */

    /**
     * 刷新延时，8333微秒，对应约120Hz
     */
    public static final int DELAY = 8333;

    // win11默认窗口边界尺寸
    public static final int TITLE_HEIGHT = 31;
    public static final int BORDER_WIDTH = 8;

    // 控件间隔
    public static final int ITEM_MARGIN = 10;

    // 控件尺寸
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 30;

    // 窗口大小（计算得到）
    public static final int WINDOW_WIDTH = CANVAS_WIDTH + BORDER_WIDTH * 2;
    public static final int WINDOW_HEIGHT = CANVAS_HEIGHT + TITLE_HEIGHT + BUTTON_HEIGHT + BORDER_WIDTH + ITEM_MARGIN * 3;

    // 地图大小（计算得到）
    public static final int MAP_WIDTH = CANVAS_WIDTH - CONTESTANT_SIZE;
    public static final int MAP_HEIGHT = CANVAS_HEIGHT - CONTESTANT_SIZE;

    // 控件位置（计算得到）
    public static final int CANVAS_POSITION_X = 0;
    public static final int CANVAS_POSITION_Y = 0;
    public static final int START_BUTTON_POSITION_X = (CANVAS_WIDTH + BORDER_WIDTH * 2) / 2 - BUTTON_WIDTH - ITEM_MARGIN / 2;
    public static final int RESTART_BUTTON_POSITION_X = (CANVAS_WIDTH + BORDER_WIDTH * 2) / 2 + ITEM_MARGIN / 2;
    public static final int BUTTON_POSITION_Y = CANVAS_HEIGHT + ITEM_MARGIN;

}
