package pdf;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.sql.*;
import java.sql.*;
import login.MysqlConnection.PingTai.DBUtil;
import VegetablePrice.Table.VegetBean.ProductXinxi;
import VegetablePrice.Table.TableConn.TableConnProduct;
import java.util.*;
import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

public class PDFUtil extends HttpServlet{
	/**
	* 创建PDF文档
	* @return
	* @throws Exception
	* @throws docException
	*/
	private PrintWriter out;
	static  String ProductID;
	static String admin;
	static String inputFile ;
	static BaseFont bfChinese ;
	public void doGet(HttpServletRequest request , HttpServletResponse response ) 
		throws ServletException , IOException {
		doPost(request , response);
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response ) 
	 throws ServletException , IOException {
		try{
			 response.setContentType("text/thml:charset=ISO-8859-1");
			 request.setCharacterEncoding("UTF-8");
			 response.setContentType("text/html");
			 response.setCharacterEncoding("UTF-8");
			 out = response.getWriter();
			 String user = request.getParameter("YongHuShang").trim();  //商家用户名
			
			 String CaiGouYuan = request.getParameter("CaiGuoYuan").trim();  //
			 ProductID = request.getParameter("ProductID").trim();  //商品类型
			//连接数据库
		    Connection  con = new DBUtil().getConn();
			//获取数据
			ArrayList<String> ShangJian = new ArrayList<String>();
			ArrayList<String> number = new ArrayList<String>();
			ArrayList<String> PersonName = new ArrayList<String>();
			String sql_admin = "select * from denglu where Name = ? ";
			PreparedStatement ps_admin = con.prepareStatement(sql_admin);
			ps_admin.setString(1  , CaiGouYuan);
			ResultSet rs_admin = ps_admin.executeQuery();
			while(rs_admin.next()) {
				admin = rs_admin.getString("ContactPerson");
			}
			String sql1 = "select * from denglu where ZuMing = ? ";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1  , ProductID);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				ShangJian.add(rs1.getString("Name"));
				number.add(rs1.getString("ContactNumber"));
				PersonName.add(rs1.getString("ContactPerson"));
			}
			System.out.println("用户选择的是"+user);
			String user1 = "NULL";
			String sql10 = "select * from denglu where Name = ? ";
			PreparedStatement ps10 = con.prepareStatement(sql10);
			ps10.setString(1  , user);
			ResultSet rs10 = ps10.executeQuery();
			while(rs10.next()) {
				user1 = rs10.getString("ContactPerson");
			}
			 System.out.println("用户选择的是"+user1);
			//Collections.shuffle(ShangJian);
			//获取该产品种类的数量及产品
			ArrayList<ProductXinxi> Zhonglei = new ArrayList<ProductXinxi>();
			ProductXinxi px ;
			PreparedStatement ps2 = con.prepareStatement( "select * from danwei where zhonglei = ? ");
			ps2.setString(1  , ProductID);
			ResultSet rs2 = ps2.executeQuery();
			int sun = 0 ;
			while(rs2.next()) {
				sun++;
				px = new ProductXinxi();
				px.setId(""+sun);
				px.setShangPin(rs2.getString("name"));
				px.setDanWei(rs2.getString("danwei"));
				Zhonglei.add(px);
			}
			ArrayList<ProductXinxi> userProduct = new TableConnProduct(con , ProductID).getProductxinxi();

			ArrayList<ProductXinxi> zongjia = new ArrayList<ProductXinxi>();
			ProductXinxi px1 ;
			//计算总价
			for (int i = 0 ;  i < ShangJian.size(); i++) {
				String sql = "SELECT sum(DanJia * XuQiuLiang) FROM infobiao where  YongHuMing = ? and LeiBie = ? ";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1 ,ShangJian.get(i));
				ps.setString(2 , ProductID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					px1 = new ProductXinxi();
					px1.setZongJia(rs.getString("sum(DanJia * XuQiuLiang)"));
					zongjia.add(px1);
				}
			}
			String path = createPDF(ShangJian,Zhonglei,PersonName,userProduct,zongjia,number,user1);
			
			stringWaterMark(inputFile , "黄山学院");
			con.close();
			out.write("报表生成成功");
			out.flush();
			out.close();
			


		}
		catch (Exception e) {
			e.printStackTrace();
			try{
			  	if (out!= null) {
					out.flush();  
					out.close();
					System.out.println("生成pdf失败！！");
				}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public static String createPDF(ArrayList<String> ShangJian ,ArrayList<ProductXinxi> Zhonglei, ArrayList<String> PersonName,ArrayList<ProductXinxi> userProduct ,ArrayList<ProductXinxi> zongjia,ArrayList<String> number, String user2) throws Exception {
		 //获取本地时间
		 Date now = new Date(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		 String heretime = dateFormat.format(now);  //本地时间
		 //输出路径
		 String outPath = "pdf/"+heretime+"/";//DataUtil.createTempPath(".pdf");
		 try
		 {
				//设置纸张
				Rectangle rect = new Rectangle(PageSize.A3);
			
				//创建文档实例
				Document doc=new Document(rect);
				
				//添加中文字体
				//BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				BaseFont bfChinese = BaseFont.createFont("pdf/Fonts/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);          //设置字体样式
			   
				Font textFont = new Font(bfChinese,11,Font.NORMAL); //正常
				Font redTextFont = new Font(bfChinese,11,Font.NORMAL,Color.RED); //正常,红色
				Font boldFont = new Font(bfChinese,11,Font.BOLD); //加粗
				Font redBoldFont = new Font(bfChinese,11,Font.BOLD,Color.RED); //加粗,红色
				Font firsetTitleFont = new Font(bfChinese,22,Font.BOLD); //一级标题
				Font secondTitleFont = new Font(bfChinese,15,Font.BOLD); //二级标题
				Font underlineFont = new Font(bfChinese,11,Font.UNDERLINE); //下划线
				File file = new File(outPath);
				if(!file.exists()) {  
					file.mkdirs(); 
				}  
				inputFile = file+"//"+ProductID;
				PdfWriter.getInstance(doc, new FileOutputStream(file+"/"+ProductID+".pdf"));				
				doc.open();
				doc.newPage();
				
				//段落  
				Paragraph p1 = new Paragraph();  
				//短语
				Phrase ph1 = new Phrase();  
				//块
				Chunk c1 = new Chunk("来源:", boldFont) ;
				Chunk c11 = new Chunk("黄山学院后勤集团软件平台", textFont) ;
				//将块添加到短语
				ph1.add(c1);
				ph1.add(c11);
				//将短语添加到段落
				p1.add(ph1);
				//将段落添加到短语
				doc.add(p1);
				
				p1 = new Paragraph();  
				ph1 = new Phrase(); 
				Chunk c2 = new Chunk("报告编号：", boldFont);
				Chunk c22 = new Chunk("HSXY-2017080140001", textFont) ;
				ph1.add(c2);
				ph1.add(c22);
				p1.add(ph1);
				doc.add(p1);
				
				p1 = new Paragraph("采购平台数据报表", firsetTitleFont);
				p1.setLeading(50);
				p1.setAlignment(Element.ALIGN_CENTER);
				doc.add(p1);


				p1 = new Paragraph("（学校版）", textFont);
				p1.setLeading(20);
				p1.setAlignment(Element.ALIGN_CENTER);
				doc.add(p1);
					
				p1 = new Paragraph();  
				p1.setLeading(20);
				p1.setAlignment(Element.ALIGN_CENTER);
				ph1 = new Phrase(); 
				Chunk c3 = new Chunk("查询时间：", boldFont) ;

				Chunk c33 = new Chunk(heretime, textFont) ;
				Chunk c4 = new Chunk(leftPad("查询人：", 10), boldFont) ;
				Chunk c44 = new Chunk(admin+"（用户登录名）", textFont) ;
				ph1.add(c3);
				ph1.add(c33);
				ph1.add(c4);
				ph1.add(c44);
				p1.add(ph1);
				doc.add(p1);
					
				p1 = new Paragraph("报告说明", secondTitleFont);
				p1.setLeading(50);
				p1.setAlignment(Element.ALIGN_CENTER);
				doc.add(p1);
				
				p1 = new Paragraph(" ");  
				p1.setLeading(30);
				doc.add(p1);
				
				p1 = new Paragraph();  
				ph1 = new Phrase(); 
				Chunk c5 = new Chunk("1.本报告由", textFont) ;
				Chunk c6 = new Chunk("黄山学院后勤集团阳光采购报价系统", underlineFont) ;
				c6.setSkew(0, 30);
				Chunk c7 = new Chunk(" 出具，依据截止报告时间采购人员信息数据库记录的信息生成。除异议标注和查询记录外，报告中的信息均由黄山学院后勤集团阳光采购报价系统提供，", textFont);
				Chunk c8 = new Chunk("根据提供的数据来生成数据表格，保证其真实性和准确性，", textFont) ;
				ph1.add(c5);
				ph1.add(c6);
				ph1.add(c7);
				ph1.add(c6);
				ph1.add(c8);
				p1.add(ph1);
				doc.add(p1);


				p1 = new Paragraph();  
				ph1 = new Phrase(); 
				Chunk c9 = new Chunk("2.异议标注是", textFont) ;
				Chunk c10 = new Chunk(" 对报告中的信息记录或对信息主体所作的说明。", textFont);
				ph1.add(c9);
				ph1.add(c6);
				ph1.add(c10);
				p1.add(ph1);
				doc.add(p1);
					
				p1 = new Paragraph("3.信息主体说明是信息主体对报数机构提供的信息记录所作的简要说明。", textFont);  
				doc.add(p1);
				
				p1 = new Paragraph();  
				ph1 = new Phrase(); 
				Chunk c12 = new Chunk("4.信息主体有权对本报告中的内容提出异议。如有异议，可联系报数机构，也可到", textFont) ;
				Chunk c13 = new Chunk(" 提出异议申请。", textFont);
				ph1.add(c12);
				ph1.add(c6);
				ph1.add(c13);
				p1.add(ph1);
				doc.add(p1);
				
				p1 = new Paragraph("5.更多咨询，请致电服务热线0559-2546720。", textFont);  
				doc.add(p1);
				
				p1 = new Paragraph("采购数据", secondTitleFont);
				p1.setSpacingBefore(30);
				p1.setSpacingAfter(30);
				p1.setAlignment(Element.ALIGN_CENTER);
				doc.add(p1);
					  
						
				// 创建一个表格  
				PdfPTable table;
				table = new PdfPTable((5+ShangJian.size()));
				float[] tablewidth = new float[(5+ShangJian.size())]; 
				for (int i = 0 ; i < (5+ShangJian.size()) ; i++){
					tablewidth[i] = 65;
				}
				table.setTotalWidth(tablewidth); //设置列宽
				table.setLockedWidth(true); //锁定列宽
				
				//添加表头信息
				ArrayList<String> tabltop = new ArrayList<String>();
				tabltop.add("#");
				tabltop.add("产品");
				for (int i = 0 ; i < ShangJian.size() ;i++ ) {
					tabltop.add("商家");
				}
				tabltop.add("单位");
				tabltop.add("需求量");
				tabltop.add("单位");
				table = createCell(table, tabltop, PersonName,Zhonglei,ShangJian,userProduct, zongjia, number, (4+Zhonglei.size()), tabltop.size());

				doc.add(table);

                p1 = new Paragraph();  
				p1.setSpacingBefore(20);
				p1.setSpacingAfter(10);
				ph1 = new Phrase(); 
				Chunk sh = new Chunk("经过系统和采购人员的抉择，此次采购商家为："+user2, redBoldFont);
				ph1.add(sh);
				p1.add(ph1);
				doc.add(p1);

				p1 = new Paragraph();  
				p1.setSpacingBefore(20);
				p1.setSpacingAfter(10);
				ph1 = new Phrase(); 
				Chunk c89 = new Chunk("注（该数据是根据采购商选择来制作的，若有数据错误，请及时提出！！）", redBoldFont);
				ph1.add(c89);
				p1.add(ph1);
				doc.add(p1);
				doc.close();
				
				}
				 catch (Exception e) {
					 e.printStackTrace();
				}
				return outPath;
		}

		/**
		* 创建单元格
		* @param table
		* @param row
		* @param cols
		* @return
		* @throws IOException 
		* @throws DocumentException 
		*/
		private static PdfPTable createCell(PdfPTable table, ArrayList<String> title,ArrayList<String> PersonName ,ArrayList<ProductXinxi> Zhonglei,ArrayList<String> ShangJian,ArrayList<ProductXinxi> userProduct ,ArrayList<ProductXinxi> zongjia,ArrayList<String> number, int row, int cols) throws DocumentException, IOException{
			//添加中文字体
			  //  BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			bfChinese = BaseFont.createFont("pdf/Fonts/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);          //设置字体样式

			Font font = new Font(bfChinese,11,Font.BOLD);
			for(int j = 0; j < cols; j++){
				PdfPCell cell = new PdfPCell();
				if(title!=null){//设置表头
					cell = new PdfPCell(new Phrase(title.get(j), font)); //这样表头才能居中
					if(table.getRows().size() == 0){
					 cell.setBorderWidthTop(3);
					}
				}
				if(row==1 && cols==1){ //只有一行一列
					cell.setBorderWidthTop(3);
				}

				if(j==0){//设置左边的边框宽度
					cell.setBorderWidthLeft(3);
				}

				if(j==(cols-1)){//设置右边的边框宽度
					cell.setBorderWidthRight(3);
				}
			   cell.setMinimumHeight(40); //设置单元格高度
			   cell.setUseAscender(true); //设置可以居中
			   cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			   cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中

			   table.addCell(cell);
			}
	        for(int j = 0; j < cols; j++){
				PdfPCell cell = new PdfPCell();
				if(j>=2 && j < 2+PersonName.size()){//设置表头
					cell = new PdfPCell(new Phrase(PersonName.get(j-2), font)); //这样表头才能居中
					if(table.getRows().size() == 0){
					 cell.setBorderWidthTop(3);
					}
				}
			   cell.setMinimumHeight(20); //设置单元格高度
			   cell.setUseAscender(true); //设置可以居中
			   cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			   cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中

			   table.addCell(cell);
			}
			for (int i = 0 ;  i < Zhonglei.size();i++ ) {
				String xuqiliang = "";
				PdfPCell cell = new PdfPCell();
				cell = new PdfPCell(new Phrase(Zhonglei.get(i).getId(), font));
				cell.setMinimumHeight(20); //设置单元格高度
			    cell.setUseAscender(true); //设置可以居中
			    cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			    table.addCell(cell);
				PdfPCell cell2 = new PdfPCell();
				cell2 = new PdfPCell(new Phrase(Zhonglei.get(i).getShangPin(), font));
				cell2.setMinimumHeight(20); //设置单元格高度
			    cell2.setUseAscender(true); //设置可以居中
			    cell2.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			    cell2.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			    table.addCell(cell2);
				for (int j = 0 ; j < ShangJian.size() ; j++ ) {
					for (int k = 0 ; k < userProduct.size() ;k++ ) {
                        if (userProduct.get(k).getShangPin().equals(Zhonglei.get(i).getShangPin()) && 
							ShangJian.get(j).equals(userProduct.get(k).getYongHuMing())) {
							PdfPCell cella = new PdfPCell();
							cella = new PdfPCell(new Phrase(userProduct.get(k).getDanJia(), font));
							cella.setMinimumHeight(20); //设置单元格高度
							cella.setUseAscender(true); //设置可以居中
							cella.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
							cella.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
							table.addCell(cella);
							xuqiliang = userProduct.get(i).getXuQiuLiang();
                        }
					}
				}
				PdfPCell cellb = new PdfPCell();
				cellb = new PdfPCell(new Phrase(Zhonglei.get(i).getDanWei(), font));
				cellb.setMinimumHeight(20); //设置单元格高度
			    cellb.setUseAscender(true); //设置可以居中
			    cellb.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			    cellb.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			    table.addCell(cellb);
				PdfPCell cellc = new PdfPCell();
				cellc = new PdfPCell(new Phrase(xuqiliang, font));
				cellc.setMinimumHeight(20); //设置单元格高度
				cellc.setUseAscender(true); //设置可以居中
				cellc.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
				cellc.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
				table.addCell(cellc);
				PdfPCell celld = new PdfPCell();
				celld = new PdfPCell(new Phrase(Zhonglei.get(i).getDanWei(), font));
				celld.setMinimumHeight(20); //设置单元格高度
			    celld.setUseAscender(true); //设置可以居中
			    celld.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			    celld.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			    table.addCell(celld);
			  
			}
			//商品价格
			PdfPCell cella = new PdfPCell();
			cella = new PdfPCell(new Phrase(String.valueOf(Zhonglei.size()+1), font));
			cella.setMinimumHeight(20); //设置单元格高度
			cella.setUseAscender(true); //设置可以居中
			cella.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cella.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cella);
			PdfPCell cellz = new PdfPCell();
			cellz = new PdfPCell(new Phrase("总价", font));
			cellz.setMinimumHeight(20); //设置单元格高度
			cellz.setUseAscender(true); //设置可以居中
			cellz.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellz.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellz);
			for (int i = 0 ; i < zongjia.size() ;i++ ) {
				PdfPCell cellf = new PdfPCell();
				cellf = new PdfPCell(new Phrase(zongjia.get(i).getZongJia(), font));
				cellf.setMinimumHeight(20); //设置单元格高度
				cellf.setUseAscender(true); //设置可以居中
				cellf.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
				cellf.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
				table.addCell(cellf);
			}
			PdfPCell cellb = new PdfPCell();
			cellb = new PdfPCell(new Phrase("元", font));
			cellb.setMinimumHeight(20); //设置单元格高度
			cellb.setUseAscender(true); //设置可以居中
			cellb.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellb.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellb);

			PdfPCell cellc = new PdfPCell();
			cellc = new PdfPCell(new Phrase("", font));
			cellc.setMinimumHeight(20); //设置单元格高度
			cellc.setUseAscender(true); //设置可以居中
			cellc.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellc.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellc);
			PdfPCell celld = new PdfPCell();
			celld = new PdfPCell(new Phrase("", font));
			celld.setMinimumHeight(20); //设置单元格高度
			celld.setUseAscender(true); //设置可以居中
			celld.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			celld.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(celld);
			//用户的电话
			PdfPCell cellphone1 = new PdfPCell();
			cellphone1 = new PdfPCell(new Phrase(String.valueOf(Zhonglei.size()+2), font));
			cellphone1.setMinimumHeight(20); //设置单元格高度
			cellphone1.setUseAscender(true); //设置可以居中
			cellphone1.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellphone1.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellphone1);
			PdfPCell cellphone2 = new PdfPCell();
			cellphone2 = new PdfPCell(new Phrase("电话号码", font));
			cellphone2.setMinimumHeight(20); //设置单元格高度
			cellphone2.setUseAscender(true); //设置可以居中
			cellphone2.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellphone2.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellphone2);
			for (int i = 0 ; i < number.size() ; i++){
				PdfPCell cell6 = new PdfPCell();
				cell6 = new PdfPCell(new Phrase(number.get(i), font));
				cell6.setMinimumHeight(20); //设置单元格高度
				cell6.setUseAscender(true); //设置可以居中
				cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
				cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
				table.addCell(cell6);
			}
			PdfPCell cellphone3 = new PdfPCell();
			cellphone3 = new PdfPCell(new Phrase("", font));
			cellphone3.setMinimumHeight(20); //设置单元格高度
			cellphone3.setUseAscender(true); //设置可以居中
			cellphone3.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellphone3.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellphone3);
			PdfPCell cellphone4 = new PdfPCell();
			cellphone4 = new PdfPCell(new Phrase("", font));
			cellphone4.setMinimumHeight(20); //设置单元格高度
			cellphone4.setUseAscender(true); //设置可以居中
			cellphone4.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellphone4.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellphone4);
			PdfPCell cellphone5 = new PdfPCell();
			cellphone5 = new PdfPCell(new Phrase("", font));
			cellphone5.setMinimumHeight(20); //设置单元格高度
			cellphone5.setUseAscender(true); //设置可以居中
			cellphone5.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
			cellphone5.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
			table.addCell(cellphone5);
			return table;
		}
		/**
		 * 设置左边距
		 * @param str
		 * @param i
		 * @return
		 */
		public static String leftPad(String str, int i) {
			int addSpaceNo = i-str.length();
			String space = ""; 
			for (int k=0; k<addSpaceNo; k++){
					space= " "+space;
			};
			String result =space + str ;
			return result;
		 }
		 //添加文字水印
		 public static void stringWaterMark(String inputFile, String waterMarkName) {
			try {
				PdfReader reader = new PdfReader(inputFile+".pdf");
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(inputFile+"1.pdf"));
				int total = reader.getNumberOfPages() + 1;
				PdfContentByte under;
				PdfGState gs = new PdfGState();  
				int j = waterMarkName.length();
				char c = 0;
				int rise = 0;
				//给每一页加水印
				for (int i = 1; i < total; i++) {
					rise = 45;
					under = stamper.getUnderContent(i);
					gs.setFillOpacity(0.2f);  
					under.beginText();  
					under.setColorFill(Color.LIGHT_GRAY);  
					under.setFontAndSize(bfChinese, 50);  
					under.setTextMatrix(70, 200);  
					under.showTextAligned(Element.ALIGN_CENTER, "黄山学院后勤集团内部文件，请注意保密！", 500,550, 55);
					// 添加水印文字
					under.endText();
				} 
				stamper.close();
				
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		 }

}