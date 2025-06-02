import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class PrizePanel extends JPanel {

    private static final int FRAME = 400;
    private static final Color BACKGROUND = new Color(204, 204, 204);
    private final BufferedImage myImage;
    private final Graphics myBuffer;
    private final Ball ball;
    private final Polkadot pd;
    private final Timer t;
    private int hits = 0;

    private boolean dragging = false;

    // constructor
    public PrizePanel() {
        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME, FRAME);
        ball = new Ball();
        pd = new Polkadot(50, 50, 30, Color.red);
        t = new Timer(5, new Listener());
        t.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    dragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragging) {
                    dragging = false;
                    double centerX = FRAME / 2.0;
                    double centerY = FRAME / 2.0;
                    ball.setdx((centerX - e.getX()) / 50.0);
                    ball.setdy((centerY - e.getY()) / 50.0);
                    System.out.println("球已發射，速度 dx: " + ball.getdx() + ", dy: " + ball.getdy());
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    ball.setX(e.getX());
                    ball.setY(e.getY());
                    System.out.println("拖曳球到位置 x: " + ball.getX() + ", y: " + ball.getY());
                }
            }
        });

        // 設定固定大小，避免面板自動縮放造成偏差
        setPreferredSize(new Dimension(FRAME, FRAME));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ✅ 不要拉伸圖片，確保滑鼠與圖像對齊
        g.drawImage(myImage, 0, 0, null);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);

            if (!dragging) {
                ball.move(FRAME, FRAME);
            }

            collide(ball, pd);

            ball.draw(myBuffer);
            pd.draw(myBuffer);

            myBuffer.setColor(Color.BLACK);
            myBuffer.setFont(new Font("Monospaced", Font.BOLD, 24));
            myBuffer.drawString("Count: " + hits, FRAME - 150, 25);

            if (dragging) {
                myBuffer.setColor(Color.YELLOW);
                myBuffer.drawOval(FRAME / 2 - 15, FRAME / 2 - 15, 30, 30);

                myBuffer.setColor(Color.RED);
                myBuffer.drawLine(FRAME / 2, FRAME / 2, (int) ball.getX(), (int) ball.getY());
            }

            repaint();
        }
    }

    private void collide(Ball b, Polkadot pd) {
        double d = distance(pd.getX(), pd.getY(), b.getX(), b.getY());
        if (d <= b.getRadius() + pd.getRadius()) {
            hits++;
            pd.jump(FRAME, FRAME);
        }
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
