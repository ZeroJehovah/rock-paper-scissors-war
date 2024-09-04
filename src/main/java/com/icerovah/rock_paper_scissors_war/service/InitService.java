package com.icerovah.rock_paper_scissors_war.service;

import com.icerovah.rock_paper_scissors_war.component.Canvas;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

import static com.icerovah.rock_paper_scissors_war.consts.Components.*;
import static com.icerovah.rock_paper_scissors_war.consts.Config.*;

@Slf4j
public class InitService {

    public static void init() {
        // 初始化窗口
        initWindow();

        // 初始化
        initContestants();

        // 初始化画布和按钮
        initCanvasAndButtons();

        // 初始化定时任务
        initSchedule();

        // 显示窗口，初始化图片缓冲区
        GAME_WINDOW.setVisible(Boolean.TRUE);
        BUFFERED_IMAGE = CANVAS.createImage(CANVAS.getWidth(), CANVAS.getHeight());
    }

    private static void initWindow() {
        log.info("初始化窗口");
        GAME_WINDOW = new JFrame("石头剪刀布大战");
        // 关闭窗口时结束进程
        GAME_WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口默认大小（含标题栏）
        GAME_WINDOW.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // 禁止改变窗口大小
        GAME_WINDOW.setResizable(Boolean.FALSE);
    }

    private static void initContestants() {
        log.info("初始化参赛选手");
        ContestantService.initContestants();
    }

    private static void initCanvasAndButtons() {
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

    private static void initSchedule() {
        log.info("初始化定时任务");
        ScheduleService.reNew();
        Runtime.getRuntime().addShutdownHook(new Thread(ScheduleService::close));
    }

}
