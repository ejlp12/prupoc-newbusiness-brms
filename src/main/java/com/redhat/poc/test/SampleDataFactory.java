package com.redhat.poc.test;

public class SampleDataFactory {
	
	public static final int GENERATOR_DUMMY_POJO = 1;
	public static final int GENERATOR_FROM_FILE = 2;
	
	public static int generatorType = 1;
	
	public static SampleDataGeneratorInf getGenerator() {
		
		SampleDataGeneratorInf gen = null;
		switch (generatorType) {
		case GENERATOR_DUMMY_POJO:
			gen  = new SampleDataGenerator();
			break;
		case GENERATOR_FROM_FILE:
			gen = new SampleDataFromFileGenerator();
			break;
		default:
			gen  = new SampleDataGenerator();
			break;
		}
		
		return gen;
	}

	public static int getGeneratorType() {
		return generatorType;
	}

	public static void setGeneratorType(int generatorType) {
		SampleDataFactory.generatorType = generatorType;
	}
	
	
}
