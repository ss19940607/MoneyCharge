package com.cwp.cmoneycharge;


public class AccountName {
	    //����ʽ������.�����ʼ��ʱ���Ѿ�����ʵ����   
	    private AccountName() {}  
	    private static final AccountName singleAccountName = new AccountName();  
	    private int accid=0;
	    
	    //��̬��������   
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
