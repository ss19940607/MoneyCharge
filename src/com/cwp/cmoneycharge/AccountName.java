package com.cwp.cmoneycharge;


public class AccountName {
	    //饿汉式单例类.在类初始化时，已经自行实例化   
	    private AccountName() {}  
	    private static final AccountName singleAccountName = new AccountName();  
	    private int accid=0;
	    
	    //静态工厂方法   
	    public static AccountName getInstance() {  
	        return singleAccountName;  
	    }  
	    
	    public void setCurrentAccountId(int accid) {
	    	this.accid=accid;
	    }
		   public int getCurrentAccountId() {
			   return this.accid;
		   }
		  
}
