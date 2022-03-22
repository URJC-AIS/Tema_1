package es.codeurjc.test.rest;


import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemTest {
	
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
                .body("{ \"description\" : \"milk\", \"checked\": false }")
                .contentType(ContentType.JSON).
        when().
             post("/items/").
		then().
		     statusCode(201).
		     body("description", equalTo("milk"));
        
    }
    
    @Test
    public void createNewItemTest2() throws JSONException{
    	
    	JSONObject body = new JSONObject();

		body.put("description", "milk");
		body.put("checked", false);
    	
        given().
            request()
                .body(body.toString())
                .contentType(ContentType.JSON).
        when().
             post("/items/").
		then().
		     statusCode(201).
		     body("description", equalTo("milk"));
        
    }
    
    @Test
    public void createNewItemTest3() throws JSONException{
    	
    	JSONObject body = new JSONObject();

		body.put("description", "milk");
		body.put("checked", false);
    	
        Response response = given().
            request()
                .body(body.toString())
                .contentType(ContentType.JSON).
        when().
             post("/items/").
		then().
		     extract().response().andReturn();
        
        JsonPath jsonResponse = from(response.getBody().asString());
        
        int id = jsonResponse.get("id");
        
    }
    
    @Test
    public void createNewItemTest4() throws JSONException{
    	
    	JSONObject body = new JSONObject();

		body.put("description", "milk");
		body.put("checked", false);
    	
        Item item = given().
            request()
                .body(body.toString())
                .contentType(ContentType.JSON).
        when().
             post("/items/").
		then().
		     extract().response().as(Item.class);
        
    }
    
}


