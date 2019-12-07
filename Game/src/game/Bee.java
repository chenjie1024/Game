package game;

import java.util.Random;
/*
 * 
 * �۷� ���� 
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
		
//		��ȡͼƬ�Լ��Ĵ�С
		width = image.getWidth();
		height = image.getHeight();
//		width = 40;
//		height = 50;
//		ȡ�����
		x = rand.nextInt(Game.WIDTH - this.width);
		y = - this.height;
		
	}
	//ȡ��������
	public int getType(){
		return awardType;
	}
	//���з���
	public void step() {
		x+=xSpeed;//���������
		y+=ySpeed;//����
		if(x>=Game.WIDTH-this.width){
			xSpeed = -1;
		}
		if(x<=0){
			xSpeed = 1;
		}
	}
	//�ж�Խ��
	public boolean outOf() {
		
		return y>=Game.HEIGHT;
	}
	
}

