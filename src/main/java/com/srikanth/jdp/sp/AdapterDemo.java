package com.srikanth.jdp.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/6/13
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdapterDemo {
	private static Logger logger = LoggerFactory.getLogger(AdapterDemo.class);
    /**
     * Adapter pattern is adapting between classes and objects. In the adapter pattern, a wrapper class
     * (ie, the adapter) is used translate requests from it to another class (ie, the adaptee). In effect,
     * an adapter provides particular interactions with an adaptee that are not offered directly by the adaptee.
     * The adapter pattern takes two forms. In the first form, a "class adapter" utilizes inheritance.
     * In the second form, an "object adapter" utilizes composition. The object adapter contains an adaptee
     * and implements the target interface to interact with the adaptee.
     * The Two-Ways Adapters are adapters that implements both interfaces of Target and Adaptee.
     * Intent:
     * Convert the interface of a class into another interface clients expect
     * Adapter lets classes work together, that could not otherwise because of incompatible interfaces
     * The classes/objects participating in adapter pattern:
     * Target - defines the domain-specific interface that Client uses.
     * Adapter - adapts the interface Adaptee to the Target interface.
     * Adaptee - defines an existing interface that needs adapting.
     * Client - collaborates with objects conforming to the Target interface.
     */
	private AdapterDemo() {}
	
    public static void main(String... args) {
        // class adapter
        logger.info("class adapter test");
        TemperatureInfo tempInfo = new TemperatureClassReporter();
        testTempInfo(tempInfo);

        // object adapter
        logger.info("\nobject adapter test");
        tempInfo = new TemperatureObjectReporter();
        testTempInfo(tempInfo);
    }
    public static void testTempInfo(TemperatureInfo tempInfo) {
        tempInfo.setTemperatureInC(0);
        logger.info("temp in C:" + tempInfo.getTemperatureInC());
        logger.info("temp in F:" + tempInfo.getTemperatureInF());

        tempInfo.setTemperatureInF(85);
        logger.info("temp in C:" + tempInfo.getTemperatureInC());
        logger.info("temp in F:" + tempInfo.getTemperatureInF());
    }
}
// Adaptee class
class CelciusReporter {

    protected double temperatureInC;

    public void setTemperature(double temperatureInC) {
        this.temperatureInC = temperatureInC;
    }
    public double getTemperature() {
        return temperatureInC;
    }
}
// Here is our target interface that will be implemented by our adapter. It defines actions that our adapter will perform.
interface TemperatureInfo {
    public double getTemperatureInF();
    public void setTemperatureInF(double temperatureInF);
    public double getTemperatureInC();
    public void setTemperatureInC(double temperatureInC);
}
// example of a class adapter
//TemperatureClassReporter is a class adapter. It extends CelciusReporter (the adaptee) and implements
// TemperatureInfo (the target interface).
class TemperatureClassReporter extends CelciusReporter implements TemperatureInfo {

	public double getTemperatureInC() {
		return temperatureInC;
	}
    public double setTemperatureInC() {
        return temperatureInC;
    }
    public double getTemperatureInF() {
        return cToF(temperatureInC);
    }
    public void setTemperatureInC(double temperatureInC) {
        this.temperatureInC = temperatureInC;
    }
    public void setTemperatureInF(double temperatureInF) {
        this.temperatureInC = fToC(temperatureInF);
    }
    private double fToC(double f) {
        return ((f - 32) * 5 / 9);
    }
    private double cToF(double c) {
        return ((c * 9 / 5) + 32);
    }
}
//TemperatureObjectReporter is an object adapter. It is similar in functionality to TemperatureClassReporter,
//except that it utilizes composition for the CelciusReporter rather than inheritance.
// example of an object adapter
class TemperatureObjectReporter implements TemperatureInfo {

    CelciusReporter celciusReporter;

    public TemperatureObjectReporter() {
        celciusReporter = new CelciusReporter();
    }
    public double getTemperatureInC() {
        return celciusReporter.getTemperature();
    }
    public double getTemperatureInF() {
        return cToF(celciusReporter.getTemperature());
    }
    public void setTemperatureInC(double temperatureInC) {
        celciusReporter.setTemperature(temperatureInC);
    }
    public void setTemperatureInF(double temperatureInF) {
        celciusReporter.setTemperature(fToC(temperatureInF));
    }
    private double fToC(double f) {
        return ((f - 32) * 5 / 9);
    }
    private double cToF(double c) {
        return ((c * 9 / 5) + 32);
    }
}