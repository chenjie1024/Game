package game;

import java.util.Random;
/*
 * 
 * 
 * С�ɻ����У�   �̳з����ࣨ�����          ʵ�ֽӿ� ���� ��ý��� 
 * 
 * */
public class AirPlane extends FlyingObject implements Enemy{
	private ChecKpoint Checkpoints = new ChecKpoint();
	//	�ƶ��ٶ�
	 
//	�ɻ�һ�����������Լ���������
	public AirPlane(){
//		��ȡͼƬ
		System.out.println("�ٶȣ�"+Checkpoints.ckt);
		
		if(Checkpoints.ckt>=1){
			Checkpoints.ckt++;
			speed += Checkpoints.ckt*2;
			System.out.println("�л��ٶȣ�"+speed);
		}else {
			
				speed = 0;
			
		}
		image = Game.airplane;
//		��ȡͼƬ�Լ��Ĵ�С
		width = image.getWidth();
		height = image.getHeight();
//		ȡ����� ���С�ɻ�����
		Random rand = new Random();
//		���С�ɻ�����λ��   ��Ϸ��߼�ȥС�ɻ��Ŀ��
		x = rand.nextInt(Game.WIDTH - this.width);
		y = -this.height;
		
	}
//	��д�÷ַ��� ���ܵ÷�
	public int getScore(){
		return 66;
	}

	//	��д�ƶ�����  ��������   y����
	public void step() {
		y+=speed;//����
		
	
	}
	//���緽��
	public boolean outOf() {
		
		
		return this.y>=Game.HEIGHT;
		
	}
	

}

