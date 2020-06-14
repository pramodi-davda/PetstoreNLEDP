package org.assesment;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class stepDefination {
	// specify global variables
	String petStatus;
	int count;
	int total;
	String namePet;
	JsonPath js;
	
	//define the REST endpoint
	@Given("^RestAssured.baseURI=\"([^\"]*)\"$")
	    public void restassuredbaseurisomething(String strArg1) throws Throwable {
		 RestAssured.baseURI="https://petstore.swagger.io";
	    }
	// get all pets using GET API call with status “available” 
	    @When("^user finds pets by status$")
	    public void user_finds_pets_by_status() throws Throwable {
	    	petStatus=given().queryParam("status","available").
	    			when().get("v2/pet/findByStatus").			
	    			then().assertThat().statusCode(200).extract().response().asString();
	    	//	System.out.println("Details of all the data " +petStatus);
	    }
	    
//getting the size of an array returning number of pets with status available
	    
	    @Then("the array size of available pets is returned")
	    public void the_array_size_of_available_pets_is_returned() {
	    
	    	
	    	js=new JsonPath(petStatus);
			count=js.getInt("petStatus.size()");  
							
			System.out.println("Total number of pets with status available is " +count);
		
	    }
// for loop to iterate through all pets with available status and return total with name doggie
	    
	    @And("^the number of pets with status available and the name doggie$")
	    public void the_number_of_pets_with_status_available_and_the_name_doggie() throws Throwable {
	    	
	    	total=0;
	    	//find names in the array, if name is "doggie" than increment total by 1
	    
	    	for (int i=0;i<count;i++)
			{
				namePet = js.get("["+i+"].name");
			if (namePet!=null){

				if (namePet.equals("doggie"))

					total=total+1;
			}

			}
			System.out.println("The total number of pet with name doggie is: " +total);
			 assertTrue(total>1); 
	    }

	}