package VegetablePrice.Table.TableConn;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.util.*;
import VegetablePrice.Table.VegetBean.ProductXinxi;

public class TableConnProduct {
	//获取表中该人员需要的产品
	ProductXinxi px ;
	ArrayList<ProductXinxi> productxinxi = new ArrayList<ProductXinxi>();
	public TableConnProduct(Connection con  , String LeiBie) throws SQLException {
		try{
			String sql = "select * from infobiao where LeiBie =  ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1 ,LeiBie);
			ResultSet rs = ps.executeQuery();
			//记录有多少页
			int id = 0 ;

			while (rs.next()) {
				id++;
				px =  new ProductXinxi();
				px.setId(""+id);
				px.setYongHuMing(rs.getString("YongHuMing"));//用户名
				px.setGongYingShang(rs.getString("GongYingShang"));//供货商
				px.setShangPin(rs.getString("ShangPin")); //商品
				px.setTiGongLiang(rs.getString("TiGongLiang")); //提供量
				px.setDanJia(rs.getString("DanJia"));//单价
				px.setDanWei(rs.getString("DanWei"));	//单位
				px.setXuQiuLiang(rs.getString("XuQiuLiang"));
				
				productxinxi.add(px);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<ProductXinxi> getProductxinxi(){
		return productxinxi;
	}

}