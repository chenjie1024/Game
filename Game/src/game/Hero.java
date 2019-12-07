package game;


import java.awt.image.BufferedImage;

/*
 * 
 * 
 * 小飞机（我方）英雄类
 * 
 * 
 * */
public class Hero extends FlyingObject{
	
	public int doubleFire;//火力值
	public int life;//生命
	public static BufferedImage[] heroImg ;//第一个图片
	public static BufferedImage[] heroImgBlowup ;//第二个图片
	public BufferedImage image;//
	public int index = 0;//图片下标
	
	public Hero(){
//		加载图片
		image = Game.hero0;
//		宽带等于图片宽带
		width = image.getWidth();
		height = image.getHeight();
//		初始位置
		x = 150;
		y = 530;
//		初始火力 生命
		doubleFire = 0;
		life = 3;
		
		heroImg = new BufferedImage[]{Game.hero0,Game.hero1};
		
		heroImgBlowup = new BufferedImage[]{Game.hero2,Game.hero3,Game.hero4};
		
		index = 0;
		
	}

	public void step() {
//		图片切换
		image = heroImg[index++/10%heroImg.length];
		
		
	}
//	移动
	public void moveTo(int x,int y){
		this.x = x -this.width/2;//
		this.y = y -this.height/2;//
	}
//	发射子弹
	public Bullet[] shoot(){
		int xstep = -1+this.width/4;
		int ystep = 20;
		
		if(doubleFire>0){
//			子弹数组   
			Bullet[] bs = new Bullet[2];
			
			bs[0] = new Bullet(this.x+1*xstep-15,this.y-ystep);//英雄4/1的宽度，高度
			bs[1] = new Bullet(this.x+4*xstep-1,this.y-ystep);//英雄3/1的宽度，高度不变
			doubleFire-=2;
			
			return bs;
		}else{
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xstep,this.y-ystep);//英雄4/2宽
			return bs;
		}
		
	}

	public boolean outOf() {
		
		return false;//永不越界
	}
	

//	英雄机增命
	
	public void addLife(){
		life++;
		
	}
//	获取命数
	public int getLife(){
		return life;
		
	}
//	减命
	public void subLife(){
		life--;
		
	}
//	加火力值
	public void addDoubleFire(){
		doubleFire += 40;
		
	}
//	清空火力值
	public void subDoubleFire(){
		doubleFire  = 0;
		
	}
//	判断 飞行物 和 飞机是否发生碰撞
	public boolean hit(FlyingObject f){
		
		int x1 = f.x - this.width/2;
		int x2 = f.x + f.width + this.width/2;
		
		int y1 = f.y - this.height/2;
		int y2 = f.y + f.height - this.height/2;
		
		int x = this.x + this.width/2;
		int y = this.y + this.height/2;
			return x>x1 && x<x2 && y>=y1 && y<=y2;//碰到减分
		
	}
//	两张图片切换
	public void Blowup(){
		image = heroImgBlowup[index++/10%heroImgBlowup.length];
	}
	

}
