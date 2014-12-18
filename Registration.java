public class Registration {
	/**
	 * @author Ifeanyi Kalu
	 *
	 */

	private String email;
	private String github;
	private String token;
	private String string;
	private Object needle;
	private Object[] array;
	private String datestamp;
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setGithub(String github){
		this.github = github;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public void setString(String string){
		this.string = string;
	}
	
	public void setNeedle(Object needle){
		this.needle = needle;
	}
	
	public void setArray(Object[] array){
		this.array = array;
	}
	
	public void setDatestamp(String datestamp){
		this.datestamp = datestamp;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getGithub(){
		return github;
	}
	
	public String getToken(){
		return token;
	}
	
	public String getString(){
		return string;
	}
	
	public Object getNeedle(){
		return needle;
	}
	
	public Object[] getArray(){
		return array;
	}
	
	public String getDatestamp(){
		return datestamp;
	}
}