package  NightAudit.TimeThreadNight;
import login.MysqlConnection.PingTai.DBUtil;
import java.util.TimerTask; 
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.sql.*;
import java.util.*;
import java.sql.*;
public class TimeThreadNight extends TimerTask {
	public void run(){
		try{    
			  Connection con = null ;
			  String product = "";
			  String username = "";
			  String time = "";
			  PreparedStatement ps = null;
			  //获取网络时间
			  String webUrl4 = "http://www.ntsc.ac.cn";
			  //获取本地时间
			  Date now = new Date(); 
			  SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");//可以方便地修改日期格式
			  String heretime = dateFormat.format(now); 
			  System.out.println(getWebsiteDatetime(webUrl4)+"\t"+heretime);

			  try {
				  ArrayList<String> a = new ArrayList<String>();
				  a.add("蔬菜");
				  a.add("粮油");
				  a.add("厨房用品");
				  a.add("日用百货");
				  con = new DBUtil().getConn();
				  try  {
					    if (heretime.indexOf("一")>=0) {
							 ps=con.prepareStatement("DELETE FROM infobiao");
							 ps.executeUpdate();
					    }
						else {
							con.setAutoCommit(false);
							ps = con.prepareStatement("DELETE FROM infobiao WHERE LeiBie = ?");
							ps.clearParameters();
							for (int i = 0; i < a.size() ; i++ ) {
								ps.setString(1,  a.get(i));
								ps.addBatch();
							}
							ps.executeBatch();
							con.commit(); //数据提交
						}
				  }
				  catch (Exception e2) {
					  e2.printStackTrace();
				  }

			  }
			  catch (Exception e1){
				  e1.printStackTrace();
			  }

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
			SimpleDateFormat sdf  = new SimpleDateFormat("EEEE" , Locale.CHINA);
			
			return sdf.format(date);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
		