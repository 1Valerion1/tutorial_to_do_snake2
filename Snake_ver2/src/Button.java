import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JPanel implements ActionListener {
    MyPanel myPanel;
	JButton pause;
	JButton continu;
	JButton restart;
	JButton exit;
	public Button(MyPanel myPanel) {
		this.myPanel = myPanel;
		this.setBounds(0,440,706,60);
		pause = new JButton("Pause");
		continu = new JButton("Next");
		restart = new JButton("Restart");
		exit = new JButton("Exit");
        this.add(pause);
        this.add(continu);
        this.add(restart);
        this.add(exit);
        
        pause.addActionListener(this);
        continu.addActionListener(this);
        restart.addActionListener(this);
        exit.addActionListener(this);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == pause) {
			Config.isPause = false;
		}
		if(e.getSource() == continu) {
			Config.isPause = true;
			myPanel.setFocusable(true);
			myPanel.requestFocus();
		}
		//  Все ок, просто тебе знаний не хватает
		if(e.getSource() == restart) {
			//1. Остановить текущий поток
			myPanel.snakeThread.stopThread();
			// Регенерируйте змей и еду
			Food food = new Food();
			myPanel.food = food;
			myPanel.snake = new Snake(food);
			//Восстанавливаем условия управления в исходное состояние
			Config.isPause = true;
			Config.isLive = true;
			// Создаем новый объект потока (объект внутреннего класса)
			MyPanel.SnakeThread snakee = myPanel.new SnakeThread();
			// Запускаем поток
			snakee.start();
			myPanel.snakeThread = snakee;

			//Получить фокус клавиатуры
			myPanel.setFocusable(true);
			myPanel.requestFocus();
		}
		
		// Объект мониторинга - выйти из игры
		if(e.getSource() == exit) {
			System.exit(0);
		}
	}

}
