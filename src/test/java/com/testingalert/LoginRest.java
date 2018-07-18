package com.testingalert;

import com.Input.InputLogin.InputLogin;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class LoginRest 
{
	public static void main(String args[])
	{
		InputLogin login = new InputLogin();
		
		//Set JSON Input Parameters
		
		login.setApiVersion("1.0"); 
		login.setActionName("login");
		login.setEmail("testingalertindia@gmail.com");
		login.setPassword("123456");
		login.setMedium("MANUAL");
		login.setAppKey("myAppKey");
			
			Gson gson = new Gson();
			gson.toJson(login);
			System.out.println("Input JSON Request :: "+ gson.toJson(login)); //Return Input JSON Request
			
			Response res = RestAssured.given().contentType("application/json").body(login).when().post("http://game.com/api/v11_2");
			
			System.out.println(" Output Response :: "+res.andReturn().asString()); //Return Output JSON Response
			
			//Test Step Validation
			
			try
			{
				if(res.path("message").equals("Login successfully"))
				{
					System.out.println("Response Time ->"+res.time()+"::::::"+ res.path("message"));
				}
				else
				{
					System.out.println("Failure Message :: " +res.path("message"));
				}
			}
			catch(Exception e)
			{
				System.out.println( e.getMessage());
			}
		}
	
}