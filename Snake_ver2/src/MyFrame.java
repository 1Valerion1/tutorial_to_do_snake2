import javax.swing.JFrame;


public class MyFrame extends JFrame {
    MyPanel myPanel = new MyPanel();
    Button button = new Button(myPanel);

    public MyFrame() {
        this.setTitle("Snakes");
        this.setBounds(300, 50, 706, 510);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.add(myPanel);
        myPanel.setFocusable(true);
        myPanel.requestFocus();
        this.add(button);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
