package Aspect_logistics_game;

import java.util.List;
import java.util.ArrayList;

public class truckUnit{//トラックオブジェクト。積載する貨物の情報を持つ。
	public int id;
	public int position;
	public int size;
	public int loadRate;
	
	public List<cargoTask> cargoList;
	
	
	truckUnit(int id, int initialPosition, int size){
		this.id = id;
		position = initialPosition;
		this.size = size;
		cargoList = new ArrayList<>();
		loadRate = 0;
	}
	
	truckUnit(int id, int initialPosition){
		this(id,initialPosition,logistics_game.truckDefaultSize);
	}
	
	public int truckPosX() {//位置x座標を返す
		return logistics_game.circleposx[position];
	}
	
	public int truckPosY() {//位置y座標を返す
		return logistics_game.circleposy[position];
	}
	
	public void loadCargo(cargoTask cargo) {//貨物積み込み
		cargoList.add(cargo);
	}
	
	public void unLoadCargo(cargoTask cargo) {//貨物荷下ろし
		for(int i = 0;i < cargoList.size();i++) {
			if (cargoList.get(i).id == cargo.id) {
				cargoList.remove(i);
			}
		}
	}
}
