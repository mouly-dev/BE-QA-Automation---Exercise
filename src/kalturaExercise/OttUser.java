package kalturaExercise;
import javax.json.Json;
import javax.json.JsonObject;

 
public class OttUser {       
	private int partnerId;
	private String objectType;
	private String username;
	private String externalId;
	private String password;


	OttUser(int partnerId,String objectType, String username,String password, String externalId){
		this.partnerId = partnerId;
		this.objectType = objectType;
		this.username = username;
		this.password= password;
		this.externalId = externalId;
	}
	
	public void setPartnerId ( int value ) {
		 partnerId = value;
		} 
	
	public void setObjectType ( String value ) {
		 objectType = value;
		} 
	
	public void setUsername ( String value ) {
		username = value;
		} 
	
	public void setPassword ( String value ) {
		password = value;
		} 
	
	public void setExternalId ( String value ) {
		externalId = value;
		} 
	
	public int getPartnerId ( int value ) {
		 return partnerId;
		} 
	
	public String getObjectType ( String value ) {
		 return objectType;
		} 
	
	public String getUsername ( String value ) {
		return username;
		} 
	
	public String getPassword ( String value ) {
		return password;
		} 
	
	public String getExternalId ( String value ) {
		return externalId;
		} 
	   
    	 public JsonObject creatOttJson() {
    	        JsonObject personObject = Json.createObjectBuilder()
    	                .add("partnerId", partnerId)
    	                .add("user", Json.createObjectBuilder()
    	                	   .add("objectType", objectType)
							   .add("username", username)
							   .add("firstName", "xympdpkyymlh")    	                		
							   .add("lastName", "1537875168491")    	                		
							   .add("email", "xympdpkyymlh1537875168491@gmail.com")    	                		
							   .add("address", "xympdpkyymlh fake address")    	                		
							   .add("city", "xympdpkyymlh fake city") 
							   .add("countryId", 7)
							   .add("externalId", externalId)      	                									      	                									   								  
							 )
    	                .add("password", "password")    	                            
    	                .build();
    	        return personObject;
    	    }
}