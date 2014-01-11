package com.srikanth.rest;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.srikanth.jaxb.Product;

@Path("/product")
public class ProductResource {
	
	/**
	 * @return
	 */
	@GET
	@Path("/get")
	@Produces("application/xml")
	public Product getProduct() {
		Product prod = new Product();
		prod.setId(1);
		prod.setName("Mattress");
		prod.setDescription("Queen size mattress");
		prod.setPrice(500);
		return prod;
	}

	/**
	 * @param prod
	 * @return
	 */
	@POST
	@Path("/create")
	@Consumes("application/xml")
	public Response createProduct(Product prod) {
		return Response.created(URI.create("/create")).build();
		
	}
}
