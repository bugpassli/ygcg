package VegetablePrice.Table.VegetBean;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductXinxi {
	private String id;
	private String username;
	private String yongHuMing; //用户名
	private String gongYingShang ; //供货商
	private String shangPin ; //商品
	private String tiGongLiang ; //提供量
	private String danJia ; //单价
	private String danWei ; //单位
	private String zongJia  ; //总价
	private String xuQiuLiang ;// 需求量
	private String biaoJi ; //标记
	private String caiGouYuan ; //采购员
	private String leiBie; //类型
	private String time ; //时间
	private String biaoShi ; //表示
	public ProductXinxi(){
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
	//商品
	public String getShangPin(){
		return shangPin;
	}
	public void setShangPin(String shangPin){
		this.shangPin = shangPin;
	}
	//用户名
	public String getYongHuMing(){
		return yongHuMing;
	}
	public void setYongHuMing(String yongHuMing){
		this.yongHuMing =yongHuMing;
	}
	//供货商
	public String getGongYingShang(){
		return gongYingShang;
	}
	public void setGongYingShang(String gongYingShang){
		this.gongYingShang = gongYingShang;
	}
	//提供量
	public String getTiGongLiang(){
		return tiGongLiang;
	}
	public void setTiGongLiang(String tiGongLiang){
		this.tiGongLiang = tiGongLiang;
	}
	//单价
	public String getDanJia(){
		return danJia;
	}
	public void setDanJia(String danJia){
		this.danJia = danJia;
	}
	//单位
	public String getDanWei(){
		return danWei;
	}
	public void setDanWei(String danWei){
		this.danWei = danWei;
	}
	//总价
	public String getZongJia(){
		return zongJia;
	}
	public void setZongJia(String zongJia){
		this.zongJia = zongJia;
	}
	//需求量
	public String getXuQiuLiang(){
		return xuQiuLiang;
	}
	public void setXuQiuLiang(String xuQiuLiang){
		this.xuQiuLiang = xuQiuLiang;
	}
	//食堂标记
	public String getBiaoJi(){
		return biaoJi;
	}
	public void setBiaoJi(String biaoJi){
		this.biaoJi = biaoJi;
	}
	//采购员
	public String getCaiGouYuan(){
		return caiGouYuan;
	}
	public void setCaiGouYuan(String caiGouYuan){
		this.caiGouYuan = caiGouYuan;
	}
	//类别
	public String getLeiBie(){
		return leiBie;
	}
	public void setLeiBie(String leiBie){
		this.leiBie = leiBie;
	}
	//时间
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}
	//表示
	public String getBiaoShi(){
		return biaoShi;
	}
	public void setBiaoShi(String biaoShi){
		this.biaoShi = biaoShi;
	}
}