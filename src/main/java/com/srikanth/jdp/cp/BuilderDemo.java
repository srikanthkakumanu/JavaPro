package com.srikanth.jdp.cp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Srikanth
 * Date: 11/4/13
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuilderDemo {
	private static Logger logger = LoggerFactory.getLogger(BuilderDemo.class);
	// Keep in mind that all of those classes are from SLF4J package!
    /**
     * It defines an instance for creating an object but letting subclasses decide which class to instantiate
     * and allows a finer control over the construction process.
     * The participants classes in this pattern are:
     * The Builder class specifies an abstract interface for creating parts of a Product object.
     * The ConcreteBuilder constructs and puts together parts of the product by implementing the Builder interface.
     * It defines and keeps track of the representation it creates and provides an interface for saving the product.
     * The Director class constructs the complex object using the Builder interface.
     * The Product represents the complex object that is being built.
     */
    void createASCIIText(Document doc) {
        ASCIIConverter asciiBuilder = new ASCIIConverter();
        RTFReader rtfReader = new RTFReader(asciiBuilder);
        rtfReader.parseRTF(doc);
        ASCIIText asciiText = asciiBuilder.getResult();
        logger.info("ASCII Text: " + asciiText);
    }
    public static void main(String... args) {
        BuilderDemo client=new BuilderDemo();
        Document doc=new Document();
        client.createASCIIText(doc);
        logger.info("This is an example of Builder Pattern");
    }
}
//Abstract Builder
abstract class TextConverter {
    abstract void convertCharacter(char c);
    abstract void convertParagraph();
}
// Product
class ASCIIText {
    Logger logger = LoggerFactory.getLogger(ASCIIText.class);
	public void append(char c) {
		//Implement the code here
		logger.info("ASCII Character: " + c);
    }
}
//Concrete Builder
class ASCIIConverter extends TextConverter {
    ASCIIText asciiTextObj;//resulting product
	/*converts a character to target representation and appends to the resulting*/
    void convertCharacter(char c) {
        char asciiChar = new Character(c).charValue();
        //gets the ascii character
        asciiTextObj.append(asciiChar);
    }
    void convertParagraph(){}
    ASCIIText getResult() {
        return asciiTextObj;
    }
}
//This class abstracts the document object
class Document {
    static int value;
    char token;
    public char getNextToken() {
        //Get the next token
        return token;
    }
}
//Director
class RTFReader {
    private static final char EOF='0'; // Delimiter for End of File
    final char CHAR='c';
    final char PARA='p';
    char t;
    TextConverter builder;
    RTFReader(TextConverter obj) {
        builder=obj;
    }
    void parseRTF(Document doc) {
        while ((t=doc.getNextToken())!= EOF) {
            switch (t) {
                case CHAR: 
                	builder.convertCharacter(t);
                	break;
                case PARA: 
                	builder.convertParagraph();
                	break;
            }
        }
    }
}
