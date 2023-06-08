package Aspect_logistics_game;

public class cargoTask {//貨物クラス。目的地、サイズの情報を持つ
	int id;
	int dest;
	int depe;
	int size;
	
	int position;
	
	truckUnit truckOn;
	
	cargoTask(int dest,int depe,int size,int id){
		this.dest = dest;
		this.depe = depe;
		this.size = size;
		position = depe;
	}
	
	public int cargoPosX() {//位置x座標を返す
		return logistics_game.circleposx[position];
	}
	
	public int cargoPosY() {//位置y座標を返す
		return logistics_game.circleposy[position];
	}
	
}
