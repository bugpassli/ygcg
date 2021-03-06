package login.MysqlConnection.Table;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Product {
	private String id;
	private String username;
	private String password;
	private String product;
	private String amount ;
	private String nuit ;
	private String userID ;
	private int pc;// 当前页码page code
	private int tp;// 总页数total page
	private String url;//它就是url后的条件！
	public Product(){
		super();
	}
	//Id
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	//用户
	public  String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	//密码
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	//产品
	public void setProduct(String product){
		this.product = product;
	}
	public String getProduct(){
		return product ;
	}
	//需求量
	public void setAmount(String amount){
		this.amount = amount;
	}
	public String getAmount(){
		return amount ;
	}
	//需求量单位
	public void setNuit(String nuit){
		this.nuit = nuit;
	}
	public String getNuit(){
		return nuit ;
	}
	//食堂识别
	public void setUserID(String userID){
		this.userID = userID;
	}
	public String getUserID(){
		return userID ;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	/**
	* 计算总页数
	* @return
	*/
	public int getTp() {
		// 通过总记录数和每页记录数来计算总页数
		return tp;
	}
	public void setTp(int tp) {
	    this.tp = tp;
	}
}