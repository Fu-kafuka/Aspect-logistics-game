package Aspect_logistics_game;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.geom.Ellipse2D;

import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.*;

public class JFrame1 extends JFrame {//メイン画面制御クラス

	private JPanel contentPane;
	private Canvas canvas;
	private mapCanvas mapcanvas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
	public JFrame1() {
		setTitle("Logi_game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		JScrollPane loadingTab = new JScrollPane();
		tabbedPane.addTab("積込", null, loadingTab, null);
		
		JPanel loadingPanel = new JPanel();
		loadingTab.setViewportView(loadingPanel);
		loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.X_AXIS));
		
		JScrollPane unloadingTab = new JScrollPane();
		tabbedPane.addTab("荷降ろし", null, unloadingTab, null);
		
		JPanel unloadingPanel = new JPanel();
		unloadingTab.setViewportView(unloadingPanel);
		unloadingPanel.setLayout(new BoxLayout(unloadingPanel, BoxLayout.X_AXIS));
		
		JScrollPane MoveTab = new JScrollPane();
		tabbedPane.addTab("移動", null, MoveTab, null);
		
		JPanel MovePanel = new JPanel();
		MoveTab.setViewportView(MovePanel);
		MovePanel.setLayout(new BoxLayout(MovePanel, BoxLayout.X_AXIS));
		
		JPanel mapPanel = new JPanel();
		GridBagConstraints gbc_mapPanel = new GridBagConstraints();
		gbc_mapPanel.gridheight = 2;
		gbc_mapPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mapPanel.fill = GridBagConstraints.BOTH;
		gbc_mapPanel.gridx = 1;
		gbc_mapPanel.gridy = 0;
		contentPane.add(mapPanel, gbc_mapPanel);
		mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		mapcanvas = new mapCanvas(); // canvasを継承して独自処理を実施 
		canvas = mapcanvas;
		mapPanel.add(canvas);
		
		JScrollPane cargoStatusScroll = new JScrollPane();
		GridBagConstraints gbc_cargoStatusScroll = new GridBagConstraints();
		gbc_cargoStatusScroll.insets = new Insets(0, 0, 0, 5);
		gbc_cargoStatusScroll.fill = GridBagConstraints.BOTH;
		gbc_cargoStatusScroll.gridx = 0;
		gbc_cargoStatusScroll.gridy = 1;
		contentPane.add(cargoStatusScroll, gbc_cargoStatusScroll);
		
		JPanel cargoStatus = new JPanel();
		cargoStatusScroll.setViewportView(cargoStatus);
		cargoStatus.setLayout(new BoxLayout(cargoStatus, BoxLayout.X_AXIS));
		
		// 以下個別描画処理
		
	}
	
	public void drawMap() {
		
	}
}

class mapCanvas extends Canvas{
	int w = 1280; // 画面サイズ
	int h = 720;
	
	Graphics2D g2d;
	Ellipse2D.Double[] ellipse;
	BufferedImage cImage = null;
	
	BasicStroke basicStroke;
	BasicStroke dottedStroke;
	
	public mapCanvas() {//初期化処理
		cImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		g2d = cImage.createGraphics();// 画像バッファを生成
		g2d.setBackground(Color.white); // バックグラウンドとサイズ設定
		setPreferredSize(new Dimension(w,h));
		
		//アンチエイリアス設定
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//背景画像設定
		g2d.setBackground(Color.white);
		ellipse = new Ellipse2D.Double[logistics_game.circleNum];
		for(int i = 0;i < logistics_game.circleNum;i++) {
			ellipse[i] = new Ellipse2D.Double(logistics_game.circleposx[i], logistics_game.circleposy[i], logistics_game.circleSize, logistics_game.circleSize);
		}
		
		//通常線
		basicStroke = new BasicStroke(3.0f);
		
		//破線
		dottedStroke = new BasicStroke(3.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f,new float[] {4.0f, 2.0f},0.0f); 
	}
	
	@Override
	public void paint(Graphics g) {//フレーム毎の描画処理
		g2d.clearRect(0, 0, w, h);
		g2d.setColor(Color.black);
		
		g2d.setStroke(basicStroke);
		//g2d.setStroke(dottedStroke);
		
		for(Ellipse2D.Double i:ellipse) {
			g2d.draw(i);
		}
		
		//バッファを描画
		g.drawImage(cImage,0,0,null);
	}
}
