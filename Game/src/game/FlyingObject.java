package game;

import java.awt.image.BufferedImage;
/*
 * 
 * 飞行物的共性累  
 * 抽象类 （每个事物是不同的 英雄类 向上飞  敌机 向下 子弹上下）
 * 		 都有自己的图片 宽高 
 * 
 * */
public abstract class FlyingObject {
//	描述飞机的共性内容
//	图片   protected 受保护的
	protected BufferedImage image;
//	宽高坐标
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	
	protected int speed;
//	移动方法
	public abstract void step();
		
//	检测飞行类是否出界
	public abstract boolean outOf();

//	检测敌人是否被子弹击中
	public boolean shootBy(Bullet b){
		int x1 = this.x;
		int x2 = this.x+this.width;
		
		int y1 = this.y;
		int y2 = this.y+this.height;
		
		int x = b.x;
		int y = b.y;
		return x>=x1 && x<=x2 && y>=y1 && y<=y2;
	}
}


