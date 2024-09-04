package com.icerovah.rock_paper_scissors_war.consts;

import cn.hutool.core.io.IoUtil;
import com.icerovah.rock_paper_scissors_war.component.Contestant;
import com.icerovah.rock_paper_scissors_war.service.ButtonService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Components {

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
