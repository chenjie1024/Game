package game;


/*
 * 
 * 子弹
 * 
 * */

public class Bullet extends FlyingObject{
//	速度
	private ChecKpoint Checkpoints = new ChecKpoint();
	 
	
	public Bullet(int x,int y){
		if(Checkpoints.ckt>=1){
			speed = Checkpoints.ckt*2;
			System.out.println("子弹速度："+speed);
		}else {
			
				speed = 0;
			
		}
//		图片
		image = Game.bullet;
//		子弹大小 位置
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	
	}
	public void step(){
		y-=speed;//向上走
	}
	
	public boolean outOf() {
		
		return this.y <= -this.height;
	}
	
	
}
