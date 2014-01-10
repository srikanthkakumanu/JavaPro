package com.srikanth.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import java.util.Set;
import java.util.HashSet;

@ApplicationPath("/myapp")
public class MyApp extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<>();
		set.add(MyResource.class);
		return set;
	}
}
