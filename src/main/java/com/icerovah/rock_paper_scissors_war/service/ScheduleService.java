package com.icerovah.rock_paper_scissors_war.service;

import cn.hutool.core.util.ObjUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

import static com.icerovah.rock_paper_scissors_war.consts.Schedule.SCHEDULE;

@Slf4j
public class ScheduleService {

    public static void initSchedule() {
        log.info("初始化定时任务");
        ScheduleService.reNew();
        Runtime.getRuntime().addShutdownHook(new Thread(ScheduleService::close));
    }

    public synchronized static void close() {
        if (ObjUtil.isNotNull(SCHEDULE)) {
            SCHEDULE.shutdownNow();
        }
        SCHEDULE = null;
    }

    public static void reNew() {
        close();
        SCHEDULE = Executors.newScheduledThreadPool(1);
    }

}
