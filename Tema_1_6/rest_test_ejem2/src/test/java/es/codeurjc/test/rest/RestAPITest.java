package es.codeurjc.test.rest;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPITest {
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    
    @Test
    public void createNewItemTest(){
    	
        given().
            request()
                .body("{ \"description\" : \"Leche\", \"checked\": false }")
                .contentType(ContentType.JSON).
        when().
             post("/items/").
		then().
		     statusCode(201).
		     body("id", equalTo(1));
        
    }
    
    // GET /items/{id}
    
    @Test
    public void getOneItemTest() {
    	
    	// GIVEN
    	Response res = given()
    		.body("{ \"description\" : \"Leche\", \"checked\": false }")
    		.contentType(ContentType.JSON)
    	.when()
    		.post("/items/").andReturn();
    	
    	int id = from(res.getBody().asString()).get("id");
    	
    	// WHEN
    	
    	when()
    		.get("/items/{id}", id)
    		
		// THEN
    	.then()
    		.statusCode(200)
    		.body(
    				"id", equalTo(id),
    				"description", equalTo("Leche"),
    				"checked", equalTo(false)
    	);
    	 
    }
    
    // GET /items/
    
    @Test
    public void getAllItemsTest() {
    	
    	// GIVEN
    	Response res1 = given()
    		.body("{ \"description\" : \"Leche\", \"checked\": false }")
    		.contentType(ContentType.JSON)
    	.when()
    		.post("/items/").andReturn();
    	
    	Response res2 = given()
        		.body("{ \"description\" : \"Pan\", \"checked\": true }")
        		.contentType(ContentType.JSON)
        	.when()
        		.post("/items/").andReturn();
    	
    	int id1 = from(res1.getBody().asString()).get("id");
    	int id2 = from(res2.getBody().asString()).get("id");
    	
    	// WHEN
    	
    	when()
    		.get("/items/")
    		
		// THEN
    		
    	// [ { id: 1, description: "Leche", checked: false }, { id: 2, description: "Pan", checked: true } ]
    		
    	.then()
    		.statusCode(200)
    		// [ 1, 2, 3 ]
    		.body(
    			"id", hasItems(id1, id2),
    			"description", hasItems("Leche", "Huevos")
			);
    	 
    }
    
    
    
    
    
    
    
    
    
    
    
    
}


