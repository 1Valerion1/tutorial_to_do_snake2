import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Food {
	// �����
		private int row;
		// �������
		private int col;
		
		// �����������
		public Food() {
			repair();
		}
		
		// ������ ���
		public void draw(Graphics g) {
			// ������������� ���� �����
			g.setColor(Color.RED);
			// ����������� ������������� (x, y, ������, ������)
			g.fillRect(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);
		}
		// ����������� ���������� �������������� ���
		public void repair() {
			// �������� �������� 0-Config.ROWS
			row = new Random().nextInt(Config.ROWS);
			col = new Random().nextInt(Config.COLS);
		}
		
		// �������� ���������� ���
		public Rectangle getFoodRec() {
			return new Rectangle(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);
		}
}
