package login.MysqlConnection.User;
import java.util.*;
public class User {
	private String id;
	private String username;
	private String password;
	private String biaozhi;
	public User(){
		super();
	}
	public User(String  username , String password){
		super();
		this.username = username;
		this.password = password;
	}
	public void setBiaozhi(String biaozhi){
		this.biaozhi = biaozhi;
	}
	public String getBiaozhi(){
		return biaozhi ;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public  String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
}