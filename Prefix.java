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
public class Prefix {

	public static void main(String[] args) throws IOException {

		HttpResponse response = getTheCollection();
		Object[] strings = findTheStrings(response);
		
		for (Object string : strings)
			System.out.println(string);
		
		sendTheStrings(strings);
	}
	
	/* Post a dictionary to the server and
	 * Return the response(which includes a string) from the server
	 * */
	public static HttpResponse getTheCollection() throws IOException{

		String endPoint = "http://challenge.code2040.org/api/prefix";

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
	 * convert the String to HashMap, convert the array of strings to Iterable, 
	 * find the strings that do not begin with the given prefix, store them in an ArrayList,
	 * convert the ArrayList to Array and return the Array
	 * */
	public static Object[] findTheStrings(HttpResponse result) throws IOException{
		
		Gson gson = new Gson();
		String output;
		ArrayList<String> answers = new ArrayList<String>(); //ArrayList to hold strings of interest
		Iterable<?> strings; //Iterable to hold the array of string
		Object pref; //pref will be an Object we are looking for in every string in the array

		//use BufferedReader to extract the String containing json from the http response
		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		while ((output = br.readLine()) != null){
			System.out.println(output);		

			//convert the String containing Json to a HashMap-nested HashMap-
			Type stringStringObjectMap = new TypeToken<Map<String, Map<String, Object>>>(){}.getType(); 
			Map<String, Map<String, Iterable<?>>> theDictionary = gson.fromJson(output, stringStringObjectMap); 

			System.out.println("");
			
			strings = theDictionary.get("result").get("array");
			pref = theDictionary.get("result").get("prefix");
			
			//convert each string in the Iterable and the pref to `Type String` and use the startsWith String method
			for (Object string : strings){
				if (string.toString().startsWith(pref.toString()) == false){
					answers.add(string.toString());
				}
			}
		}
		return answers.toArray(); //convert ArrayList to Array and return
	}
	
	/* Take an argument of Object[] type, post to the server and
	 * Return any response from the server
	 * */
	public static void sendTheStrings(Object[] input) throws IOException{

		String endPoint = "http://challenge.code2040.org/api/validateprefix";

		Registration ifeanyi = new Registration();	
		Gson gson = new Gson(); //Gson instance

		//set the token and string private instances
		ifeanyi.setToken("8EBDqKCWgK");
		ifeanyi.setArray(input);

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