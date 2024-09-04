package com.icerovah.rock_paper_scissors_war.enums;

/**
 * 行动策略
 */
public enum ActionStrategyEnum {

    /**
     * 无脑（不主动改变方向）
     */
    RECKLESS,

    /**
     * 进攻（优先靠近最近可消灭的对手）
     */
    OFFENSIVE,

    /**
     * 防守（优先远离最近的猎手）
     */
    DEFENSIVE,

    /**
     * 智能（根据最近的对手行动）
     */
    INTELLIGENT

}
