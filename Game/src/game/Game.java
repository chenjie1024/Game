package game;
/*
 * ������ ��̬���� ��ʼ���� ����ؿ� ����Boss �ӱ������� 
 * */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233689943517353386L;
	
	

	public static final int WIDTH = 400;
	
	public static final int HEIGHT = 700;
	
	//	����IO�� ����ͼƬ
	public static BufferedImage logoImg;//logoͼƬ
	public static BufferedImage bgImg;//����ͼƬ
	public static BufferedImage logoImg1;//����ͼƬ
	
	public static BufferedImage play;//��ʼ
	public static BufferedImage pause;//��ͣ
	public static BufferedImage gameover;//��Ϸ����
	
	public static BufferedImage hero0;//Ӣ�ۻ�
	public static BufferedImage hero1;//Ӣ�ۻ� 
	public static BufferedImage hero2;//Ӣ�ۻ�
	public static BufferedImage hero3;//Ӣ�ۻ�
	public static BufferedImage hero4;//Ӣ�ۻ� 
	
	public static BufferedImage airplane;//�л�
	public static BufferedImage emenmy;
	public static BufferedImage bee;//�۷�
	public static BufferedImage prop_type;//�۷�
	public static BufferedImage bullet;//�ӵ�
	
	public static BufferedImage zty;//
	
//	��������Hero��������
	private Hero hero = new Hero();
	
	private ChecKpoint Checkpoints = new ChecKpoint();
	
	private FlyingObject[] flyings = {};//һ�ѵ��ˣ��л�+С�۷䣩
	private Bullet[] bullets = {};//һ���ӵ�
	
	
	public static final int START = 0;//����״̬
	public static final int RUNNING = 1;//����״̬
	public static final int PAUSE = 2;//��ͣ״̬
	public static final int GAME_OVER = 3;//��Ϸ����
	
	public int state = START;//Ĭ������״̬
	
	
	
	public String[] Checkpoint = new String[]{"����","��һ��","�ڶ���","������","���Ĺ�","�����"};
	
	
	
//	main����һ�� ����ҲҲ����һ��   �����ಿ��
	static{
//		���쳣
		try {
//			IO����ͼƬ
			logoImg = ImageIO.read(Game.class.getClassLoader().getResource("logo.jpg"));
			//logoImg1 = ImageIO.read(Game.class.getClassLoader().getResource("Logo.png"));
			bgImg = ImageIO.read(Game.class.getClassLoader().getResource("background.png"));
			
			play = ImageIO.read(Game.class.getClassLoader().getResource("Play.png"));
			pause = ImageIO.read(Game.class.getClassLoader().getResource("pause.png"));
			gameover = ImageIO.read(Game.class.getClassLoader().getResource("gameover.png"));
			
			hero0 = ImageIO.read(Hero.class.getClassLoader().getResource("hero0.png"));
			hero1 = ImageIO.read(Hero.class.getClassLoader().getResource("hero1.png"));
			hero2 = ImageIO.read(Hero.class.getClassLoader().getResource("hero_blowup2.png"));
			hero3 = ImageIO.read(Hero.class.getClassLoader().getResource("hero_blowup3.png"));
			hero3 = ImageIO.read(Hero.class.getClassLoader().getResource("hero_blowup4.png"));
			
			airplane = ImageIO.read(Hero.class.getClassLoader().getResource("airplane.png"));
			//emenmy = ImageIO.read(Hero.class.getClassLoader().getResource("enemy1.png"));
			bee = ImageIO.read(Hero.class.getClassLoader().getResource("bee.png"));
			prop_type = ImageIO.read(Hero.class.getClassLoader().getResource("prop_type.png"));
			bullet = ImageIO.read(Hero.class.getClassLoader().getResource("bullet.png"));
			
			//zty = ImageIO.read(Hero.class.getClassLoader().getResource("ZTY.png"));
			
		} catch (IOException e) {
			System.out.println("ͼƬ����");
			e.printStackTrace();
			
		}
	}
//	������
	public static void main(String[] args) {
//		��������
		JFrame frame = new JFrame("�鳤����Java�ɻ���ս");
//		����Ĭ�Ϲر�   �˳�Ӧ�ó���Ĭ�ϴ��ڹرղ�����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		���ô��ڵĴ�С ��λ PX 
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		
		frame.setLocationRelativeTo(null);
		
		Game g = new Game();
//		������
		frame.add(g);
		
		frame.setVisible(true);
		
		g.action();
		
		
		
	}
	
	public void paint(Graphics g){
//		���ڼ��ر��� logo ͼƬ 
		g.drawImage(bgImg, 0, 0, null);
//		g.drawImage(logoImg, 0, 0, null);
		paintHero(g);
		
		paintFlying(g);
		
		paintBullsets(g);
		
		paintSL(g);
		
		paintState(g);//����״̬ͼ
		
	}
	public void action(){
//		������������
		MouseAdapter l = new MouseAdapter() {

//			����ƶ�
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING){//����״̬
					int x =e.getX();
					int y =e.getY();//��ȡ����x��y
					hero.moveTo(x,y);
				}
			}
		
			
			
			//	�����
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch (state) {
				case START://
					state = RUNNING;////����״̬
					break;
				case GAME_OVER://����
					Checkpoints.score = 0;//
					hero = new Hero();//���µ���
					flyings = new FlyingObject[0]; //��������������
					bullets = new Bullet[0];//�ӵ���������
					state = START;//����״̬
					break;
			
				}
				
			}


//   	����Ƴ�
			@Override
			public void mouseExited(MouseEvent e) {
				if(state==RUNNING){
					state=PAUSE;//��ͣ
				}
			}


//	   	�������
			
			public void mouseEntered(MouseEvent e) {
				if(state==PAUSE){
					state=RUNNING;//����
				}
			}

		
		};
//		����������¼�
		this.addMouseListener(l);
//		������껬���¼�
		this.addMouseMotionListener(l);
		
//		���ö�ʱ��
		Timer t = new Timer();
//		����  ��дTimerTask   �е� run ����
		t.schedule(new TimerTask() {
			
			public void run() {
				if(state==RUNNING){
//					checkImg();//���ͼƬ
					enterAction();//���л�
					stepAction();//��������
					shootAction();//���ӵ�
					outOfAction();//Խ������� ɾ��
					checkHitAction();//	����ӵ�������Ƿ�����ײ
					hitAction();//Ӣ�ۻ�������ײ
					Checkpoints.Checkpoints();//�жϹؿ�
					checkGameOverAction();//�ж���Ϸ�Ƿ����
				}
				
//				����Hero��repanint����
				repaint();//��Ӣ�ۻ�
				
				
				
			}
		}, 10,10);
	}
//	����ӵ�������Ƿ�����ײ
	public void checkHitAction(){
		for(int i = 0; i<bullets.length;i++){
			Bullet b = bullets[i];
			check(b);
		}
		
	}
	

	public void check(Bullet b){
		int index = -1;
		for(int i = 0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(f.shootBy(b)){
				index = i ;//
				break;
			}
		}
		if(index != -1){
			FlyingObject f = flyings[index];
			if(f instanceof Enemy){
				Enemy e =  (Enemy)f;
				Checkpoints.score += e.getScore();
				
			}
			if (f instanceof Award){
				Award a = (Award)f;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					
				
				}
			
			}
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length-1];//�����Ƴ�
			flyings[flyings.length-1] = t;
//			����
			flyings = Arrays.copyOf(flyings, flyings.length-1);
		}
	}
//	
	public void paintSL(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(new Font("����",3,16));
		g.drawString("������" + Checkpoints.score, 10, 20);
		g.drawString("������" + hero.getLife(), 10, 40);
		g.drawString("������" + hero.doubleFire, 10, 60);
		g.drawString("�ؿ���" + Checkpoint[Checkpoints.ckt], 10, 80);
	}
//	��Ӣ�۶��󷽷�
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
//	�����˷���
	public void paintFlying(Graphics g){
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
//	���ӵ�����
	public void paintBullsets(Graphics g){
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}
//	��״̬
	public void paintState(Graphics g){
		
		switch (state) {//��ͬ״̬��ͬͼ
		case START:
			
			
			g.drawImage(logoImg, 0, 0, null);
			g.drawImage(logoImg1,45, Game.HEIGHT/3,300,100, null);
			
			g.drawImage(play,50,Game.HEIGHT/2, 300,100,null);
//			g.drawImage(zty, 0, 0, 40,50,null);
			
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;

		
		}
	}

// �����볡
	int flyIndex = 0;//�����볡����
	
	public void enterAction(){
		flyIndex++;
//		С��ȡ�����л�
		if (flyIndex%40==0) {
			
			FlyingObject obj = nextOne();//��ȡ���˶���
//			��ȡ�л�����
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = obj; 
		}
	}
//	���ɵл�����
	public FlyingObject nextOne(){
//		�������������
		Random rand = new Random();
//		�������0-19
		int type = rand.nextInt(50);
//		��������С��4 ���۷� ���� ���л�
		if(type<4){
			return new Bee();
		}else{	
			
			return new AirPlane(); 			
		}
		
	}
	
//	������л� �۷� �ӵ���
	public void stepAction(){
		hero.step();
		for(int i= 0;i<flyings.length;i++){
			flyings[i].step();//����
		}
		
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();//�ӵ�
		}
		
		
		
	}
//	�ӵ��볡
	int shootIndex = 0;//�������
	public void shootAction(){
		shootIndex++;
		if(shootIndex%30==0){
			Bullet[] bs = hero.shoot();//��ȡ�ӵ�����
			
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);//����
			
			System.arraycopy(bs, 0, bullets,bullets.length - bs.length,bs.length);//����׷��
			
		}
	}
//	Խ������� ɾ�� 
	public void outOfAction(){
		int index = 0;//��Խ��������±�
		
		FlyingObject[] lives = new FlyingObject[flyings.length];//��Խ���������
		
		for(int i = 0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOf()){ // Խ�� true  ��Խ�緵��false
				//����Խ��ĵ��˶�����ӵ���ĵ���������
				lives[index] = f;
				index++;
			}
			
		}
//		����Խ��ĵ������鸴�Ƶ�flyings  ����Ϊindex����Խ�������
		flyings = Arrays.copyOf(lives, index);
		
		index = 0;
		
		Bullet[] bulives = new Bullet[bullets.length];
		
		for(int i=0;i<bullets.length;i++){
//			��ȡÿ���ӵ�����
			Bullet b = bullets[i];
			if(!b.outOf()){
				bulives[index] = b;
				index++;
			}
		}
		bullets = Arrays.copyOf(bulives, index);	
		
	}
//	Ӣ�ۻ�������ײ
	public void hitAction(){
		for(int i =0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(hero.hit(f)){
				
				hero.subLife();//�����
				hero.Blowup();//���������
				hero.subDoubleFire();//�������
				
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
				
			}
		}
	}
//	�ж���Ϸ����
	public void checkGameOverAction(){
		if(hero.getLife()<=0){
			state = GAME_OVER;
			
		}
	}

	
//	���ͼƬ
//	public void checkImg(Graphics g){
//		if(state == START){
//			g.drawImage(logoImg, 0, 0, null);
//		}
//	}
	
}
