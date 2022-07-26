import java.awt.Color;
import java.awt.Graphics;

public class Node {
	int row;// ������
	int col;// �������
	Node next;// ��������� ���������� ����
	Node pre;// ��������� ����������� ����
	String dir;// ����������� ����
	
	// �����������: �������������� ���������� � �������������� ���� � ����������� ����������� ����
	public Node(int row,int col,String dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	
	// ������ ����
	public void draw(Graphics g) {
		// ���� ���������� ���� �������� ���� ����� ����, ������� ���� - ������ ����
		if (this.pre == null) {
			// ������ ���� ������ ���� ������
			g.setColor(Color.YELLOW);
		}else {
			g.setColor(Color.BLUE);
		}
		g.fillOval(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);

	}
}
