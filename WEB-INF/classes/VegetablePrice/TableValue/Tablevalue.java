package VegetablePrice.TableValue;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import login.MysqlConnection.PingTai.DBUtil;
import VegetablePrice.Table.VegetBean.ProductXinxi;
import net.sf.json.*;
import VegetablePrice.Table.TableConn.TableConnProduct;
import net.sf.json.util.CycleDetectionStrategy;
public class Tablevalue extends HttpServlet {
	private PreparedStatement ps = null;
	private Connection con ;
	private static final long serialVersionUID = 1L;
	public void goPost(HttpServletRequest request , HttpServletResponse response) 
	   throws ServletException , IOException {
	   doGet(request , response );
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) 
	   throws ServletException , IOException {
		try	{
			con = new DBUtil().getConn();//数据库连接
			response.setContentType("text/thml:charset=ISO-8859-1");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			String stuno =    request.getParameter("stuno"); 
			String ProductID = request.getParameter("ProductID");
			String  username = request.getParameter("username");
			//ProductID = new String(ProductID.getBytes("ISO8859-1"), "GB2312");
			PrintWriter out = response.getWriter();
			//System.out.println("计算总价"+ProductID+"\t"+username+"\t"+stuno);

			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0 ; i < Integer.parseInt(stuno) ;i++ ) {
				list.add(Integer.parseInt(request.getParameter("saleNumber_"+i)));
				//System.out.println(i+"\t"+request.getParameter("saleNumber_"+i));
			}
			ArrayList<String> zhonglei = new ArrayList<String>();
			//获取该产品种类的数量及产品
			ArrayList<ProductXinxi> Zhonglei = new ArrayList<ProductXinxi>();
			ProductXinxi px1 ;
			PreparedStatement ps2 = con.prepareStatement( "select * from danwei where zhonglei = ? ");
			ps2.setString(1  , ProductID);
			int sun = 0 ;
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				sun++;
				px1 = new ProductXinxi();
				px1.setId(""+sun);
				px1.setShangPin(rs2.getString("name"));
				px1.setDanWei(rs2.getString("danwei"));
				Zhonglei.add(px1);

				zhonglei.add(rs2.getString("name"));
			}
			
			//获取产品中有多少个商家
			ArrayList<String> ShangJian = new ArrayList<String>();
			ArrayList<String> number = new ArrayList<String>();
			ArrayList<String> PersonName = new ArrayList<String>();
			Hashtable<String, String> has=new Hashtable<String, String>();
			ProductID = ProductID;
			String sql1 = "select * from denglu where ZuMing = ? ";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1  , ProductID);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				ShangJian.add(rs1.getString("Name"));
				PersonName.add(rs1.getString("ContactPerson"));
				has.put(rs1.getString("ContactPerson"),rs1.getString("Name"));
				number.add(rs1.getString("ContactNumber"));
			}
			//更新需求量
			for (int i = 0 ; i < Integer.parseInt(stuno) ;i++ )  {
				try	{
					ps=con.prepareStatement("update infobiao set   XuQiuLiang = ? where LeiBie =  ? and ShangPin =?");
					ps.setString(1,request.getParameter("saleNumber_"+i));
					ps.setString(2,ProductID);
					ps.setString(3,zhonglei.get(i));
					ps.executeUpdate();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			ArrayList<ProductXinxi> userProduct = new TableConnProduct(con , ProductID).getProductxinxi();
			ArrayList<String> zongjia = new ArrayList<String>();
			//计算总价
			for (int i = 0 ;  i < ShangJian.size(); i++) {
				String sql = "SELECT sum(DanJia * XuQiuLiang) FROM infobiao where  YongHuMing = ? and LeiBie = ? ";
				ps = con.prepareStatement(sql);
				ps.setString(1 ,ShangJian.get(i));
				ps.setString(2 , ProductID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					zongjia.add(rs.getString("sum(DanJia * XuQiuLiang)"));
				}
			}
			
			out.write(zongjia.toString());
			out.flush();  
			out.close();
			/*session.setAttribute("sjlist" ,ShangJian);
			session.setAttribute("username" ,username);
			session.setAttribute("ProductID" ,ProductID);
			session.setAttribute("PersonName" ,PersonName);
			session.setAttribute("number" , number);
			session.setAttribute("zllist" ,Zhonglei);  //菜品种类
			session.setAttribute("listxuq" ,list);
			session.setAttribute("has" , has);
			session.setAttribute("zongjia" ,zongjia);
			session.setAttribute("listproduct" , userProduct);
			System.out.println(Zhonglei.size());
			response.sendRedirect("Productform.jsp");*/
		}
		catch (Exception e){
			request.setAttribute("error",e);
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			e.printStackTrace();
		}
	}	
}