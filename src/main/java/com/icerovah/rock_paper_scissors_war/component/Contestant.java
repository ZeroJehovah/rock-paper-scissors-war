package com.icerovah.rock_paper_scissors_war.component;

import com.icerovah.rock_paper_scissors_war.enums.RockPaperScissorsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contestant {

    private RockPaperScissorsEnum camp;

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
    private double acceleration;

    /**
     * 加速度方向
     */
    private double accelerationDirection;

    /**
     * 是否锁定坐标(在边缘碰撞后锁定)
     */
    private boolean lockCoordinate;

    public void lockCoordinate() {
        lockCoordinate = Boolean.TRUE;
    }

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
        lockCoordinate = Boolean.FALSE;
    }

    public void move() {
        coordinateX = lastCoordinateX + velocity * Math.cos(direction);
        coordinateY = lastCoordinateY + velocity * Math.sin(direction);
    }

    public void updateDirectionAndVelocity() {
        double newDirectionX = Math.cos(direction) * velocity + Math.cos(accelerationDirection) * acceleration;
        double newDirectionY = Math.sin(direction) * velocity + Math.sin(accelerationDirection) * acceleration;
        this.direction = Math.atan2(newDirectionY, newDirectionX);
//        double newVelocity = Math.sqrt(Math.pow(newDirectionX, 2) + Math.pow(newDirectionY, 2));
//        this.velocity = Math.min(newVelocity, Config.CONTESTANT_VELOCITY);
    }

}
