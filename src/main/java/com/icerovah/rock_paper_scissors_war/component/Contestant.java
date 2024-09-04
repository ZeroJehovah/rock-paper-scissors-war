package com.icerovah.rock_paper_scissors_war.component;

import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contestant {

    private RockPaperScissorsEnum type;

    /**
     * 精确坐标X
     */
    private double coordinateX;

    /**
     * 精确坐标Y
     */
    private double coordinateY;

    /**
     * 上一次的精确坐标X
     */
    private double lastCoordinateX;

    /**
     * 上一次的精确坐标Y
     */
    private double lastCoordinateY;

    /**
     * 速度
     */
    private double velocity;

    /**
     * 方向，弧度值(-π, π]，0为向右，π/2为向上，-π/2为向下，π为向左
     */
    private double direction;

    /**
     * 加速度
     */
    private float acceleration;

    /**
     * 加速度方向
     */
    private float accelerationDirection;

    /**
     * 绘图坐标X
     */
    public int getPositionX() {
        return (int) coordinateX;
    }

    /**
     * 绘图坐标Y
     */
    public int getPositionY() {
        return (int) coordinateY;
    }

    public void backUpCoordinates() {
        lastCoordinateX = coordinateX;
        lastCoordinateY = coordinateY;
    }

    public void move() {
        coordinateX = lastCoordinateX + velocity * Math.cos(direction);
        coordinateY = lastCoordinateY + velocity * Math.sin(direction);
    }

}
