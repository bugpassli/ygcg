package YongHuShang;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.TimerTask; 
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.*;
import login.MysqlConnection.PingTai.DBUtil;
import VegetablePrice.Table.VegetBean.ProductXinxi;
public class AjaxServlert extends HttpServlet {
	private static final long serialsionUID=  1L; 
	private boolean True = false ; 
	private  PreparedStatement ps = null;
	private  ResultSet rs = null;
	public void doGet(HttpServletRequest request , HttpServletResponse response ) 
		throws ServletException , IOException {
		doPost(request , response);
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response ) 
		throws ServletException , IOException {
		Connection con = null;
		try{
			 con = new DBUtil().getConn();
			 response.setContentType("text/thml:charset=ISO-8859-1");
			 request.setCharacterEncoding("UTF-8");
			 response.setContentType("text/html");
			 response.setCharacterEncoding("UTF-8");
			 PrintWriter out = response.getWriter();
			
			 String user = request.getParameter("YongHuShang").trim();  //商家用户名
			 String CaiGouYuan = request.getParameter("CaiGuoYuan").trim();  //
			 String ProductID = request.getParameter("ProductID").trim();  //商品类型
			 //获取网络时间
			 String webUrl4 = "http://www.ntsc.ac.cn";
			 //获取本地时间
			 Date now = new Date(); 
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
			 String heretime = dateFormat.format(now);  //本地时间
			 String urltime  =  getWebsiteDatetime(webUrl4);

			 PreparedStatement ps1 = con.prepareStatement("select *  from infobiao where   LeiBie = ? ");
			 ps1.setString(1  , ProductID );
			 ResultSet rs1 = ps1.executeQuery();
			 ArrayList<ProductXinxi> Xinxi = new ArrayList<ProductXinxi>();
			 ProductXinxi px ;
			 while (rs1.next()) {
				 px = new ProductXinxi();
				 px.setYongHuMing(rs1.getString("YongHuMing"));
				 px.setGongYingShang(rs1.getString("GongYingShang")); //用户名
				 px.setShangPin(rs1.getString("ShangPin"));  //商品
				 px.setTiGongLiang(rs1.getString("TiGongLiang"));  //提供量
				 px.setDanJia(rs1.getString("DanJia"));  //单价
				 px.setDanWei(rs1.getString("DanWei"));  //单位
				 px.setXuQiuLiang(rs1.getString("XuQiuLiang")); //需求量
				 px.setBiaoJi(rs1.getString("BiaoJi"));  //标记
				 px.setLeiBie(rs1.getString("LeiBie")); //类别
				
				 PreparedStatement ps2 = con.prepareStatement("select *  from denglu where Name = ?  ");
				 ps2.setString(1  , user );
				 ResultSet rs2 = ps2.executeQuery();
				 if (rs2.next()) {
					 px.setCaiGouYuan(rs2.getString("UserName")); //采购员名字
					// System.out.println(rs2.getString("UserName"));
				 }
				 Xinxi.add(px);
				 System.out.println(rs1.getString("YongHuMing"));
				
			 }
			 //判断数据库中是否存在这产品
			 String sql = "select * from jiandubiao where Time like '%"+heretime+"%';";
			 ps = con.prepareStatement(sql);
			 rs = ps.executeQuery();
			 while (rs.next()) {
				True = true;
				//System.out.println(True+"\t"+Xinxi.size());
			 }
			 if (True ==  true) {
					//删除数据
					ps=con.prepareStatement("delete from jiandubiao where Time like '%"+heretime+"%';");
					ps.executeUpdate();
					//重新添加数据
					con.setAutoCommit(false);
					ps = con.prepareStatement("insert into jiandubiao ( YongHuMing ,GongYingShang, ShangPin ,TiGongLiang ,DanJia ,DanWei , XuQiuLiang , BiaoJi ,LeiBie , CaiGouYuan, Time , ZongJia , CGSID , BiaoShi ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.clearParameters();
					for (int i = 0; i < Xinxi.size() ; i++ ) {
						ps.setString(1,  Xinxi.get(i).getYongHuMing());
						ps.setString(2 , Xinxi.get(i).getGongYingShang());
						ps.setString(3 , Xinxi.get(i).getShangPin());
						ps.setString(4 , Xinxi.get(i).getTiGongLiang());
						ps.setString(5 , Xinxi.get(i).getDanJia());
						ps.setString(6 , Xinxi.get(i).getDanWei());
						ps.setString(7 , Xinxi.get(i).getXuQiuLiang());
						ps.setString(8 , Xinxi.get(i).getBiaoJi());
						ps.setString(9 , Xinxi.get(i).getLeiBie());
						ps.setString(10 ,Xinxi.get(i).getCaiGouYuan());
						ps.setString(11 ,urltime);
						ps.setString(12 , ""+(Double.valueOf(Xinxi.get(i).getDanJia())*(Double.valueOf(Xinxi.get(i).getXuQiuLiang()))));
						ps.setString(13 , CaiGouYuan );
						if ((Xinxi.get(i).getYongHuMing()).equals(user)){
							ps.setString(14 , "1" );
						}
						else {
							ps.setString(14 , "0");
						}
						ps.addBatch();
					}
					ps.executeBatch();
					con.commit(); //数据提交
					out.write("更改成功");
			 }
			 //添加数据
			 if (True == false) {
				 try {
					con.setAutoCommit(false);
					ps = con.prepareStatement("insert into jiandubiao ( YongHuMing ,GongYingShang, ShangPin ,TiGongLiang ,DanJia ,DanWei , XuQiuLiang , BiaoJi ,LeiBie , CaiGouYuan, Time , ZongJia , CGSID , BiaoShi ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.clearParameters();
					for (int i = 0; i < Xinxi.size() ; i++ ) {
						ps.setString(1,  Xinxi.get(i).getYongHuMing());
						ps.setString(2 , Xinxi.get(i).getGongYingShang());
						ps.setString(3 , Xinxi.get(i).getShangPin());
						ps.setString(4 , Xinxi.get(i).getTiGongLiang());
						ps.setString(5 , Xinxi.get(i).getDanJia());
						ps.setString(6 , Xinxi.get(i).getDanWei());
						ps.setString(7 , Xinxi.get(i).getXuQiuLiang());
						ps.setString(8 , Xinxi.get(i).getBiaoJi());
						ps.setString(9 , Xinxi.get(i).getLeiBie());
						ps.setString(10 ,Xinxi.get(i).getCaiGouYuan());
						ps.setString(11 ,urltime);
						ps.setString(12 , ""+(Double.valueOf(Xinxi.get(i).getDanJia())*(Double.valueOf(Xinxi.get(i).getXuQiuLiang()))));
						ps.setString(13 , CaiGouYuan );
						if ((Xinxi.get(i).getYongHuMing()).equals(user)){
							ps.setString(14 , "1" );
						}
						else {
							ps.setString(14 , "0");
						}
						ps.addBatch();
					}
					ps.executeBatch();
					con.commit(); //数据提交
					out.write("更改成功");
				 }
				 catch (Exception e2) {
					 e2.printStackTrace();
					 con.close();
				 }
			 }
			True = false;
			con.close();
			out.flush();  
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String getWebsiteDatetime(String webUrl){
		try	{
			URL url = new URL(webUrl);//获取资源对象
			URLConnection uc = url.openConnection(); //生成连接对象
			uc.connect(); //发送连接
			long ld = uc.getDate(); //读取网站时间
			Date date = new Date(ld);// 转换为标准时间对象
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd" , Locale.CHINA);
			
			return sdf.format(date);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}