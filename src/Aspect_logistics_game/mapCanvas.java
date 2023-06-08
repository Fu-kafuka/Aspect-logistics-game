package Aspect_logistics_game;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


import javax.imageio.ImageIO;

class mapCanvas extends Canvas{ //描画用クラス
	final int w = 1280; // 画面サイズ
	final int h = 720;
	
	Graphics2D g2d;
	BufferedImage cImage = null;
	
	BasicStroke basicStroke;
	BasicStroke dottedStroke;
	
	Ellipse2D.Double[] ellipse;
	Line2D.Double[] line2D;
	
	BufferedImage car_truck;
	BufferedImage box_danbo_ru;
	
	final float truckImageScale = 0.3f;
	final float boxImageScale = 0.1f;
	
	
	public mapCanvas() {//初期化処理
		cImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		g2d = cImage.createGraphics();// 画像バッファを生成
		g2d.setBackground(Color.white); // バックグラウンドとサイズ設定
		setPreferredSize(new Dimension(w,h));
		
		//アンチエイリアス設定
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		//通常線
		basicStroke = new BasicStroke(3.0f);
		
		//破線
		dottedStroke = new BasicStroke(3.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f,new float[] {4.0f, 2.0f},0.0f); 
	

		//背景円描画設定
		ellipse = new Ellipse2D.Double[logistics_game.circleNum];
		for(int i = 0;i < logistics_game.circleNum;i++) {
			ellipse[i] = new Ellipse2D.Double(logistics_game.circleposx[i], logistics_game.circleposy[i], logistics_game.circleSize, logistics_game.circleSize);
		}
		
		//背景線描画設定
		line2D = new Line2D.Double[logistics_game.passNum];
		for(int i = 0;i<logistics_game.passNum;i++) {
			line2D[i] = new Line2D.Double(  logistics_game.circleposx[logistics_game.node1[i]] + logistics_game.circleSize/2,
											logistics_game.circleposy[logistics_game.node1[i]] + logistics_game.circleSize/2,
											logistics_game.circleposx[logistics_game.node2[i]] + logistics_game.circleSize/2,
											logistics_game.circleposy[logistics_game.node2[i]] + logistics_game.circleSize/2);
		}
		
		//ノード番号描画設定
		Font font = new Font("Meiryo",Font.PLAIN,20);
		g2d.setFont(font);
		
		//画像読み込み
		String truckPath = "res/car_truck.png";
		try {
			car_truck = ImageIO.read(new File(truckPath));
		} catch(IOException e) {
			e.printStackTrace();
			car_truck = null;
		}
		
		String boxPath = "res/box_danbo-ru_close.png";
		try {
			box_danbo_ru = ImageIO.read(new File(boxPath));
		} catch(IOException e) {
			e.printStackTrace();
			box_danbo_ru = null;
		}
		
	}
	
	@Override
	public void paint(Graphics g) {//フレーム毎の描画処理
		g2d.clearRect(0, 0, w, h);
		g2d.setColor(Color.black);
		
		g2d.setStroke(basicStroke);
		//g2d.setStroke(dottedStroke);
		
		for(Ellipse2D.Double i:ellipse) {//円描画
			g2d.draw(i);
		}
		
		//線描画
		for(int i = 0;i<logistics_game.passNum;i++) {
			if (i+1 > logistics_game.passNum - logistics_game.highCostPassNum) {
				g2d.setStroke(dottedStroke); //破線に切り替え
			}
			g2d.draw(line2D[i]);
		}
		
		//ノード番号描画
		for(int i = 0;i<logistics_game.circleNum;i++) {
			g2d.drawString(Integer.toString(i), logistics_game.circleposx[i], logistics_game.circleposy[i]);
		}
		
		//画像描画
		for(cargoTask i:logistics_game.cargoList) {//荷物描画
			g2d.drawImage(box_danbo_ru,
					i.truckPosX(), i.truckPosY(),
					i.truckPosX()+(int)(box_danbo_ru.getWidth()*boxImageScale), i.truckPosY()+(int)(box_danbo_ru.getHeight()*boxImageScale),
					0, 0,
					box_danbo_ru.getWidth(), box_danbo_ru.getHeight(), null);
		
		}
		
		for(truckUnit i:logistics_game.truckList) {//トラック描画
			g2d.drawImage(car_truck,
					i.truckPosX(), i.truckPosY(),
					i.truckPosX()+(int)(car_truck.getWidth()*truckImageScale), i.truckPosY()+(int)(car_truck.getHeight()*truckImageScale),
					0, 0,
					car_truck.getWidth(), car_truck.getHeight(), null);
		
		}
		

		
		
		//バッファを描画
		g.drawImage(cImage,0,0,null);
	}
}