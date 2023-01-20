package hr.fer.zemris.anim.particles;

import processing.core.*;
import java.util.ArrayList;

/**
 * literatura:
 * https://processing.org/tutorials/
 * 
 * Modelira vatromet; šarolikost i nestajanje čestica (zadatak 2.4.5)
 * uz stvaranje izvora klikom miša (zadatak 2.4.6).
 */
public class Fireworks extends PApplet {

	/** sustavi čestica */
	ArrayList<ParticleSystem> s;
	
	/** mijenja metodu setup alata Processing */
	public void settings() {
		s = new ArrayList<ParticleSystem>();
		size(720, 540);
	}
	
	/** klikom miša dodaje se novi izvor eksplozije */
	public void mousePressed() {
		s.add(new ParticleSystem(new PVector(mouseX, mouseY)));
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
		
		/** životni vijek */ 
		float lifespan;
		
		/** boja */
		float r, g, b;
		
		/** čestica */ 
		Particle(PVector l, float r, float g, float b) {
			acceleration = new PVector(0, 0.00981f);
			velocity = new PVector(random(-1, 1), random(-2, 0));
			position = l.copy();
			
			lifespan = 255.0f;
			
			this.r = r;
			this.g = g;
			this.b = b;
			
			fill(r, g, b, lifespan);
		}
	
	 	/** korak u prikazu */ 
		public void update() {
			velocity.add(acceleration);
			position.add(velocity);
			
			lifespan -= 1.0f;
			
			fill(r,g,b, lifespan);
			ellipse(position.x, position.y, 4, 4);
		}
	
		/** životni vijek čestice */ 
		public boolean isDead() {
			if (lifespan < 0.0f) {
				return true;
			} return false;
		}
	}

	/** sustav čestica */ 
	class ParticleSystem {

		/** čestice */
		ArrayList<Particle> particles;
		
		/** izvor */
		PVector origin;
		
		/** broj čestica u eksploziji */
		int numOfParticles = 0;
		
		/** boja je ista za cijelu eksploziju */
		float r, g, b;

		/** inicijalizacija */ 
		ParticleSystem(PVector position) {
			origin = position.copy();
			particles = new ArrayList<Particle>();
			
			r = random(20,255);
			g = random(20,255);
			b = random(20,255);
		}
	
		/** dodavanje čestice */ 
		public void addParticle() {
			
			if (numOfParticles < random(10,80)) {
				particles.add(new Particle(origin, r, g, b));
				numOfParticles++;
			}	
		}
	
		/** pokretanje čitavog sustava */
		public void run() {
			for (int i = 0; i < particles.size(); i++) {
				Particle p = particles.get(i);
				p.update();
				
				if (p.isDead()) {
					particles.remove(i);
				}
			}
		}
	}

	/** glavni program */ 
	public static void main(String[] args) {
		String[] title = {"Fireworks"};
		Fireworks syst = new Fireworks();
		PApplet.runSketch(title, syst);
	}
}
