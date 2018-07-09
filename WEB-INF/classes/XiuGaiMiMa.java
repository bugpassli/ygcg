import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class DatabaseAccess
 */
@WebServlet("/XiuGaiMiMa")
public class XiuGaiMiMa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL ="jdbc:mysql://localhost:3306/hsuygcg?useUnicode=true&characterEncoding=utf-8";
	
	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "183411"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XiuGaiMiMa() {
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
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "登录界面";
		// 处理中文
		String text =new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
		String pass=new String(request.getParameter("password1").getBytes("ISO8859-1"),"UTF-8");
		try{
			// 注册 JDBC 驱动器
			
			Class.forName(JDBC_DRIVER);
			// 打开一个连接
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// 执行 SQL 查询
			String sql;
			sql = "update denglu set Password='"+pass+"' where Name='"+text+"'";
			ps=conn.prepareStatement(sql);
			int x=0;
			x=ps.executeUpdate(sql); 
			if(x==0)out.println("<script>alert('修改失败!');</script>");
			// 展开结果集数据库
			else
				out.println("<script>alert('修改成功!')</script>");

			// 完成后关闭
			
			ps.close();
			conn.close();
		} catch(SQLException se) {
			// 处理 JDBC 错误
			out.println(se.toString());
		} catch(Exception e) {
			// 处理 Class.forName 错误
			out.println(e.toString());
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