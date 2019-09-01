package kalturaExercise;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class RunTests {

  Logger logger = Logger.getLogger("outLogs");
  FileHandler fileHandler;  
  
  @BeforeSuite
  public void beforeSuite() throws RuntimeException, IOException {
	  
		  fileHandler = new FileHandler("./logs/Log_" + new Date().getTime());
	      logger.addHandler(fileHandler);
	      SimpleFormatter formatter = new SimpleFormatter();  
	      fileHandler.setFormatter(formatter);	  
  }	
	
  @BeforeTest
  public void beforeTest() {
  }
  
  @Test(enabled=true)  // positive test
  
  public void Test1() throws JSONException { 
	  logger.info("Starting test1");
	  Date date = new Date();
	  long time = date.getTime();
	  String username = "User_" + time;
	  String externalId = "Ext_" + time;
	  OttUser ottUser = new OttUser(185, "KalturaOTTUser", username, "password", externalId);
	  HttpRequest phttprequest = new HttpRequest();
	  String httpResp = "";
	  try {
	   logger.info("sending request: ");
	   httpResp = phttprequest.PostHttpRequest(ottUser);
	   logger.info("Respond: " + httpResp);
	  } catch (IOException e) {
	   e.printStackTrace();
	   logger.info("error occured: " + e.getMessage());
	  }

	  JSONObject respJsonObject = new JSONObject(httpResp);
	  String actualUsername = respJsonObject.getJSONObject("result").getString("username");
	  String actualExternalId = respJsonObject.getJSONObject("result").getString("externalId");
	  try {
	   assertEquals(username, actualUsername);
	  } catch (Exception e) {
	   Assert.fail("Exception asserting username, expected:" + username + " but actual is: " + actualUsername);
	  }
	  try {
	   assertEquals(externalId, actualExternalId);
	  } catch (Exception e) {
	   Assert.fail("Exception asserting ExternalId, expected:" + externalId + " but actual is: " + actualExternalId);
	  }
	  logger.info("PASSED: Test1");
	 }
  
  
  @Test(enabled=true)  // negative test - set empty username
  
  public void Test2() throws JSONException {
	  logger.info("Starting test2");
	  Date date = new Date();
	  long time = date.getTime();
	  String username = "";
	  String externalId = "Ext_" + time;
	  OttUser ottUser = new OttUser(185, "KalturaOTTUser", username, "password", externalId);
	  HttpRequest phttprequest = new HttpRequest();
	  String httpResp = "";
	  try {
	   logger.info("sending request: ");
	   httpResp = phttprequest.PostHttpRequest(ottUser);
	   logger.info("Respond: " + httpResp);
	  } catch (IOException e) {
	   e.printStackTrace();
	   logger.info("error occured: " + e.getMessage());
	  }

	  JSONObject respJsonObject = new JSONObject(httpResp);
	  String actualMessage = respJsonObject.getJSONObject("result").getJSONObject("error").getString("message");
	  String actualCode = respJsonObject.getJSONObject("result").getJSONObject("error").getString("code");
	  try {
	   assertEquals("Argument [username or password] cannot be empty", actualMessage);
	  } catch (Exception e) {
	   Assert.fail("Exception asserting error message, expected:" + "'Argument [username or password] cannot be empty'" + " but actual is: " + actualMessage);
	  }
	  try {
	   assertEquals("500003", actualCode);
	  } catch (Exception e) {
	   Assert.fail("Exception asserting ExternalId, expected:" + "'500003'" + " but actual is: " + actualCode);
	  }
	  logger.info("PASSED: Test2");
	 }
  
  
  @Test(enabled=true)  // negative test - set invalid objectType
  
  public void Test3() throws JSONException {
	  logger.info("Starting test3");
	  Date date = new Date();
	  long time = date.getTime();
	  String username = "User_" + time;
	  String externalId = "Ext_" + time;
	  OttUser ottUser = new OttUser(185, "invalidObjectType", username, "password", externalId);
	  HttpRequest phttprequest = new HttpRequest();
	  String httpResp = "";
	  try {
	   logger.info("sending request: ");
	   httpResp = phttprequest.PostHttpRequest(ottUser);
	   logger.info("Respond: " + httpResp);
	  } catch (IOException e) {
	   e.printStackTrace();
	   logger.info("error occured: " + e.getMessage());
	  }
	  JSONObject respJsonObject = new JSONObject(httpResp);
	  String actualMessage = respJsonObject.getJSONObject("result").getJSONObject("error").getString("message");
	  String actualCode = respJsonObject.getJSONObject("result").getJSONObject("error").getString("code");
	  try {
	   assertEquals(actualMessage, "Invalid object type [invalidObjectType]");
	  } catch (Exception e) {
	   Assert.fail("Exception asserting objectType, expected:" + "'Invalid objectType [invalidObjectType]'" + " but actual is: " + actualMessage);
	  }
	  try {
	   assertEquals(actualCode, "500076");
	  } catch (Exception e) {
	   Assert.fail("Exception asserting error code, expected:" + "'500076'" + " but actual is: " + actualCode);
	  }
	  logger.info("PASSED: Test3");
	 }


  @AfterSuite
  public void afterSuite() {
	  fileHandler.flush();  
	  
  }

}
