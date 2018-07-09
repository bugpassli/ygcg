import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckBox
 */

public class CheckBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String title = "��ȡ��ѡ������";
		String docType = "<!DOCTYPE html> \n";
			out.println(docType +
	            "<html>\n" +
	            "<head><title>" + title + "</title></head>\n" +
	            "<body bgcolor=\"#f0f0f0\">\n" +
	            "<h1 align=\"center\">" + title + "</h1>\n" +
	            "<ul>\n" +
	            "  <li><b>���񰴽̳̱�ʶ��</b>: "
	            + request.getParameter("runoob") + "\n" +
	            "  <li><b>Google ��ʶ��</b>: "
	            + request.getParameter("google") + "\n" +
	            "  <li><b>�Ա���ʶ��</b>: "
	            + request.getParameter("taobao") + "\n" +
	            "</ul>\n" +
	            "</body></html>");
	}
	
	// ���� POST ��������ķ���
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}