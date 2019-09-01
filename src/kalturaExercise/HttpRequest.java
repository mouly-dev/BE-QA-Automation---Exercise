package kalturaExercise;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    public String PostHttpRequest(OttUser ottUser) throws IOException {

    	   BufferedReader bufferedReader = null;
    	   String respOut = null;
    	   boolean isError = false;
        try {

            URL url = new URL("https://rest-eus1.ott.kaltura.com/restful_v5_0/api_v3/service/ottuser/action/register");
            HttpURLConnection httpConnection  = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            // Writes the JSON parsed as string to the connection
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(ottUser.creatOttJson().toString().getBytes());
            Integer responseCode = httpConnection.getResponseCode();

            // Checking the the message code and reading the buffer
            if (responseCode >= 200  && responseCode <= 201) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }

            // Getting the response
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            respOut = content.toString();
            if (isError) {throw new java.lang.Error("fail to send request, Error code: " + responseCode + " , message: " + respOut);}
        } catch (Exception e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        finally{ bufferedReader.close(); }
		return respOut;
    }

}
