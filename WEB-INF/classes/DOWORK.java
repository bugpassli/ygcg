import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;
class DOWORK 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL ="jdbc:mysql://localhost:3306/hsuygcg?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	static final String USER = "root";
	static final String PASS = "183411"; 
	public void dowork() {
	{
		Connection conn = null;
		PreparedStatement ps=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sj=df.format(new Date());
		try{
			// 注册 JDBC 驱动器
			
			Class.forName(JDBC_DRIVER);
			// 打开一个连接
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// 执行 SQL 查询
			String sql;
			sql = "select * from denglu";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			ArrayList<String> name=new ArrayList<String>();
			ArrayList<String> zuming=new ArrayList<String>();
			ArrayList<String> biaoming=new ArrayList<String>();
			ArrayList<String> ghs=new ArrayList<String>();
			ArrayList<String> biaozhi=new ArrayList<String>();
			while(rs.next()){
				name.add(rs.getString("Name"));
				zuming.add(rs.getString("ZuMing"));
				biaoming.add(rs.getString("BiaoMing"));
				ghs.add(rs.getString("GongHuoShang"));
				biaozhi.add(rs.getString("BiaoZhi"));
			}
			for(int i=0;i<biaoming.size();i++){
				String s1=name.get(i);
				String s2=zuming.get(i);
				String s3=biaoming.get(i);
				String s4=ghs.get(i);
				String s5=biaozhi.get(i);
				if(s5.equals("0")){
					if((s1.substring(0,2).equals("10")&&s1.length()==4)||s1.substring(0,2).equals("70")||s1.substring(0,2).equals("14")||s1.substring(0,2).equals("15")){
						sql="select * from "+s3+" where DATE_FORMAT(`TIMESTAMP`,'%Y-%m-%d')='"+sj+"'";
						ps=conn.prepareStatement(sql);
						ResultSet rs2 = ps.executeQuery(sql);
						while(rs2.next()){
							String s6=rs2.getString("ShangPin");
							String s7=rs2.getString("DanJia");
							String s8=rs2.getString("DanWei");
							String s9=rs2.getString("TiGongLiang");
							sql="insert into infobiao(YongHuMing,GongYingShang,ShangPin,DanJia,DanWei,LeiBie,TiGongLiang) values('"+s1+"','"+s4+"','"+s6+"','"+s7+"','"+s8+"','"+s2+"','"+s9+"')";
							ps = conn.prepareStatement(sql);
							ps.executeUpdate();
							String jj= "";
							jj = s3.replaceAll("([1-9]+[0-9]*|0)(\\.[\\d]+)?", "");
							sql="insert into "+jj+"jingjia (ShangPin,Danjia) values('"+s6+"','"+s7+"')";
							ps = conn.prepareStatement(sql);
							ps.executeUpdate();
						}
						
					 


					}
					else{
						java.util.Date date=new java.util.Date();
						int day=date.getDay();
						if(day==6){
						sql="select * from "+s3+" where DATE_FORMAT(`TIMESTAMP`,'%Y-%m-%d')='"+sj+"'";
						ps=conn.prepareStatement(sql);
						ResultSet rs2 = ps.executeQuery(sql);
						while(rs2.next()){
							String s6=rs2.getString("ShangPin");
							String s7=rs2.getString("DanJia");
							String s8=rs2.getString("DanWei");
							String s9=rs2.getString("TiGongLiang");
							sql="insert into infobiao(YongHuMing,GongYingShang,ShangPin,DanJia,DanWei,LeiBie,TiGongLiang) values('"+s1+"','"+s4+"','"+s6+"','"+s7+"','"+s8+"','"+s2+"','"+s9+"')";
							ps = conn.prepareStatement(sql);
							ps.executeUpdate();	
							String jj= "";
							jj = s3.replaceAll("([1-9]+[0-9]*|0)(\\.[\\d]+)?", "");
							sql="insert into "+jj+"jingjia (ShangPin,Danjia) values('"+s6+"','"+s7+"')";
							ps = conn.prepareStatement(sql);
							ps.executeUpdate();
						}
					}
				}
				 
				


				}//if
			}//for
			//System.out.println("Run");
			conn.close();
		}




		catch(Exception e){System.out.println(e.toString());}
      }
	}//dowork
}
