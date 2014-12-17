import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

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
public class Haystack {

	public static void main(String[] args) throws IOException {

		HttpResponse response = getTheCollection();
		int theNeedle = findTheNeedle(response);
		System.out.println(theNeedle);
		sendTheNeedle(theNeedle);
	}

	/* Post a dictionary to the server and
	 * Return the response(which includes a string) from the server
	 * */
	public static HttpResponse getTheCollection() throws IOException{

		String endPoint = "http://challenge.code2040.org/api/haystack";

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
	 * convert the String to HashMap, convert the haystack to ArrayList, find the index of the given needle in the ArrayList and
	 * return the index
	 * */
	public static int findTheNeedle(HttpResponse result) throws IOException{
		
		Gson gson = new Gson();
		String output;
		int answer = 0;
		ArrayList<?> collection; //ArrayList to hold the haystack
		Object needle; //the needle will be an Object we are looking for in the haystack

		//use BufferedReader to extract the String containing json from the http response
		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		while ((output = br.readLine()) != null){
			System.out.println(output);		

			//convert the String containing Json to a HashMap-nested HashMap-
			Type stringStringObjectMap = new TypeToken<Map<String, Map<String, Object>>>(){}.getType(); 
			Map<String, Map<String, ArrayList<?>>> theDictionary = gson.fromJson(output, stringStringObjectMap); 

			System.out.println("");
			
			collection = theDictionary.get("result").get("haystack");
			needle = theDictionary.get("result").get("needle");

			answer = collection.indexOf(needle);		
			}

		return answer;
	}
	
	/* Take an argument of String type, post to the server and
	 * Return any response from the server
	 * */
	public static void sendTheNeedle(Object input) throws IOException{

		String endPoint = "http://challenge.code2040.org/api/validateneedle";

		Registration ifeanyi = new Registration();	
		Gson gson = new Gson(); //Gson instance

		//set the token and string private instances
		ifeanyi.setToken("8EBDqKCWgK");
		ifeanyi.setNeedle(input);

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