package game;


/*
 * 
 * �ӵ�
 * 
 * */

public class Bullet extends FlyingObject{
//	�ٶ�
	private ChecKpoint Checkpoints = new ChecKpoint();
	 
	
	public Bullet(int x,int y){
		if(Checkpoints.ckt>=1){
			speed = Checkpoints.ckt*2;
			System.out.println("�ӵ��ٶȣ�"+speed);
		}else {
			
				speed = 0;
			
		}
//		ͼƬ
		image = Game.bullet;
//		�ӵ���С λ��
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	
	}
	public void step(){
		y-=speed;//������
	}
	
	public boolean outOf() {
		
		return this.y <= -this.height;
	}
	
	
}
