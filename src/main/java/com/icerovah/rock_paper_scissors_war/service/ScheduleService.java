package com.icerovah.rock_paper_scissors_war.service;

import cn.hutool.core.util.ObjUtil;

import java.util.concurrent.Executors;

import static com.icerovah.rock_paper_scissors_war.consts.Schedule.SCHEDULE;

public class ScheduleService {

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
