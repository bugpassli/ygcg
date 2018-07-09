package login.MysqlConnection.Table;
public class CanteenOrder {  
    private String canteenID= null;  
	private String id= null;
    private String product = null;  
    private String price = null;  
    private String amount = null;  
    private String money = null; 
	private String power = null;
	private String sumamount = null;
	public CanteenOrder(){
			super();
	}
	public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    } 
    public String getCanteenID() {  
        return canteenID;  
    }  
    public void setCanteenID(String canteenID) {  
        this.canteenID = canteenID;  
    }  
    public String getProduct() {  
        return product;  
    }  
    public void setProduct(String product) {  
        this.product = product;  
    }  
    public String getPrice() {  
        return price;  
    }  
    public void setPrice(String price) {  
        this.price = price;  
    }  
    public String getAmount() {  
        return amount;  
    }  
    public void setAmount(String amount) {  
        this.amount = amount;  
    }  
    public String getMoney() {  
        return money;  
    }  
    public void setMoney(String money) {  
        this.money = money;  
    } 
	
	public String getPower() {  
        return power;  
    }  
    public void setPower(String power) {  
        this.power = power;  
    } 
	public String getSumamount() {  
        return sumamount;  
    }  
    public void setSumamount(String sumamount) {  
        this.sumamount = sumamount;  
    } 
      
}  