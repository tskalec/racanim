package hr.fer.zemris.anim.particles;

public class Settings {
	
	public int glassWidth;
	public int glassHeight;
	
	public int screenWidth;
	public int screenHeight;
	
	public int initialNumOfDrinks;
	
	public float minVelocity;
	public float maxVelocity;
	
	public int percent;
	
	public int fallenMax;
	
	public Settings() {
		// default values
		glassWidth = 20;
		glassHeight = 30;
		screenWidth = 720;
		screenHeight = 540;
		
		initialNumOfDrinks = 5;
		fallenMax = 20;
		
		// difficulty settings impact the following
		minVelocity = 0.1f;
		maxVelocity = 3.0f;
		percent = 3;
	}

	public int getGlassWidth() {
		return glassWidth;
	}

	public void setDoorWidth(int doorWidth) {
		this.glassWidth = doorWidth;
	}

	public int getGlassHeight() {
		return glassHeight;
	}

	public void setDoorHeight(int doorHeight) {
		this.glassHeight = doorHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getInitialNumOfDoors() {
		return initialNumOfDrinks;
	}

	public void setInitialNumOfParticles(int initialNumOfDoors) {
		this.initialNumOfDrinks = initialNumOfDoors;
	}

	public float getMinVelocity() {
		return minVelocity;
	}

	public void setMinVelocity(float minVelocity) {
		this.minVelocity = minVelocity;
	}

	public float getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(float maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int getFallenMax() {
		return fallenMax;
	}

	public void setFallenMax(int fallenMax) {
		this.fallenMax = fallenMax;
	}
	
}
