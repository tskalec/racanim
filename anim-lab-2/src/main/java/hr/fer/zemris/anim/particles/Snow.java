package hr.fer.zemris.anim.particles;

import processing.core.*;
import java.util.ArrayList;

/**
 * literatura:
 * https://processing.org/tutorials/
 * 
 * Modelira snijeg; utjecaj gravitacije i vjetra (zadatak 2.4.3).
 */

public class Snow extends PApplet {

	/** sustav čestica */
	ParticleSystem ps;
	
	/** broja palih pahuljica */ 
	int fallen = 0;

	/** mijenja metodu setup alata Processing */
	public void settings() {
		ps = new ParticleSystem(new PVector(random(0, width), 0));
		size(720, 540);
	}
	
	/** metoda koja pokreće crtanje */
	public void draw() {
		background(0);
		ps.addParticle();
		ps.run();
	}

	/** modeliranje čestice */
	class Particle {

		/** pozicija */
		PVector position;
		
		/** brzina */ 
		PVector velocity;
		
		/** akceleracija */
		PVector acceleration;
	
		/** čestica */
		Particle(PVector l) {
			acceleration = new PVector(0, 0.00981f);
			velocity = new PVector(random(-1, 1), random(-2, 0));
			position = l.copy();
		}
	
		/** korak u prikazu */ 
		public void update() {

			if (position.y > height - fallen/100000) {
				velocity = new PVector(0, 0);
				acceleration = new PVector(0, 0);
				fallen++;
			} else {
				velocity.add(acceleration);
				position.add(velocity);
				
				// value to remap, start, stop, new_start, new_stop
				float lateralMouseMovement = map(mouseX, 0, width, -2f, 2f);
				
				PVector wind = new PVector(lateralMouseMovement, 0);
				position.add(wind);
			}
			
			// pahuljica
			ellipse(position.x, position.y, 4, 4);
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
			particles = new ArrayList<Particle>();
		}
	
		/** dodavanje čestice */ 
		public void addParticle() {
			origin = new PVector(random(0, width), 0);
			particles.add(new Particle(origin));
		}
	
		/** pokretanje čitavog sustava */ 
		public void run() {
			for (int i = 0; i < particles.size(); i++) {
				Particle p = particles.get(i);
				p.update();
			}
		}
	}

	/** glavni program */ 
	public static void main(String[] args) {
		String[] title = {"Snow"};
		Snow syst = new Snow();
		PApplet.runSketch(title, syst);
	}
}
