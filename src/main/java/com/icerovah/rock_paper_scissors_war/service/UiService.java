package com.icerovah.rock_paper_scissors_war.service;

import cn.hutool.core.util.StrUtil;
import com.icerovah.rock_paper_scissors_war.component.Canvas;
import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

import java.awt.*;

import static com.icerovah.rock_paper_scissors_war.consts.UiConstants.*;
import static com.icerovah.rock_paper_scissors_war.consts.UiConstants.RESTART_BUTTON;
import static com.icerovah.rock_paper_scissors_war.consts.Config.*;
import static com.icerovah.rock_paper_scissors_war.consts.Config.BUTTON_HEIGHT;

@Slf4j
public class UiService {

    /**
     * 初始化窗口
     */
    public static void initWindow() {
        log.info("初始化窗口");
        GAME_WINDOW = new JFrame();
        // 设置标题
        setDefaultTitle();
        // 关闭窗口时结束进程
        GAME_WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口默认大小（含标题栏）
        GAME_WINDOW.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // 禁止改变窗口大小
        GAME_WINDOW.setResizable(Boolean.FALSE);
    }

    /**
     * 显示窗口，初始化图片缓冲区
     */
    public static void showWindow() {
        log.info("显示窗口");
        GAME_WINDOW.setVisible(Boolean.TRUE);
        BUFFERED_IMAGE = CANVAS.createImage(CANVAS.getWidth(), CANVAS.getHeight());
    }

    /**
     * 初始化画布和按钮
     */
    public static void initCanvasAndButtons() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.LIGHT_GRAY);

        log.info("初始化画布");
        CANVAS = new Canvas();
        CANVAS.setBounds(CANVAS_POSITION_X, CANVAS_POSITION_Y, CANVAS_WIDTH, CANVAS_HEIGHT);
        CANVAS.setBackground(Color.WHITE);
        contentPane.add(CANVAS);

        log.info("初始化按钮");
        START_BUTTON = new JButton("开始");
        START_BUTTON.setBounds(START_BUTTON_POSITION_X, BUTTON_POSITION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        START_BUTTON.setVisible(Boolean.TRUE);
        START_BUTTON.addActionListener(e -> ButtonService.startButtonEvent());
        contentPane.add(START_BUTTON);

        RESTART_BUTTON = new JButton("重新开始");
        RESTART_BUTTON.setBounds(RESTART_BUTTON_POSITION_X, BUTTON_POSITION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        RESTART_BUTTON.setVisible(Boolean.TRUE);
        RESTART_BUTTON.addActionListener(e -> ButtonService.restartButtonEvent());
        contentPane.add(RESTART_BUTTON);

        GAME_WINDOW.setContentPane(contentPane);
    }

    public static void setDefaultTitle() {
        GAME_WINDOW.setTitle(DEFAULT_TITLE);
    }

    public static void setScoreTitle(int rockCount, int paperCount, int scissorsCount) {
        GAME_WINDOW.setTitle(StrUtil.format(SCORE_TITLE_TEMPLATE, rockCount, paperCount, scissorsCount));
    }

    public static void setWinnertTitle(RockPaperScissorsEnum winner) {
        GAME_WINDOW.setTitle(StrUtil.format(WINNER_TITLE_TEMPLATE, winner.getLabel()));
    }

}
