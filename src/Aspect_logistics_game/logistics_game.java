package Aspect_logistics_game;

import java.awt.EventQueue;

import javax.swing.JFrame;



public class logistics_game {
	
	public static final int circleNum = 9;
	public static final int circleSize = 100;
	public static final int[] circleposx = {10, 200,400,500,650,700,800,950,1100}; // ノードの位置
	public static final int[] circleposy = {600,550,200,400,600,0  ,300,550 ,500};
	
	public static final int passNum = 10;//経路の経由地点とコスト
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {// フレームの生成と描画
			public void run() {
				try {
					JFrame1 frame = new JFrame1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

}
