package game;
/**
 * �ؿ��� �÷����ж�
 * @author Administrator
 *
 */
public class ChecKpoint {
	
	
	public int score = 0;//����
	public int ckt = 1;//�ؿ���������
	
	//	�ؿ�
	public int Checkpoints(){
		if(score>=500){
			
			ckt=2;
		}
		if(score>=1000){
			
			ckt=3;
		}
		if(score>=1500){
			
			ckt=4;
		}
		if(score>=3000){
			
			ckt=0;
		}
		return ckt;
	}
	
}
