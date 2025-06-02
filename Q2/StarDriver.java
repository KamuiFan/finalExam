
import javax.swing.*;

public class StarDriver {

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("請輸入星星的角數 (大於2):");
        int numPoints = 5;
        try {
            numPoints = Integer.parseInt(input);
            if (numPoints < 3) {
                numPoints = 5;
            }
        } catch (Exception e) {
            // 預設5角星
        }
        JFrame frame = new JFrame("多角星");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new StarPanel(numPoints));
        frame.setVisible(true);
    }
}
