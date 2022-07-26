import java.awt.Color;
import java.awt.Graphics;

public class Node {
	int row;// Строка
	int col;// столбец
	Node next;// Указатель следующего узла
	Node pre;// указатель предыдущего узла
	String dir;// Направление змеи
	
	// Конструктор: инициализируем информацию о местоположении змеи и формулируем направление змеи
	public Node(int row,int col,String dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	
	// рисуем узлы
	public void draw(Graphics g) {
		// Если предыдущий узел текущего узла равен нулю, текущий узел - голова змеи
		if (this.pre == null) {
			// Рисуем цвет головы змеи желтым
			g.setColor(Color.YELLOW);
		}else {
			g.setColor(Color.BLUE);
		}
		g.fillOval(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);

	}
}
