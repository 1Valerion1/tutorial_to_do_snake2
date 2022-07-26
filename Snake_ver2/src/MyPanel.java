import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements KeyListener {

    // ������� ������ ���
    Food food = new Food();
    // ������� ������ ����
    Snake snake = new Snake(food);
    // ������� ������ ������
    SnakeThread snakeThread = new SnakeThread();

    public MyPanel() {
        // ������������� ���������� � ������ ����������
        this.setBounds(0, 0, 700, 440);
        // ������������� ���� ���� ����������
        this.setBackground(Color.BLACK);
        // ��������� �����
        snakeThread.start();
        // ������������ ��������� ����������
        this.addKeyListener(this);
    }

    // ������ ���������
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // ������������� ���� �������
        g.setColor(Color.GRAY);
        // ������ �������������� �����
        for (int i = 0; i < Config.ROWS; i++) {
            // ���������� ������� ����, ����� ���������� ����� ����� ������� (x1, y1) � (x2, y2)
            g.drawLine(0, Config.SPAN * i, Config.COLS * Config.SPAN, Config.SPAN * i);
        }
        // ������ ������������ �����
        for (int i = 0; i < Config.COLS; i++) {
            g.drawLine(Config.SPAN * i, 0, Config.SPAN * i, Config.ROWS * Config.SPAN);
        }
        // ���� ��������
        snake.move();
        // ������ ���
        food.draw(g);
        // ������ ����
        snake.draw(g);
        // ���� ���
        snake.eat();
    }

    // ���������� ����
    class SnakeThread extends Thread {
        boolean flag = true;// ������ �������

        @Override
        public void run() {
            // Config.isLive: ����������, ���� �� ����
            while (Config.isLive && flag) {
                try {
                    // ����� ���� �� ������, ���������� ��������
                    Thread.sleep(200);// ������� ����� ���� �� 0,2 �������
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Config.isPause == true: �������� ���������� ����
                // Config.isPause == false: �������� ������������� ����
                if (Config.isLive && Config.isPause) {
                    // �������������� ������� � �������� ���������� ��������
                    // ����� ���������� ����������� repaint () -> ����� ������ awt -> ����� update () -> paint ()
                    repaint();
                }
                if (!Config.isLive) {
                    // ��������� ���������� ���� ��� ���������� ����
                    JOptionPane.showMessageDialog(MyPanel.this, "Game over");
                }
            }
        }

        // ����� ��������� ������
        public void stopThread() {
            flag = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // ����� ������ ����������� ���������� Snake
        snake.keyControl(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
