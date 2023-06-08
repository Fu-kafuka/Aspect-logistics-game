package Aspect_logistics_game;

import java.util.ArrayList;
import java.util.List;

public class transportNode {//配送の出発・経由・目的地点を示すクラス。隣接地点の情報を持つ

	int id;
	List<nodePass> pass;
	List<cargoTask> cargoList;
	
	transportNode(int id){
		this.id = id;
		pass = new ArrayList<>(3);
		cargoList = new ArrayList<>();
	}
	
	public void unLoadCargo(cargoTask cargo) {//貨物荷下ろし。おろされる側なのでadd
		cargoList.add(cargo);
	}
	
	public void loadCargo(cargoTask cargo) {//貨物積み込み。荷物がいなくなるのでremove
		for(int i = 0;i < cargoList.size();i++) {
			if (cargoList.get(i).id == cargo.id) {
				cargoList.remove(i);
			}
		}
	}
	
}

class nodePass{//近隣地点へのパス
	int dest;
	int start;
	int cost;
	
	nodePass(int dest, int start, int cost){
		this.dest=dest;
		this.start =start;
		this.cost = cost;
	}
}