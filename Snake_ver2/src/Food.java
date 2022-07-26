import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Food {
	// линия
		private int row;
		// столбец
		private int col;
		
		// Конструктор
		public Food() {
			repair();
		}
		
		// рисуем еду
		public void draw(Graphics g) {
			// Устанавливаем цвет кисти
			g.setColor(Color.RED);
			// Закрашенный прямоугольник (x, y, ширина, высота)
			g.fillRect(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);
		}
		// Произвольно генерируем местоположение еды
		public void repair() {
			// Диапазон значений 0-Config.ROWS
			row = new Random().nextInt(Config.ROWS);
			col = new Random().nextInt(Config.COLS);
		}
		
		// Получаем координаты еды
		public Rectangle getFoodRec() {
			return new Rectangle(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);
		}
}
