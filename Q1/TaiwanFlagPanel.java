
import java.awt.*;
import javax.swing.*;

public class TaiwanFlagPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 國旗尺寸 600x400
        int width = getWidth();
        int height = getHeight();

        // 1. 畫滿地紅
        g.setColor(Color.RED);
        g.fillRect(0, 0, width, height);

        // 2. 畫左上角青天 (300x200)
        int blueW = 300;
        int blueH = 200;
        g.setColor(new Color(0, 56, 168));
        g.fillRect(0, 0, blueW, blueH);

        // 3. 畫白色正12芒星（烏龜畫圖法）
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int numPoints = 12;
        int starCenterX = blueW / 2;
        int starCenterY = blueH / 2;
        int starOuter = 30 * 5 / 2; // 75
        Polygon star = new Polygon();
        double angleTurtle = Math.toRadians(-90); // 讓第一個尖角朝正上
        for (int j = 0; j < numPoints; j++) {
            int px = starCenterX + (int) (starOuter * Math.cos(angleTurtle));
            int py = starCenterY + (int) (starOuter * Math.sin(angleTurtle));
            star.addPoint(px, py);
            angleTurtle += Math.toRadians(150); // 右轉150度
        }
        g2.setColor(Color.WHITE);
        g2.fillPolygon(star);

        // 4. 畫藍色圓形蓋住12角星中間 (直徑17*5=85)
        int blueCircleD = 17 * 5;
        int blueCircleX = starCenterX - blueCircleD / 2;
        int blueCircleY = starCenterY - blueCircleD / 2;
        g2.setColor(new Color(0, 56, 168));
        g2.fillOval(blueCircleX, blueCircleY, blueCircleD, blueCircleD);

        // 5. 畫白色圓形 (直徑15*5=75)
        int whiteCircleD = 15 * 5;
        int whiteCircleX = starCenterX - whiteCircleD / 2;
        int whiteCircleY = starCenterY - whiteCircleD / 2;
        g2.setColor(Color.WHITE);
        g2.fillOval(whiteCircleX, whiteCircleY, whiteCircleD, whiteCircleD);
    }
}
