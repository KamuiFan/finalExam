//Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
//version 6.17.2003

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class KarelPanel extends JPanel {

    private static final int PANEL_WIDTH = 395, PANEL_HEIGHT = 391; //constants
    private static final Color BACKGROUND = new Color(204, 204, 204);

    private int xPos, yPos, dir; //fields
    private final ImageIcon[] myArray;
    private final BufferedImage myImage;
    private final Graphics myBuffer;

    public KarelPanel() {
        myImage = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myArray = new ImageIcon[4];
        myArray[0] = new ImageIcon("Q2/karele.gif");   //east
        myArray[1] = new ImageIcon("Q2/kareln.gif");   //north
        myArray[2] = new ImageIcon("Q2/karelw.gif");   //west
        myArray[3] = new ImageIcon("Q2/karels.gif");   //south
        dir = 0;     //start facing east
        xPos = 1;    //starting xPos
        yPos = PANEL_HEIGHT - 1 - myArray[dir].getImage().getHeight(null); //starting yPos
        Timer t = new Timer(250, new Listener());
        t.start();

        // 新增方向鍵控制邏輯
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        dir = 1; // 向上
                        if (yPos - 30 < 0) {
                            System.out.println("警告：已達上邊界");
                        } else {
                            yPos -= 30;
                        }
                    }
                    case KeyEvent.VK_DOWN -> {
                        dir = 3; // 向下
                        if (yPos + 30 >= PANEL_HEIGHT) {
                            System.out.println("警告：已達下邊界");
                        } else {
                            yPos += 30;
                        }
                    }
                    case KeyEvent.VK_LEFT -> {
                        dir = 2; // 向左
                        if (xPos - 30 < 0) {
                            System.out.println("警告：已達左邊界");
                        } else {
                            xPos -= 30;
                        }
                    }
                    case KeyEvent.VK_RIGHT -> {
                        dir = 0; // 向右
                        if (xPos+60 >= PANEL_WIDTH) {
                            System.out.println("警告：已達右邊界");
                        } else {
                            xPos += 30;
                        }
                    }
                    case KeyEvent.VK_SPACE -> {
                        dir = 0; // 初始方向
                        xPos = 1; // 初始 xPos
                        yPos = PANEL_HEIGHT - 1 - myArray[dir].getImage().getHeight(null); // 初始 yPos
                    }
                }
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
            myBuffer.setColor(Color.red);
            // 垂直線：修正讓每格都等寬且不會跳過中間線
            for (int i = 0; i <= 12; i++) {
                int x = 15 + i * 30;
                myBuffer.fillRect(x, 0, 1, PANEL_HEIGHT); // 畫一條 1px 寬的豎線
            }
            for (int i = 0; i <= 13; i++) {
                int y = 15 + i * 30;
                myBuffer.fillRect(0, y, PANEL_WIDTH, 1); // 畫一條 1px 高的橫線
            }

            myBuffer.drawImage(myArray[dir].getImage(), xPos, yPos, null);

            repaint();
        }
    }
}
