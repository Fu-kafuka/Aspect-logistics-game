package Aspect_logistics_game;

import java.util.List;

public aspect traceData {//アスペクトのファイル。
	/*
	 * 機能
	 * 1.荷物の積み下ろしのタイミングで位置と貨物を告知
	 * 2.荷物とトラックの位置を通知
	 * 
	 * */

	public pointcut loading() : execution(* loadCargo(*)); //積み込み時にログを取りたいのでジョインポイントを設定
	
	after(List cargoList) : loading() && target(cargoList){
		System.out.println("貨物"+Integer.toString(cargoList.get(0).id)+"is at"+Integer.toString(cargoList.get(0).position));
	}
	

	public pointcut unloading() : execution(* loadCargo(*)); //荷下ろし。下す前の地点をとる。
	
	before(List cargoList) : unloading() && target(cargoList){
		System.out.println("貨物"+Integer.toString(cargoList.get(0).id)+"was at"+Integer.toString(cargoList.get(0).position));
	}
	
	public pointcut logPosition() : execution(int *Pos+.(int));
	
	after(int position) : logPosition() && target(position){
		System.out.println("x座標:"+Integer.toString(logistics_game.circleposx[position])+",y座標"+Integer.toString(logistics_game.circleposy[position]));
	}
}
