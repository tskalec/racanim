package hr.fer.zemris.anim.particles;

import processing.core.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class BottomsUp extends PApplet {
	
	public static Settings settings;
	
	public static boolean clicked = false;

	/** sustav čestica */
	ParticleSystem system;
	
	/** broja palih čaša */ 
	int currentFallenTotal = 0;
	
	/** broj punih */
	int fullTotal = 0;
	
	/** broj praznih */
	int drunkTotal = 0;
	
	/** ukupno u igri */
	int drinksTotal = 0;
	
	/** jesu li čaše pale (dosegla dno) **/
	boolean fallen = false;
	
	PImage bg;

	/** mijenja metodu setup alata Processing */
	public void settings() {
		system = new ParticleSystem();
		system.initialize();
		size(settings.getScreenWidth(), settings.getScreenHeight());
		//bg = loadImage("bar.png");
	}
	
	/** metoda koja pokreće crtanje */
	public void draw() {
		background(Math.max(200 - drunkTotal * 5, 20));
		system.run();
		//background(bg);
		if (random(0,100) < settings.getPercent()) {
			system.addBeer(new PVector(random (0, settings.getScreenWidth()), settings.getScreenHeight()));
		}
		
	}
	
	/** detekcija klika mišem */
	public void mousePressed() {
		clicked = true;
	}

	/** modeliranje pića */
	class Beer {

		/** pozicija */
		PVector position;
		
		/** brzina */ 
		PVector velocity;
		
		/** akceleracija */
		PVector acceleration;
		
		/** intenzitet crvene boje u rgb sustavu*/
		int red;
		
		/** intenzitet crvene boje u rgb sustavu*/
		int green;
		
		/** intenzitet crvene boje u rgb sustavu*/
		int blue;
		
		/** čestica */
		Beer(PVector l) {
			acceleration = new PVector(0, -0.00981f);
			velocity = new PVector(0, -random(settings.getMinVelocity(), settings.getMaxVelocity()));
			position = l.copy();
			red = 242;
			green = 142;
			blue = 28;
		}
	
		/** korak u prikazu */ 
		public void update() {
			
			if (position.y < 25) {
				fallen = true;
				
				if (currentFallenTotal > settings.getFallenMax()-1) {
					
					SwingUtilities.invokeLater(() -> {
						new ResultsGUI(fullTotal, drunkTotal).setVisible(true);
						dispose();
					});
					
				}
			} else {
				velocity.add(acceleration);
				position.add(velocity);
			}
			
			// vrata
		
			fill(255,255,255);
			rectMode(CENTER);
			rect(position.x, position.y-10, settings.getGlassWidth(), settings.getGlassHeight());
			fill(red,green,blue);
			rect(position.x, position.y, settings.getGlassWidth(), settings.getGlassHeight());
		}
		
		/** je li čaša pala na dno */ 
		public boolean hasFallen() {
			return fallen;
		}
	}

	/** sustav */ 
	class ParticleSystem {
		
		/** sva pića */
		ArrayList<Beer> drinks;
		
		/** izvor u prostoru */
		PVector origin;
	
		/** konstruktor */ 
		ParticleSystem() {
		}
		
		/** inicijalizacija */ 
		public void initialize() {
			drinks = new ArrayList<Beer>();
			
			int screenWidth = settings.getScreenWidth();
			int numOfParticles = settings.getInitialNumOfDoors();
			int doorWidth = settings.getGlassWidth();
			
			int k = screenWidth / (numOfParticles);
			
			for (int i = 0; i < numOfParticles; i++) {
				addBeer(new PVector(i*k+doorWidth, 540));
			}
			
		}
	
		/** dodavanje pića */ 
		public void addBeer(PVector origin) {
			drinks.add(new Beer(origin));
			fullTotal++;
			drinksTotal++;
		}
	
		/** pokretanje čitavog sustava */ 
		public void run() {
			
			int w = settings.getGlassWidth();
			int h = settings.getGlassHeight();
			currentFallenTotal = 0;
			
			for (Beer d : drinks) {
				
				if (BottomsUp.clicked) {
					
					if (Math.abs(d.position.x - mouseX) * 2 < w && Math.abs(d.position.y - mouseY) * 2 < h) {
						d.red = 255;
						d.green = 255;
						d.blue = 255;
						drunkTotal++;
						fullTotal--;
					}			
				}
				
				if (d.hasFallen()) {
					currentFallenTotal++;
				}
				
				d.update();
			}
			
			clicked = false;
			
		}
	}

	/** glavni program */ 
	public static void main(String[] args) {
		
		settings = new Settings();
		
		SwingUtilities.invokeLater(() -> {
			new SettingsGUI().setVisible(true);
		});
		
	}
}
