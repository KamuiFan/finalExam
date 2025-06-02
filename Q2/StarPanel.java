import java.awt.*;
import javax.swing.*;

public class StarPanel extends JPanel {
    private int numPoints;

    public StarPanel(int numPoints) {
        this.numPoints = numPoints;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int outerRadius = Math.min(width, height) / 2 - 20;
        int innerRadius = outerRadius / 2;
        Polygon star = new Polygon();
        for (int i = 0; i < numPoints * 2; i++) {
            double angle = Math.PI / numPoints * i;
            int r = (i % 2 == 0) ? outerRadius : innerRadius;
            int x = centerX + (int) (r * Math.sin(angle));
            int y = centerY - (int) (r * Math.cos(angle));
            star.addPoint(x, y);
        }
        g.setColor(Color.ORANGE);
        g.fillPolygon(star);
        g.setColor(Color.BLACK);
        g.drawPolygon(star);
    }
}
