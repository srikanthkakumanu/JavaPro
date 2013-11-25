package com.srikanth.jdp.sp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CompositeDemoTest {

	@Test
	public void testCompositePattern() {
	       List<Shape> allShapesInSoftware = new ArrayList<Shape>();
	        Shape lineShape = new Line(0,0,1,1);
	        allShapesInSoftware.add(lineShape);
	        Shape rectangelShape = new Rectangle();
	        allShapesInSoftware.add(rectangelShape);
            
	        ComplexShape complexShape = new ComplexShape();
	        complexShape.addToShape(rectangelShape);
	        complexShape.addToShape(lineShape);
	        allShapesInSoftware.add(complexShape);

	        ComplexShape veryComplexShape = new  ComplexShape();
	        veryComplexShape.addToShape(complexShape);
	        veryComplexShape.addToShape(lineShape);
	        allShapesInSoftware.add(veryComplexShape);
	        CompositeDemo.renderGraphics(allShapesInSoftware);
	        allShapesInSoftware.get(0).explodeShape();
	}

}
