package Aspect_logistics_game;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;



public class logistics_game {
	public static final float interval = 1.0f;
	
	public static final int circleNum = 9;//ノード数
	public static final int circleSize = 100;
	public static final int[] circleposx = {10, 200,400,500,650,700,800,950,1100}; // ノードの位置
	public static final int[] circleposy = {600,550,200,400,600,50 ,300,550 ,400};
	
	public static final int passNum = 10;//経路の経由地点と移動コスト
	public static final int highCostPassNum = 2;
	public static final int[] node1 = {0,1,3,3,3,6,4,7,2,6};
	public static final int[] node2 = {1,3,2,4,6,5,7,8,5,8};
	public static final int[] cost  = {1,1,1,1,1,1,1,1,5,5};
	
	//荷物タスクの一覧
	public static final int cargoNum = 10;
	public static final int[] cargoDepe = {0 ,0 ,1 ,3 ,2 ,6 ,5 ,4 ,7 ,8 };
	public static final int[] cargoDest = {2 ,8 ,0 ,5 ,7 ,8 ,2 ,5 ,6 ,1 };
	public static final int[] cargoSize = {10,10,10,10,10,10,10,10,10,10};
	
	
	public static final int truckNum = 3; // トラック数
	public static final int truckDefaultSize = 10;
	
	static JFrame1 frame;
	
	public static List<cargoTask> cargoList;//オブジェクトのリスト
	public static List<truckUnit> truckList;
	public static List<transportNode> nodeList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//各クラスのインスタンス生成
		nodeList = new ArrayList<>(passNum);
		truckList = new ArrayList<>(truckNum);
		cargoList = new LinkedList<>();

		for(int i = 0;i < passNum;i++) {//ノードの生成
			nodeList.add(new transportNode(i));
		}
		
		for(int i = 0;i < passNum;i++) {
			nodeList.get(node1[i]).pass.add(new nodePass(node2[i],node1[i],cost[i]));
			nodeList.get(node2[i]).pass.add(new nodePass(node1[i],node2[i],cost[i]));
		}
		
		for(int i =0;i<truckNum;i++) {//トラックの生成
			truckList.add(new truckUnit(i,0));
		}
		
		for(int i = 0;i<cargoNum;i++){//荷物の生成
			cargoTask cargo = new cargoTask(cargoDest[i],cargoDepe[i],cargoSize[i],i);
			cargoList.add(cargo);
			nodeList.get(cargoDepe[i]).cargoList.add(cargo);
		}
		
		
		EventQueue.invokeLater(new Runnable() {// フレームの生成と描画
			public void run() {
				try {
					frame = new JFrame1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//以下に配送アルゴリズムを実装する。今回は作成しなかった。
		/*
		while(true) {
			for(truckUnit i:truckList){
				for(cargoTask j : nodeList.get(i.position).cargoList){
					if(i.size - i.loadRate > j.size){
						
					}
				}
			}
		}*/

	}

}
