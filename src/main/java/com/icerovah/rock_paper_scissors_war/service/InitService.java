package com.icerovah.rock_paper_scissors_war.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitService {

    public static void init() {
        // 初始化窗口
        UiService.initWindow();

        // 初始化参赛选手
        ContestantService.initContestants();

        // 初始化画布和按钮
        UiService.initCanvasAndButtons();

        // 初始化定时任务
        ScheduleService.initSchedule();

        // 显示窗口，初始化图片缓冲区
        UiService.showWindow();
    }

}
