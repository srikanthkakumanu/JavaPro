package com.srikanth.jdp.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Animal {
	void describe();
}
class LivingAnimal implements Animal {
	Logger logger = LoggerFactory.getLogger(LivingAnimal.class);
	@Override
	public void describe() {
		logger.info("I am an Animal.");
	}
}
abstract class AnimalDecorator implements Animal {
	Animal animal;
	public AnimalDecorator(Animal animal) {
		this.animal = animal;
	}
}
class LegDecorator extends AnimalDecorator {
	Logger logger = LoggerFactory.getLogger(LegDecorator.class);
	
	public LegDecorator(Animal animal) {
		super(animal);
	}
	@Override
	public void describe() {
		animal.describe();
		logger.info(" I have legs.");
		dance();
	}
	public void dance() {
		logger.info(" I can dance.");
	}
}
class WingDecorator extends AnimalDecorator {
	Logger logger = LoggerFactory.getLogger(WingDecorator.class);
	public WingDecorator(Animal animal) {
		super(animal);
	}
	@Override
	public void describe() {
		animal.describe();
		logger.info(" I have wings.");
		fly();
	}
	public void fly() {
		logger.info(" I can fly.");
	}
}
class GrowlDecorator extends AnimalDecorator {
	Logger logger = LoggerFactory.getLogger(GrowlDecorator.class);
	
	public GrowlDecorator(Animal animal) {
		super(animal);
	}
	@Override
	public void describe() {
		animal.describe();
		growl();
	}
	public void growl() {
		logger.info(" Grrrrr.");
	}
}
