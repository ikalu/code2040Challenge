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

public class IfeanyiRegistration {

	public static void main(String[] args ) throws IOException {
		String endPoint = "http://challenge.code2040.org/api/register";
		String output;

		Registration ifeanyi = new Registration();			

		HttpPost post = new HttpPost(endPoint);	
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		//set the email and github private instances
		ifeanyi.setEmail("ifkalu@yahoo.com");
		ifeanyi.setGithub("https://github.com/ikalu/code2040Challenge.git");

		/* Use Gson to convert to Json and
		 * Set the data to be sent via HttpPost
		 * */
		StringEntity data = new StringEntity(gson.toJson(ifeanyi));
		post.setEntity(data);
		post.setHeader("Content-type", "application/json");
		HttpResponse result = httpclient.execute(post);
		System.out.println(result);
		
		//use BufferedReader to extract the string containing json from the http response
		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		while ((output = br.readLine()) != null){
			System.out.println(output);		

			//convert the String containing json to a HashMap
			Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType(); 
			Map<String,String> myToken = gson.fromJson(output, stringStringMap); 

			System.out.println("");

			//set the token private instance 
			ifeanyi.setToken(myToken.get("result"));

			System.out.println(ifeanyi.getToken());				
		}	
	}
}