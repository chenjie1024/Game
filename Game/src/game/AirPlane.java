package game;

import java.util.Random;
/*
 * 
 * 
 * 小飞机（敌）   继承飞行类（飞行物）          实现接口 奖励 获得奖励 
 * 
 * */
public class AirPlane extends FlyingObject implements Enemy{
	private ChecKpoint Checkpoints = new ChecKpoint();
	//	移动速度
	 
//	飞机一旦创建就有自己的累类型
	public AirPlane(){
//		获取图片
		System.out.println("速度："+Checkpoints.ckt);
		
		if(Checkpoints.ckt>=1){
			Checkpoints.ckt++;
			speed += Checkpoints.ckt*2;
			System.out.println("敌机速度："+speed);
		}else {
			
				speed = 0;
			
		}
		image = Game.airplane;
//		获取图片自己的大小
		width = image.getWidth();
		height = image.getHeight();
//		取随机数 随机小飞机飞行
		Random rand = new Random();
//		随机小飞机飞行位置   游戏宽高减去小飞机的宽高
		x = rand.nextInt(Game.WIDTH - this.width);
		y = -this.height;
		
	}
//	重写得分方法 击败得分
	public int getScore(){
		return 66;
	}

	//	重写移动方法  从上往下   y增大
	public void step() {
		y+=speed;//向下
		
	
	}
	//出界方法
	public boolean outOf() {
		
		
		return this.y>=Game.HEIGHT;
		
	}
	

}

