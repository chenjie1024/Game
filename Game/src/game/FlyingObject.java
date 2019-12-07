package game;

import java.awt.image.BufferedImage;
/*
 * 
 * ������Ĺ�����  
 * ������ ��ÿ�������ǲ�ͬ�� Ӣ���� ���Ϸ�  �л� ���� �ӵ����£�
 * 		 �����Լ���ͼƬ ��� 
 * 
 * */
public abstract class FlyingObject {
//	�����ɻ��Ĺ�������
//	ͼƬ   protected �ܱ�����
	protected BufferedImage image;
//	�������
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	
	protected int speed;
//	�ƶ�����
	public abstract void step();
		
//	���������Ƿ����
	public abstract boolean outOf();

//	�������Ƿ��ӵ�����
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


