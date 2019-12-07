package game;

public interface Award {
//	击败蜜蜂获得奖励 不同的奖励
	//	火力
	public int DOUBLE_FIRE = 0;
//	生命
	public int LIFE = 1;
	
//	获取奖励类型
	public int getType();
	
}

