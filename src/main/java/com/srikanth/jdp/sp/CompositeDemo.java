package com.srikanth.jdp.sp;

import java.util.List;
import java.util.ArrayList;
/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/6/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompositeDemo {
    /**
     * The intent of this pattern is to compose objects into tree structures to represent part-whole hierarchies.
     * Composite lets clients treat individual objects and compositions of objects uniformly.
     *
     */
	private CompositeDemo() {}
	
    public static void main(String... args) {
        List<Shape> allShapesInSoftware = new ArrayList<Shape>();
       // create a line shape
        Shape lineShape = new Line(0,0,1,1);
        // add it to the shapes
        allShapesInSoftware.add(lineShape);
        // create a rectangle shape
        Shape rectangelShape = new Rectangle();
        // add it to shapes
        allShapesInSoftware.add(rectangelShape);
        // create a complex shape
        // note that we have dealt with the complex shape
        // not with shape interface because we want
        // to do a specific operation
        // that does not apply to all shapes
        ComplexShape complexShape = new ComplexShape();
        // complex shape is made of the rectangle and the line
        complexShape.addToShape(rectangelShape);
        complexShape.addToShape(lineShape);
        // add to shapes
        allShapesInSoftware.add(complexShape);
        // create a more complex shape which is made of the
        // previously created complex shape
        // as well as a line
        ComplexShape veryComplexShape = new  ComplexShape();
        veryComplexShape.addToShape(complexShape);
        veryComplexShape.addToShape(lineShape);
        allShapesInSoftware.add(veryComplexShape);
        renderGraphics(allShapesInSoftware);
        // you can explode any object
        // despite the fact that the shape might be
        // simple or complex
        Shape[] arrayOfShapes = allShapesInSoftware.get(0).explodeShape();
    }
    private static void renderGraphics(List<Shape> shapesToRender) {
        // note that despite the fact there are
        // simple and complex shapes
        // this method deals with them uniformly
        // and using the Shape interface
        for(Shape s : shapesToRender) {
            s.renderShapeToScreen();
        }
    }
}
//Component Interface
interface Shape {
    public void renderShapeToScreen();
    public Shape[] explodeShape();
}
// The code below illustrates a Simple shape object which is a line, note that this object represents a leaf.
class Line implements Shape {
    /**
     * Create a line between point1 and point2
     */
    public Line(int point1X, int point1Y, int point2X, int point2Y) {
    }
    public Shape[] explodeShape() {
        // making a simple shape explode would return only the shape itself, there are no parts of this shape
        Shape[] shapeParts = {this};
        return shapeParts;
    }
    public void renderShapeToScreen() {
        // logic to render this shape to screen
   }
}
//The code below illustrates a complex object, which is a rectangle object. This object represents a Composite.
class Rectangle implements Shape {
    // List of shapes forming the rectangle
    // rectangle is centered around origin
    Shape[] rectangleEdges = {new Line(-1,-1,1,-1),new Line(-1,1,1,1),new Line(-1,-1,-1,1),new Line(1,-1,1,1)};
    public Shape[] explodeShape() {
        return rectangleEdges;
    }
    public void renderShapeToScreen() {
        for(Shape s : rectangleEdges) {
            // delegate to child objects
            s.renderShapeToScreen();
        }
    }
}
// The code below illustrates a more complex shape object which also represents a composite
class ComplexShape implements Shape {
   List<Shape> shapeList = new ArrayList<Shape>();
    public void addToShape(Shape shapeToAddToCurrentShape) {
        shapeList.add(shapeToAddToCurrentShape);
    }
    public Shape[] explodeShape() {
        return (Shape[]) shapeList.toArray();
    }
    public void renderShapeToScreen() {
        for(Shape s:shapeList) {
            // use delegation to handle this method
            s.renderShapeToScreen();
        }
    }
}