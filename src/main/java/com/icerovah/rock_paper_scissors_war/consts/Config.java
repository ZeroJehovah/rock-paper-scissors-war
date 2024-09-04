package com.icerovah.rock_paper_scissors_war.consts;

public class Config {

    /**
     * 刷新延时，8333微秒，对应约120Hz
     */
    public static final int DELAY = 8333;

    // win11默认窗口边界尺寸
    public static final int TITLE_HEIGHT = 31;
    public static final int BORDER_WIDTH = 8;

    // 控件间隔
    public static final int ITEM_MARGIN = 2;

    // 参赛选手数量
    public static final int CONTESTANT_COUNT = 20;
    // 参赛选手速度
    public static final int CONTESTANT_VELOCITY = 1;

    // 控件尺寸
    public static final int CONTESTANT_SIZE = 16;
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 300;
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 30;

    // 窗口大小（计算得到）
    public static final int WINDOW_WIDTH = CANVAS_WIDTH + ITEM_MARGIN * 2 + BORDER_WIDTH * 2;
    public static final int WINDOW_HEIGHT = CANVAS_HEIGHT + TITLE_HEIGHT + BUTTON_HEIGHT + BORDER_WIDTH + ITEM_MARGIN * 3;

    // 地图大小（计算得到）
    public static final int MAP_WIDTH = CANVAS_WIDTH - CONTESTANT_SIZE;
    public static final int MAP_HEIGHT = CANVAS_HEIGHT - CONTESTANT_SIZE;

    // 控件位置（计算得到）
    public static final int CANVAS_POSITION_X = ITEM_MARGIN;
    public static final int CANVAS_POSITION_Y = ITEM_MARGIN;
    public static final int START_BUTTON_POSITION_X = (CANVAS_WIDTH + BORDER_WIDTH * 2) / 2 - BUTTON_WIDTH - ITEM_MARGIN / 2;
    public static final int RESTART_BUTTON_POSITION_X = (CANVAS_WIDTH + BORDER_WIDTH * 2) / 2 + ITEM_MARGIN / 2;
    public static final int BUTTON_POSITION_Y = CANVAS_HEIGHT + ITEM_MARGIN * 2;

}
