import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseAccess
 */
@WebServlet("/InsertDataBaseGHS")
public class InsertDataBaseGHS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/website?useUnicode=true&characterEncoding=utf-8";
	
	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "183411"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDataBaseGHS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement ps=null;
		// 设置响应内容类型
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		// 处理中文
		String text =new String(request.getParameter("cpsl").getBytes("ISO8859-1"),"UTF-8");
		String zh=new String(request.getParameter("zh").getBytes("ISO8859-1"),"gb2312");
		int count=Integer.parseInt(text);
		String s="";
		String s1="",s2="",s3="",s4="",s5="",s6="",s7="";
		try{
			// 注册 JDBC 驱动器
			
			Class.forName(JDBC_DRIVER);
			// 打开一个连接
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// 执行 SQL 查询
			String sql;
			int x=0;
			 
			for(int i=0;i<count;i++){
				s1=new String(request.getParameter("mc"+i).getBytes("ISO8859-1"),"gb2312");
				s+="1";
				s2=new String(request.getParameter("sl"+i).getBytes("ISO8859-1"),"gb2312");
				s+="2";
				s3=new String(request.getParameter("dj"+i).getBytes("ISO8859-1"),"gb2312");
				s+="3";
				s4=new String(request.getParameter("dw"+i).getBytes("ISO8859-1"),"gb2312");
				s+="4";
				s5=new String(request.getParameter("zj"+i).getBytes("ISO8859-1"),"gb2312");
				s+="5";
				s6=new String(request.getParameter("lj"+i).getBytes("ISO8859-1"),"gb2312");
				s+="6";
				s7=(new java.util.Date()).toLocaleString();
				s+="7";
				sql= "INSERT INTO ghs(mc,sl,dj,dw,zj,tp,sj,zh) VALUES('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+zh+"')";
				//实例化 PreparedStatement
				ps = conn.prepareStatement(sql);
				x=ps.executeUpdate();
				
			}
			if(x>0){
				out.println("<script>alert('提交成功!');</script>");
			}
			// 展开结果集数据库
			if(x==0)
				out.println("<script>alert('没有提交任何数据!');</script>");
			// 完成后关闭
			
			ps.close();
			conn.close();
		} catch(SQLException se) {
			// 处理 JDBC 错误
			out.println(s1+s2+s3+s4+s5+s6+s7);
			out.println(se.toString()+s);
		} catch(Exception e) {
			// 处理 Class.forName 错误
			out.println(s1+s2+s3+s4+s5+s6+s7);
			out.println(e.toString()+s);
		}finally{
			// 最后是用于关闭资源的块
			try{
				if(ps!=null)
				ps.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}