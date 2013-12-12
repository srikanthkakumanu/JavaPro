package com.srikanth.simple;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


/**
 * http://www.javabeat.net/2007/06/java-6-0-features-part-2-pluggable-annotation-processing-api/
 * @author Srikanth
 *
 */
public class AnnotationExample {

}
// This is class level Annotation
@Target(value={ElementType.TYPE})
@interface ClassLevelAnnotation {
	
}
// This is Method level Annotation
@Target(value={ElementType.METHOD})
@interface MethodLevelAnnotation {
	
}

@ClassLevelAnnotation
class AnnonatedComponent {
	@MethodLevelAnnotation
	public void annonatedMethod() {
		
	}
}

