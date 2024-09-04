package com.icerovah.rock_paper_scissors_war.consts;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.icerovah.rock_paper_scissors_war.component.Contestant;
import com.icerovah.rock_paper_scissors_war.service.ButtonService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UiConstants {

    public static final String DEFAULT_TITLE = "石头剪刀布大战";
    public static final String SCORE_TITLE_TEMPLATE = StrUtil.format("{} [R: {}, P: {}, S: {}]", DEFAULT_TITLE);
    public static final String WINNER_TITLE_TEMPLATE = StrUtil.format("{} [恭喜{}获胜]", DEFAULT_TITLE);

    public static JFrame GAME_WINDOW;
    public static Canvas CANVAS;
    public static Image BUFFERED_IMAGE;
    public static JButton START_BUTTON;
    public static JButton RESTART_BUTTON;
    public static List<Contestant> CONTESTANTS;

    public static final Image ROCK_IMAGE;
    public static final Image PAPER_IMAGE;
    public static final Image SCISSORS_IMAGE;

    static {
        try (InputStream paper = ButtonService.class.getClassLoader().getResourceAsStream("images/rock.png")) {
            ROCK_IMAGE = new ImageIcon(IoUtil.readBytes(paper)).getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (InputStream paper = ButtonService.class.getClassLoader().getResourceAsStream("images/paper.png")) {
            PAPER_IMAGE = new ImageIcon(IoUtil.readBytes(paper)).getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (InputStream paper = ButtonService.class.getClassLoader().getResourceAsStream("images/scissors.png")) {
            SCISSORS_IMAGE = new ImageIcon(IoUtil.readBytes(paper)).getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
