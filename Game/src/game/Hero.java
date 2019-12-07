package game;


import java.awt.image.BufferedImage;

/*
 * 
 * 
 * С�ɻ����ҷ���Ӣ����
 * 
 * 
 * */
public class Hero extends FlyingObject{
	
	public int doubleFire;//����ֵ
	public int life;//����
	public static BufferedImage[] heroImg ;//��һ��ͼƬ
	public static BufferedImage[] heroImgBlowup ;//�ڶ���ͼƬ
	public BufferedImage image;//
	public int index = 0;//ͼƬ�±�
	
	public Hero(){
//		����ͼƬ
		image = Game.hero0;
//		�������ͼƬ���
		width = image.getWidth();
		height = image.getHeight();
//		��ʼλ��
		x = 150;
		y = 530;
//		��ʼ���� ����
		doubleFire = 0;
		life = 3;
		
		heroImg = new BufferedImage[]{Game.hero0,Game.hero1};
		
		heroImgBlowup = new BufferedImage[]{Game.hero2,Game.hero3,Game.hero4};
		
		index = 0;
		
	}

	public void step() {
//		ͼƬ�л�
		image = heroImg[index++/10%heroImg.length];
		
		
	}
//	�ƶ�
	public void moveTo(int x,int y){
		this.x = x -this.width/2;//
		this.y = y -this.height/2;//
	}
//	�����ӵ�
	public Bullet[] shoot(){
		int xstep = -1+this.width/4;
		int ystep = 20;
		
		if(doubleFire>0){
//			�ӵ�����   
			Bullet[] bs = new Bullet[2];
			
			bs[0] = new Bullet(this.x+1*xstep-15,this.y-ystep);//Ӣ��4/1�Ŀ�ȣ��߶�
			bs[1] = new Bullet(this.x+4*xstep-1,this.y-ystep);//Ӣ��3/1�Ŀ�ȣ��߶Ȳ���
			doubleFire-=2;
			
			return bs;
		}else{
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xstep,this.y-ystep);//Ӣ��4/2��
			return bs;
		}
		
	}

	public boolean outOf() {
		
		return false;//����Խ��
	}
	

//	Ӣ�ۻ�����
	
	public void addLife(){
		life++;
		
	}
//	��ȡ����
	public int getLife(){
		return life;
		
	}
//	����
	public void subLife(){
		life--;
		
	}
//	�ӻ���ֵ
	public void addDoubleFire(){
		doubleFire += 40;
		
	}
//	��ջ���ֵ
	public void subDoubleFire(){
		doubleFire  = 0;
		
	}
//	�ж� ������ �� �ɻ��Ƿ�����ײ
	public boolean hit(FlyingObject f){
		
		int x1 = f.x - this.width/2;
		int x2 = f.x + f.width + this.width/2;
		
		int y1 = f.y - this.height/2;
		int y2 = f.y + f.height - this.height/2;
		
		int x = this.x + this.width/2;
		int y = this.y + this.height/2;
			return x>x1 && x<x2 && y>=y1 && y<=y2;//��������
		
	}
//	����ͼƬ�л�
	public void Blowup(){
		image = heroImgBlowup[index++/10%heroImgBlowup.length];
	}
	

}
