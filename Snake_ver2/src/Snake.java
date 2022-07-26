import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	Node head;// ������� ������
	Node body;// ���� ����
	Node tail;// ������� �����
	Food food;// ���
	// �������������� ��������� ������� ������ � ����������� ������
	public Snake(Food food) {
		// �������� ������ ����, ���� ����, ����� �������� ������ 
		head = new Node(7, 13, Config.RIGHT);
		body = new Node(7, 12, Config.RIGHT);
		tail = new Node(7, 11, Config.RIGHT);
		// ���������� ��������� ����� ������� ����, ����� ���� � ������� ����
		head.next = body;
		body.pre = head;
		body.next = tail;
		tail.pre = body;
		// �������������� ������� ������
		this.food = food;
		
	}
	
	// ������ ����
	public void draw(Graphics g) {
		// � ������ ��������� �����, ��� ����� ������ ������ ����, � ����� ������� ������ ����
		for (Node n = head;n!=null;n = n.next) {
			// ����� ������ ��������� ����
			n.draw(g);
		}
	}
	
	// ���� ��������
	public void move() {
		// 1. �������� ������� ������ 2. ������� ������� ����� 3. ������� ���� 4. ����������� ������
		addHead();// ��������� ������� ������
		removeTail();// ������� ������� �����
		deadCheck();// ����������� ������
	}
	
	// ��������� ������� ������
	public void addHead() {
		// ����� �� ����������� ������� ������
		Node node = null;
		switch (head.dir) {
		case Config.RIGHT:
			node = new Node(head.row,head.col+1, head.dir);
			break;
		case Config.LEFT:
			node = new Node(head.row, head.col-1, head.dir);
			break;
		case Config.UP:
			node = new Node(head.row-1, head.col, head.dir);
			break;
		case Config.DOWN:
			node = new Node(head.row+1, head.col, head.dir);
			break;
		default:
			break;
		}
		
		// ����� ����� ����������� ����� � ������� ����
		node.next = head;
		head.pre = node;
		head = node;// ��������� ����� ���� ������ ���� �������� ������ ����
		
	}
	
	// ������� ������� �����
	public void removeTail() {
	// 1. ���������� ��� �������� ������ �������� NULL, � ��������� ��������� ����������� ���� �������� ������ ����� ����� ����
		tail.pre.next = null;
		// 2. ����������� ��������� ���� �������� ������ �������� ������
		tail = tail.pre;
	}
	
	// ��������� ������������ �������� ����
	public void keyControl(KeyEvent e) {
		// �����, ����, ����� � ������. ��������� ����������� �������� ������ ����, ���������, ��� ���������� ��������� ������������ �������� ����.
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (head.dir.equals(Config.DOWN)) {
				break;
			}
			head.dir = Config.UP;
			break;
		case KeyEvent.VK_DOWN:
			if (head.dir.equals(Config.UP)) {
				break;
			}
			head.dir = Config.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			if (head.dir.equals(Config.RIGHT)) {
				break;
			}
			head.dir = Config.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			if (head.dir.equals(Config.LEFT)) {
				break;
			}
			head.dir = Config.RIGHT;
			break;
		
		}
	}
	
	/*
	  	 *  ���� ��� 
	  * 1. ����������, ��������� �� ���������� ������ ���� � ���������� ���.
	  * 2. ������������ ����� ������ ���� (�������� ������, �� �� �����)
	  * 3. ����� ����������� ����������� ���.
	 */
	public void eat() {
		// ����������, ������������ �� ��� �������������� (�������� �� ������� ������ ���)
		Rectangle a = getHeadRec();
		Rectangle b = food.getFoodRec();
		if (a.intersects(b)) {
			addHead();// ��������� ������� ������
			food.repair();// ����������� ������������ ���
		}
		
	}
	
	// �������� ���������� ������ ����
	public Rectangle getHeadRec() {
		// �������� ���������� �������������� ������� ������
		return new Rectangle(head.col*Config.SPAN, head.row*Config.SPAN,
				              Config.SPAN, Config.SPAN);
	}
	
	// ���������, ������ �� ����
	public void deadCheck() {
		// 1. ������� ������ �������� � �������
		// �������� �����: 0-Config.ROWS-1
		// �������� �������: 0-Config.COLS-1
		if (head.row<0||head.col<0||head.row>Config.ROWS-1
				                  ||head.col>Config.COLS-1) {
			// ������ ������ ������ ���� �� ������
			Config.isLive = false;
		}
		// 2. ������ ���� �� ����� �������� ����
		// ������� �� ���� ���� � ���������, ��������� �� ������ ���� ���� ���� � ������� ����
		for (Node n = head.next; n!=null; n = n.next) {
			// ����������, ��������� �� ��������� ������ ���� � ���������� �������� ���� ���� ����
			if (head.row == n.row && head.col == n.col) {
				Config.isLive = false;
				break;
			}
		}
	}
}
