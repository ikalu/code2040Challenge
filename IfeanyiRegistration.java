import java.io.*;
import java.lang.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.gson.Gson;
/**
 * @author Ifeanyi Kalu
 *
 */

public class IfeanyiRegistration {

	public static void main(String[] args ) throws IOException {
		String endPoint = "http://challenge.code2040.org/api/register";

		Registration ifeanyi = new Registration();	
		HttpPost post = new HttpPost(endPoint);	
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		ifeanyi.email = "ifkalu@yahoo.com";
		ifeanyi.github = "https://github.com/ikalu/code2040Challenge.git";

		try {				
			StringEntity data = new StringEntity(gson.toJson(ifeanyi));
			post.setEntity(data);
			post.setHeader("Content-type", "application/json");
			HttpResponse result = httpclient.execute(post);
			System.out.println(result);
			BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null){
				System.out.println(output);
			}
		} catch (Exception handle) {
			handle.printStackTrace();			
		} finally {
			httpclient.close();
		}	
	}
}