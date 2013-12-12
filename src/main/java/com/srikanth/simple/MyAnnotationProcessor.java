package com.srikanth.simple;

import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom Annotation Processor
 * @author Srikanth
 *
 */
@SuppressWarnings("restriction")
@SupportedAnnotationTypes(value={"*"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MyAnnotationProcessor extends AbstractProcessor {
	Logger logger = LoggerFactory.getLogger(MyAnnotationProcessor.class);
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations,	RoundEnvironment roundEnvironment) {
		for (TypeElement element : annotations) {
            logger.info(element.getQualifiedName().toString());
        }
		return false;
	}
}
