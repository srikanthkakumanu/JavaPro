package com.srikanth.jdp.cp;


import static org.junit.Assert.*;

import org.junit.Test;

public class BuilderDemoTest {

	@Test
	public void testBuilderPattern() {
        BuilderDemo client=new BuilderDemo();
        Document doc=new Document();
        //client.createASCIIText(doc);
       assertNotEquals(client, doc);
	}

}
