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
@WebServlet("/GetDataBase")
public class GetDataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/website";
	
	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "183411"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDataBase() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "使用 GET 方法读取表单数据";
		// 处理中文
		String text =new String(request.getParameter("zh").getBytes("ISO8859-1"),"UTF-8");
		String pass=new String(request.getParameter("pa").getBytes("ISO8859-1"),"UTF-8");
		try{
			// 注册 JDBC 驱动器
			
			Class.forName(JDBC_DRIVER);
			// 打开一个连接
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// 执行 SQL 查询
			String sql;
			sql = "select * from t1";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			int x=0;
			while(rs.next()){
				// 通过字段检索
				String zh = rs.getString("zhanghu");
				String mima = rs.getString("mima");
				if(zh.equals(text)&&pass.equals(mima)){
					x=1;
					response.sendRedirect("http://localhost:8080/MainScreen.html#");
					break;
				}
			}
			if(x==0)out.println("登录失败！");
			// 展开结果集数据库
	

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