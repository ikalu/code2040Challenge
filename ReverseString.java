import java.io.*;
import java.lang.reflect.Type;
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

public class ReverseString {

	public static void main(String[] args) throws IOException {
		
		HttpResponse response = getTheString();
		String theWord = reverseTheString(response);
		System.out.println(theWord);
		sendTheString(theWord);
	}

	/* Post a dictionary to the server and
	 * Return any response(which includes a string) from the server
	 * */
	public static HttpResponse getTheString() throws IOException{

		String endPoint = "http://challenge.code2040.org/api/getstring";

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
	 * convert the String to HashMap, reverse the String and
	 * return the reversed string
	 * */
	public static String reverseTheString(HttpResponse result) throws IOException{
		
		Gson gson = new Gson();
		String output;
		String answer = null;

		//use BufferedReader to extract the String containing json from the http response
		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		while ((output = br.readLine()) != null){
			System.out.println(output);		

			//convert the String containing json to a HashMap
			Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType(); 
			Map<String,String> theString = gson.fromJson(output, stringStringMap); 

			System.out.println("");

			System.out.println(theString.get("result"));	

			//use the reverse method in StringBuilder class to reverse the string
			StringBuilder buffer = new StringBuilder(theString.get("result"));
			buffer.reverse();

			System.out.println("");
			answer = buffer.toString();
		}

		return answer;
	}
	
	/* Take an argument of String type, post to the server and
	 * Return any response from the server
	 * */
	public static void sendTheString(String input) throws IOException{

		String endPoint = "http://challenge.code2040.org/api/validatestring";

		Registration ifeanyi = new Registration();	
		Gson gson = new Gson(); //Gson instance

		//set the token and string private instances
		ifeanyi.setToken("8EBDqKCWgK");
		ifeanyi.setString(input);

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