package hr.fer.zemris.anim.particles;

import processing.core.*;
import java.util.ArrayList;

/**
 * literatura:
 * https://processing.org/tutorials/
 * 
 * Modelira konfete; šarolikost (zadatak 2.4.5)
 * uz stvaranje izvora klikom miša (zadatak 2.4.6).
 */ 
public class SlowConfetti extends PApplet {

	/** sustavi čestica */
	ArrayList<ParticleSystem> s;

	/** mijenja metodu setup alata Processing */
	public void settings() {
		s = new ArrayList<ParticleSystem>();
		size(720, 540);
	}

	/** klikom miša dodaje se novi izvor konfeta */
	public void mousePressed() {
		s.add(new ParticleSystem(new PVector(mouseX,mouseY)));
	}

	/** metoda koja pokreće crtanje */
	public void draw() {
		background(0);
		for (ParticleSystem ps : s) {
			ps.addParticle();
			ps.run();
		}
	}

	/** modeliranje čestice */
	class Particle {
		
		/** pozicija */
		PVector position;
		
		/** brzina */ 
		PVector velocity;
		
		/** akceleracija */
		PVector acceleration;
		
		/** svaka čestica je drugačije boje */ 
		int r = (int) random(0,255);
		int g = (int) random(0,255);
		int b = (int) random(0,255);
		
		/** čestica */ 
		Particle(PVector l) {
			acceleration = new PVector(0, -0.00981f);
			velocity = new PVector(random(-1, 1), random(-1, 0));
			position = l.copy();
			fill(r,g,b);
		}
	
	 	/** korak u prikazu */ 
		public void update() {
			velocity.add(acceleration);
			position.add(velocity);

			ellipse(position.x, position.y, 6, 6);
			fill(r,g,b);
		}
	
	}

	/** sustav čestica */ 
	class ParticleSystem {
		
		/** čestice */
		ArrayList<Particle> particles;
		
		/** izvor */
		PVector origin;
	
		/** inicijalizacija */
		ParticleSystem(PVector position) {
			origin = position.copy();
			particles = new ArrayList<Particle>();
		}
	
		/** dodavanje čestice */ 
		public void addParticle() {
			particles.add(new Particle(origin));
		}
	
		/** pokretanje čitavog sustava */ 
		public void run() {
			for (Particle p : particles) {
				p.update();
			}
		}
	}

	/** glavni program */ 
	public static void main(String[] args) {
		String[] title = {"SlowConfetti"};
		SlowConfetti syst = new SlowConfetti();
		PApplet.runSketch(title, syst);
	}
}
