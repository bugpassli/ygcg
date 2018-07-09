import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/CheckDataBase"})
public class CheckDataBase extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/hsuygcg?useUnicode=true&characterEncoding=utf-8";
  static final String USER = "root";
  static final String PASS = "183411";

  protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    Connection localConnection = null;
    PreparedStatement localPreparedStatement = null;

    HttpSession localHttpSession = paramHttpServletRequest.getSession();
    paramHttpServletResponse.setContentType("text/html;charset=UTF-8");
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    String str1 = "µÇÂ¼½çÃæ";

    String str2 = new String(paramHttpServletRequest.getParameter("zh").getBytes("ISO8859-1"), "UTF-8");
    String str3 = new String(paramHttpServletRequest.getParameter("pa").getBytes("ISO8859-1"), "UTF-8");
    try
    {
      Class.forName("com.mysql.jdbc.Driver");

      localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsuygcg?useUnicode=true&characterEncoding=utf-8", "root", "183411");

      String str4 = "select * from denglu";
      localPreparedStatement = localConnection.prepareStatement(str4);
      ResultSet localResultSet = localPreparedStatement.executeQuery(str4);
      int i = 0;
      String str5 = str2.substring(0, 2);
      String str6 = "";
      while (localResultSet.next())
      {
        String str7 = localResultSet.getString("Name");
        String str8 = localResultSet.getString("Password");
        String str9 = localResultSet.getString("ShiTang");
        String str10 = localResultSet.getString("UserName");
        String str11 = localResultSet.getString("Sex");
        String str12 = localResultSet.getString("NianLing");
        String str13 = localResultSet.getString("ZhiWu");
        String str14 = localResultSet.getString("CaiGouNianXian");
        if ((str7.equals(str2)) && (str3.equals(str8))) {
          String str15 = localResultSet.getString("status");
          if (str15.equals("1")) {
            paramHttpServletResponse.sendRedirect("zhuxiao.jsp");
            break;
          }
          Date localDate = new Date();
          int j = 15;
          if ((j >= 0) && (j < 12)) {
            if ((str5.equals("10")) || (str5.equals("20")) || (str5.equals("30")) || (str5.equals("40")) || 
              (str5
              .equals("50")) || 
              (str5.equals("60")) || (str5.equals("70")) || (str5.equals("80")) || 
              (str5
              .equals("90")) || 
              (str5.equals("11")) || (str5.equals("12")) || (str5.equals("13")) || (str5.equals("14")) || (str5.equals("15")))
            {
              str6 = localResultSet.getString("ZuMing");
              str7 = localResultSet.getString("BiaoMing");
              i = 1;
              localHttpSession.setAttribute("pa", str8);
              localHttpSession.setAttribute("zh", str6);
              localHttpSession.setAttribute("bz", str7);
              localHttpSession.setAttribute("bm", str7);
              paramHttpServletResponse.sendRedirect("DL.jsp");
              break;
            }

            paramHttpServletResponse.sendRedirect("notime.jsp");
            break;
          }

          String str16 = localResultSet.getString("ZuMing");
          String str17 = localResultSet.getString("BiaoMing");
          i = 1;
          localHttpSession.setAttribute("pa", str8);
          localHttpSession.setAttribute("zh", str16);
          localHttpSession.setAttribute("bz", str7);
          localHttpSession.setAttribute("bm", str17);

          if (str7.substring(0, 2).equals("19")) {
            paramHttpServletResponse.sendRedirect("/admin/index.jsp?admin=" + str7 + "&password=" + str8);
            break;
          }
          if (str7.substring(0, 2).equals("18")) {
            paramHttpServletResponse.sendRedirect("/Online_E_System/index.jsp?admin=" + str7 + "&password=" + str8);
            break;
          }
          if ((str7.substring(0, 2).equals("16")) || (str7.substring(0, 2).equals("17")))
          {
            localHttpSession.setAttribute("username", str7);
            localHttpSession.setAttribute("ShiTang", str9);
            localHttpSession.setAttribute("UserName", str10);
            localHttpSession.setAttribute("Sex", str11);
            localHttpSession.setAttribute("NianLing", str12);
            localHttpSession.setAttribute("ZhiWu", str13);
            localHttpSession.setAttribute("CaiGouNianXian", str14);
            paramHttpServletResponse.sendRedirect("index.jsp");
            break;
          }
          paramHttpServletResponse.sendRedirect("DL.jsp");
          break;
        }

      }

      if (i == 0) localPrintWriter.println("<script>alert('µÇÂ½Ê§°Ü');this.location.href='/YGCG/kitch.jsp';</script>");

      localPreparedStatement.close();
      localConnection.close();
    }
    catch (SQLException localSQLException5) {
      localPrintWriter.println(localSQLException5.toString());
    }
    catch (Exception localSQLException7) {
      localPrintWriter.println(localSQLException7.toString());
    }
    finally {
      try {
        if (localPreparedStatement != null)
          localPreparedStatement.close();
      } catch (SQLException localSQLException8) {
      }
      try {
        if (localConnection != null)
          localConnection.close();
      } catch (SQLException localSQLException9) {
        localSQLException9.printStackTrace();
      }
    }
  }

  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
}