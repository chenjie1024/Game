package game;
/*
 * 可升级 动态背景 开始界面 加入关卡 加入Boss 加背景音乐 
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
	
	//	创建IO流 加入图片
	public static BufferedImage logoImg;//logo图片
	public static BufferedImage bgImg;//背景图片
	public static BufferedImage logoImg1;//背景图片
	
	public static BufferedImage play;//开始
	public static BufferedImage pause;//暂停
	public static BufferedImage gameover;//游戏结束
	
	public static BufferedImage hero0;//英雄机
	public static BufferedImage hero1;//英雄机 
	public static BufferedImage hero2;//英雄机
	public static BufferedImage hero3;//英雄机
	public static BufferedImage hero4;//英雄机 
	
	public static BufferedImage airplane;//敌机
	public static BufferedImage emenmy;
	public static BufferedImage bee;//蜜蜂
	public static BufferedImage prop_type;//蜜蜂
	public static BufferedImage bullet;//子弹
	
	public static BufferedImage zty;//
	
//	创建调用Hero方法函数
	private Hero hero = new Hero();
	
	private ChecKpoint Checkpoints = new ChecKpoint();
	
	private FlyingObject[] flyings = {};//一堆敌人（敌机+小蜜蜂）
	private Bullet[] bullets = {};//一堆子弹
	
	
	public static final int START = 0;//启动状态
	public static final int RUNNING = 1;//运行状态
	public static final int PAUSE = 2;//暂停状态
	public static final int GAME_OVER = 3;//游戏结束
	
	public int state = START;//默认启动状态
	
	
	
	public String[] Checkpoint = new String[]{"结束","第一关","第二关","第三关","第四关","第五关"};
	
	
	
//	main运行一次 方法也也允许一次   匿名类部类
	static{
//		抛异常
		try {
//			IO读入图片
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
			System.out.println("图片错误");
			e.printStackTrace();
			
		}
	}
//	主方法
	public static void main(String[] args) {
//		创建窗口
		JFrame frame = new JFrame("归长安—Java飞机大战");
//		窗口默认关闭   退出应用程序默认窗口关闭操作。
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		设置窗口的大小 单位 PX 
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		
		frame.setLocationRelativeTo(null);
		
		Game g = new Game();
//		加载类
		frame.add(g);
		
		frame.setVisible(true);
		
		g.action();
		
		
		
	}
	
	public void paint(Graphics g){
//		窗口加载背景 logo 图片 
		g.drawImage(bgImg, 0, 0, null);
//		g.drawImage(logoImg, 0, 0, null);
		paintHero(g);
		
		paintFlying(g);
		
		paintBullsets(g);
		
		paintSL(g);
		
		paintState(g);//加载状态图
		
	}
	public void action(){
//		创建鼠标监听器
		MouseAdapter l = new MouseAdapter() {

//			鼠标移动
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING){//运行状态
					int x =e.getX();
					int y =e.getY();//获取鼠标的x和y
					hero.moveTo(x,y);
				}
			}
		
			
			
			//	鼠标点击
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch (state) {
				case START://
					state = RUNNING;////运行状态
					break;
				case GAME_OVER://结束
					Checkpoints.score = 0;//
					hero = new Hero();//重新调用
					flyings = new FlyingObject[0]; //飞行类数组清零
					bullets = new Bullet[0];//子弹数组清零
					state = START;//启动状态
					break;
			
				}
				
			}


//   	鼠标移除
			@Override
			public void mouseExited(MouseEvent e) {
				if(state==RUNNING){
					state=PAUSE;//暂停
				}
			}


//	   	鼠标移入
			
			public void mouseEntered(MouseEvent e) {
				if(state==PAUSE){
					state=RUNNING;//运行
				}
			}

		
		};
//		添加鼠标监听事件
		this.addMouseListener(l);
//		处理鼠标滑动事件
		this.addMouseMotionListener(l);
		
//		启用定时器
		Timer t = new Timer();
//		调用  重写TimerTask   中的 run 方法
		t.schedule(new TimerTask() {
			
			public void run() {
				if(state==RUNNING){
//					checkImg();//清除图片
					enterAction();//画敌机
					stepAction();//画飞行物
					shootAction();//画子弹
					outOfAction();//越界飞行物 删除
					checkHitAction();//	检测子弹与敌人是否发生碰撞
					hitAction();//英雄机发生碰撞
					Checkpoints.Checkpoints();//判断关卡
					checkGameOverAction();//判断游戏是否结束
				}
				
//				调用Hero的repanint方法
				repaint();//画英雄机
				
				
				
			}
		}, 10,10);
	}
//	检测子弹与敌人是否发生碰撞
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
			flyings[index] = flyings[flyings.length-1];//数组移除
			flyings[flyings.length-1] = t;
//			缩容
			flyings = Arrays.copyOf(flyings, flyings.length-1);
		}
	}
//	
	public void paintSL(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(new Font("宋体",3,16));
		g.drawString("分数：" + Checkpoints.score, 10, 20);
		g.drawString("命数：" + hero.getLife(), 10, 40);
		g.drawString("火力：" + hero.doubleFire, 10, 60);
		g.drawString("关卡：" + Checkpoint[Checkpoints.ckt], 10, 80);
	}
//	画英雄对象方法
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
//	画敌人方法
	public void paintFlying(Graphics g){
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
//	画子弹方法
	public void paintBullsets(Graphics g){
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}
//	画状态
	public void paintState(Graphics g){
		
		switch (state) {//不同状态不同图
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

// 敌人入场
	int flyIndex = 0;//敌人入场次数
	
	public void enterAction(){
		flyIndex++;
//		小于取余加入敌机
		if (flyIndex%40==0) {
			
			FlyingObject obj = nextOne();//获取敌人对象
//			获取敌机数组
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = obj; 
		}
	}
//	生成敌机对象
	public FlyingObject nextOne(){
//		调用随机数方法
		Random rand = new Random();
//		随机生成0-19
		int type = rand.nextInt(50);
//		如果随机数小于4 出蜜蜂 否则 出敌机
		if(type<4){
			return new Bee();
		}else{	
			
			return new AirPlane(); 			
		}
		
	}
	
//	飞行物（敌机 蜜蜂 子弹）
	public void stepAction(){
		hero.step();
		for(int i= 0;i<flyings.length;i++){
			flyings[i].step();//敌人
		}
		
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();//子弹
		}
		
		
		
	}
//	子弹入场
	int shootIndex = 0;//射击次数
	public void shootAction(){
		shootIndex++;
		if(shootIndex%30==0){
			Bullet[] bs = hero.shoot();//获取子弹对象
			
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);//扩容
			
			System.arraycopy(bs, 0, bullets,bullets.length - bs.length,bs.length);//数组追加
			
		}
	}
//	越界飞行物 删除 
	public void outOfAction(){
		int index = 0;//不越界飞行物下标
		
		FlyingObject[] lives = new FlyingObject[flyings.length];//不越界敌人数组
		
		for(int i = 0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOf()){ // 越界 true  不越界返回false
				//将不越界的敌人对象添加到活的敌人数组中
				lives[index] = f;
				index++;
			}
			
		}
//		将不越界的敌人数组复制到flyings  长度为index（不越界个数）
		flyings = Arrays.copyOf(lives, index);
		
		index = 0;
		
		Bullet[] bulives = new Bullet[bullets.length];
		
		for(int i=0;i<bullets.length;i++){
//			获取每个子弹对象
			Bullet b = bullets[i];
			if(!b.outOf()){
				bulives[index] = b;
				index++;
			}
		}
		bullets = Arrays.copyOf(bulives, index);	
		
	}
//	英雄机发生碰撞
	public void hitAction(){
		for(int i =0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(hero.hit(f)){
				
				hero.subLife();//清除命
				hero.Blowup();//清除命动画
				hero.subDoubleFire();//清除火力
				
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
				
			}
		}
	}
//	判断游戏结束
	public void checkGameOverAction(){
		if(hero.getLife()<=0){
			state = GAME_OVER;
			
		}
	}

	
//	清除图片
//	public void checkImg(Graphics g){
//		if(state == START){
//			g.drawImage(logoImg, 0, 0, null);
//		}
//	}
	
}
