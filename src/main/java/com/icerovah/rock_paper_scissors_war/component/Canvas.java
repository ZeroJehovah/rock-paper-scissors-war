package com.icerovah.rock_paper_scissors_war.component;

import java.awt.*;

import static com.icerovah.rock_paper_scissors_war.consts.UiConstants.*;
import static com.icerovah.rock_paper_scissors_war.consts.UiConstants.CONTESTANTS;
import static com.icerovah.rock_paper_scissors_war.consts.Config.CONTESTANT_SIZE;

public class Canvas extends java.awt.Canvas {

    @Override
    public void paint(Graphics graphics) {
        graphics.clearRect(0, 0, CANVAS.getWidth(), CANVAS.getHeight());
        for (Contestant contestant : CONTESTANTS) {
            graphics.drawImage(contestant.getCamp().getImage(), contestant.getPositionX(), contestant.getPositionY(), CONTESTANT_SIZE, CONTESTANT_SIZE, this);
        }
    }

    public void update(Graphics g) {
        // 获取图片的绘图对象
        Graphics buffer = BUFFERED_IMAGE.getGraphics();
        // 向缓冲区中绘制图片
        paint(buffer);
        // 将缓冲区中的图片绘制到窗口界面中
        g.drawImage(BUFFERED_IMAGE, 0, 0, null);
    }

}