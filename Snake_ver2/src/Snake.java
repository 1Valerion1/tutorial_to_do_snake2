import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	Node head;// Змеиная голова
	Node body;// Тело змеи
	Node tail;// Змеиный хвост
	Food food;// еда
	// Инициализируем начальную позицию змейки и направление змейки
	public Snake(Food food) {
		// Создание головы змеи, тела змеи, узлов змеиного хвоста 
		head = new Node(7, 13, Config.RIGHT);
		body = new Node(7, 12, Config.RIGHT);
		tail = new Node(7, 11, Config.RIGHT);
		// Связывание отношений между головой змеи, телом змеи и хвостом змеи
		head.next = body;
		body.pre = head;
		body.next = tail;
		tail.pre = body;
		// Инициализируем пищевой объект
		this.food = food;
		
	}
	
	// рисуем змею
	public void draw(Graphics g) {
		// У змейки несколько узлов, вам нужно вынуть каждый узел, а затем вывести каждый узел
		for (Node n = head;n!=null;n = n.next) {
			// Вызов метода отрисовки узла
			n.draw(g);
		}
	}
	
	// Змея движется
	public void move() {
		// 1. Добавьте змеиную голову 2. Удалите змеиный хвост 3. Съешьте пищу 4. Обнаружение смерти
		addHead();// добавляем змеиную голову
		removeTail();// Удаляем змеиный хвост
		deadCheck();// обнаружение смерти
	}
	
	// добавляем змеиную голову
	public void addHead() {
		// Судьи по направлению змеиной головы
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
		
		// Связь между привязанным узлом и головой змеи
		node.next = head;
		head.pre = node;
		head = node;// Назначьте новый узел головы змеи исходной голове змеи
		
	}
	
	// Удаляем змеиный хвост
	public void removeTail() {
	// 1. Установите для змеиного хвоста значение NULL, и следующий указатель предыдущего узла змеиного хвоста будет равен нулю
		tail.pre.next = null;
		// 2. Приписываем последний узел змеиного хвоста змеиному хвосту
		tail = tail.pre;
	}
	
	// Управляем направлением движения змеи
	public void keyControl(KeyEvent e) {
		// Вверх, вниз, влево и вправо. Изменение направления движения головы змеи, определяя, как клавиатура управляет направлением движения змеи.
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
	  	 *  Есть еду 
	  * 1. Определите, совпадают ли координаты головы змеи и координаты еды.
	  * 2. Восстановите новую жадную змею (добавьте голову, но не хвост)
	  * 3. Снова произвольно генерируйте еду.
	 */
	public void eat() {
		// Определяем, пересекаются ли два прямоугольника (касается ли змеиная голова еды)
		Rectangle a = getHeadRec();
		Rectangle b = food.getFoodRec();
		if (a.intersects(b)) {
			addHead();// добавляем змеиную голову
			food.repair();// Произвольно генерировать еду
		}
		
	}
	
	// Получить координаты головы змеи
	public Rectangle getHeadRec() {
		// Получаем координаты прямоугольника змеиной головы
		return new Rectangle(head.col*Config.SPAN, head.row*Config.SPAN,
				              Config.SPAN, Config.SPAN);
	}
	
	// Проверяем, мертва ли змея
	public void deadCheck() {
		// 1. Змеиная голова попадает в границу
		// Диапазон строк: 0-Config.ROWS-1
		// Диапазон столбца: 0-Config.COLS-1
		if (head.row<0||head.col<0||head.row>Config.ROWS-1
				                  ||head.col>Config.COLS-1) {
			// Меняем статус жадной змеи на смерть
			Config.isLive = false;
		}
		// 2. Голова змеи не может касаться тела
		// Пройдем по телу змеи и определим, совпадает ли каждый узел тела змеи с головой змеи
		for (Node n = head.next; n!=null; n = n.next) {
			// Определяем, совпадает ли положение головы змеи с положением текущего узла тела змеи
			if (head.row == n.row && head.col == n.col) {
				Config.isLive = false;
				break;
			}
		}
	}
}
