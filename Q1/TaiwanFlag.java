
import javax.swing.*;

public class TaiwanFlag {

    public static void main(String[] args) {
        JFrame frame = new JFrame("中華民國國旗");
        TaiwanFlagPanel flagPanel = new TaiwanFlagPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // 3:2比例
        frame.add(flagPanel);
        frame.setVisible(true);
    }
}
