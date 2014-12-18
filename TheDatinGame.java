import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;
import java.sql.Timestamp;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Ifeanyi Kalu
 *
 */
public class TheDatinGame {

	public static void main(String[] args) throws IOException, ParseException {

		HttpResponse response = getTheDate();
		String datestamp = addTheInterval(response);
		
		System.out.println(datestamp);	
		
		sendTheTimestamp(datestamp);
	}
	
	/* Post a dictionary to the server and
	 * Return the response(which includes a string) from the server
	 * */
	public static HttpResponse getTheDate() throws IOException{

		String endPoint = "http://challenge.code2040.org/api/time";

		Registration ifeanyi = new Registration();	

		ifeanyi.setToken("8EBDqKCWgK");

		//Instances of Gson and HttpPost
		HttpPost post = new HttpPost(endPoint);	
		Gson gson = new Gson();

		CloseableHttpClient httpclient = HttpClients.createDefault();

		/* Use Gson to convert to Json and
		 * Set the data to be sent via HttpPost
		 * */
		StringEntity data = new StringEntity(gson.toJson(ifeanyi));
		post.setEntity(data);
		post.setHeader("Content-type", "application/json");
		HttpResponse result = httpclient.execute(post);
		System.out.println(result);

		return result;	
	}
	
	/* Take an argument of HttpResponse type, extract the String, 
	 * convert the String to HashMap, convert ISO8601 to Unix Timestamp -millisecs-, divide by 1000 to get seconds
	 * add interval, and return the new time in formatted ISO8601
	 * */
	public static String addTheInterval(HttpResponse result) throws IOException, ParseException{
		
		Gson gson = new Gson();
		String output;
		DateFormat time = null;
		Timestamp timestamp = null;

		//use BufferedReader to extract the String containing json from the http response
		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		while ((output = br.readLine()) != null){
			System.out.println(output);		

			//convert the String containing Json to a HashMap-nested HashMap-
			Type stringStringObjectMap = new TypeToken<Map<String, Map<String, String>>>(){}.getType(); 
			Map<String, Map<String, String>> theDictionary = gson.fromJson(output, stringStringObjectMap); 

			System.out.println("");
			
			time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			time.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			long unixTime = time.parse(theDictionary.get("result").get("datestamp")).getTime();
			int interval = Integer.parseInt(theDictionary.get("result").get("interval"));
			
			unixTime /= 1000;
			
			Long newTime = (unixTime + interval);
			
			timestamp = new java.sql.Timestamp(newTime * 1000); 	
		}
		return time.format(timestamp); // return timestamp
	}
	
	/* Take an argument of String type, post to the server and
	 * Return any response from the server
	 * */
	public static void sendTheTimestamp(String datestamp) throws IOException{

		String endPoint = "http://challenge.code2040.org/api/validatetime";

		Registration ifeanyi = new Registration();	
		Gson gson = new Gson(); //Gson instance

		//set the token and string private instances
		ifeanyi.setToken("8EBDqKCWgK");
		ifeanyi.setDatestamp(datestamp);

		HttpPost post = new HttpPost(endPoint);	//HttpPost instance		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		/* Use Gson to convert to Json and
		 * Set the data to be sent via HttpPost
		 */
		StringEntity data = new StringEntity(gson.toJson(ifeanyi)); 
		post.setEntity(data);
		post.setHeader("Content-type", "application/json");
		HttpResponse result = httpclient.execute(post);
		System.out.println(result);
		String output;
		
		//use BufferedReader to extract the String containing json from the http response
		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		while ((output = br.readLine()) != null){
			System.out.println(output);
		}
	}
}
