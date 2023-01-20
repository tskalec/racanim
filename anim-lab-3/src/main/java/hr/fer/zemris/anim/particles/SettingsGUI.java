package hr.fer.zemris.anim.particles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import processing.core.PApplet;

public class SettingsGUI extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	public SettingsGUI () {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocation(10, 10);
		setSize(250, 120);
		initGUI();
		
		setLocationRelativeTo(null);
	}

	private void initGUI() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		this.setTitle("Settings");
		
		// difficulty
		JPanel settingsPanel = new JPanel();
		
		settingsPanel.setBackground(Color.decode("0xf28e1c"));
		
		ButtonGroup difficultySettings = new ButtonGroup();
		
		JRadioButton easy = new JRadioButton("EASY");
		JRadioButton medium = new JRadioButton("MEDIUM");
		JRadioButton hard = new JRadioButton("HARD");
		
		easy.setBackground(Color.decode("0xf28e1c"));
		medium.setBackground(Color.decode("0xf28e1c"));
		hard.setBackground(Color.decode("0xf28e1c"));
		easy.setForeground(Color.WHITE);
		medium.setForeground(Color.WHITE);
		hard.setForeground(Color.WHITE);

		difficultySettings.add(easy);
		difficultySettings.add(medium);
		difficultySettings.add(hard);
		
		medium.setSelected(true);
		
		settingsPanel.add(easy);
		settingsPanel.add(medium);
		settingsPanel.add(hard);
		
		getContentPane().add(settingsPanel, BorderLayout.NORTH);
		
		// start
		JToggleButton start = new JToggleButton("START");
		
		start.setBackground(Color.decode("0x555555"));
		start.setForeground(Color.WHITE);
		
		start.setPreferredSize(new Dimension (250, 50));
		
		start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
            	if (easy.isSelected()) {
            		BottomsUp.settings.setMinVelocity(0.1f);
            		BottomsUp.settings.setMaxVelocity(0.5f);
            		BottomsUp.settings.setPercent(1);
            	} else if (hard.isSelected()) {
            		BottomsUp.settings.setMinVelocity(0.5f);
            		BottomsUp.settings.setMaxVelocity(2.0f);
            		BottomsUp.settings.setPercent(5);
            	} else {
            		BottomsUp.settings.setMinVelocity(0.2f);
            		BottomsUp.settings.setMaxVelocity(1.0f);
            		BottomsUp.settings.setPercent(3);
            	}
            	
            	String[] title = {"Bottoms up"};
        		BottomsUp syst = new BottomsUp();
        		PApplet.runSketch(title, syst);
        		dispose();
            }
            
        });
		
		settingsPanel.add(start);
		
		getContentPane().add(settingsPanel, BorderLayout.CENTER);
		
	}

}
