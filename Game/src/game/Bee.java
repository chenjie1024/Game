package game;

import java.util.Random;
/*
 * 
 * 蜜蜂 方法 
 * 
 * */
public class Bee extends FlyingObject implements Award{
	
	private int xSpeed = 1;
	private int ySpeed = 2;
	
	private int awardType;
	
	public Bee(){
		
		Random rand = new Random();
		
		awardType = rand.nextInt(2);
		
		if(awardType==0){
			image = Game.prop_type;
		}else{
			image = Game.bee;
		}
		
//		获取图片自己的大小
		width = image.getWidth();
		height = image.getHeight();
//		width = 40;
//		height = 50;
//		取随机数
		x = rand.nextInt(Game.WIDTH - this.width);
		y = - this.height;
		
	}
	//取奖励类型
	public int getType(){
		return awardType;
	}
	//飞行方向
	public void step() {
		x+=xSpeed;//向左或向右
		y+=ySpeed;//向下
		if(x>=Game.WIDTH-this.width){
			xSpeed = -1;
		}
		if(x<=0){
			xSpeed = 1;
		}
	}
	//判断越界
	public boolean outOf() {
		
		return y>=Game.HEIGHT;
	}
	
}

