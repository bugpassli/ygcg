package ShuliBiao;
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
public class ChaXunTable extends HttpServlet {
	private static final long serialsionUID=  1L; 
	private boolean True = false ; 
	private  PreparedStatement ps = null;
	private  ResultSet rs = null;
	public void doGet(HttpServletRequest request , HttpServletResponse response ) 
		throws ServletException , IOException {
		 doPost(request , response );
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response ) 
		throws ServletException , IOException {
		Connection con = null;
		try{
			 con = new DBUtil().getConn();
			 String CaiGouYuan = request.getParameter("username").trim();  //�ɹ����û���
			 System.out.println(CaiGouYuan);
			 //��ȡ����ʱ��
			 String webUrl4 = "http://www.ntsc.ac.cn";
			 //��ȡ����ʱ��
			 Date now = new Date(); 
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ
			 String heretime = dateFormat.format(now);  //����ʱ��
			 String urltime  =  getWebsiteDatetime(webUrl4);

			 PreparedStatement ps1 = con.prepareStatement("select *  from jiandubiao where Time like '%"+heretime+"%' and CGSID = ? ");
			 ps1.setString(1  , CaiGouYuan );
			 ResultSet rs1 = ps1.executeQuery();
			 ArrayList<ProductXinxi> Xinxi = new ArrayList<ProductXinxi>();
			 ProductXinxi px ;
			 int id = 0 ;
			 while (rs1.next()) {
				 px = new ProductXinxi();
				 id++;
				 px.setId(""+id);
				 px.setGongYingShang(rs1.getString("GongYingShang")); //�û���
				 px.setShangPin(rs1.getString("ShangPin"));  //��Ʒ
				 px.setTiGongLiang(rs1.getString("TiGongLiang"));  //�ṩ��
				 px.setDanJia(rs1.getString("DanJia"));  //����
				 px.setDanWei(rs1.getString("DanWei"));  //��λ
				 px.setXuQiuLiang(rs1.getString("XuQiuLiang")); //������
				 px.setBiaoJi(rs1.getString("BiaoJi"));  //���
				 px.setLeiBie(rs1.getString("LeiBie")); //���
				 px.setZongJia(rs1.getString("ZongJia")); //���
				
				 PreparedStatement ps2 = con.prepareStatement("select *  from denglu where Name = ?  ");
				 ps2.setString(1  , CaiGouYuan );
				 ResultSet rs2 = ps2.executeQuery();
				 if (rs2.next()) {
					 px.setCaiGouYuan(rs2.getString("UserName")); //�ɹ�Ա����
				 }
				 Xinxi.add(px);
				
			 }
			
			 HttpSession session = request.getSession();
			 session.setAttribute("list" , Xinxi);
			 response.sendRedirect("chaxun.jsp");
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String getWebsiteDatetime(String webUrl){
		try	{
			URL url = new URL(webUrl);//��ȡ��Դ����
			URLConnection uc = url.openConnection(); //�������Ӷ���
			uc.connect(); //��������
			long ld = uc.getDate(); //��ȡ��վʱ��
			Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd" , Locale.CHINA);
			
			return sdf.format(date);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}