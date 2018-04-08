package com.cwp.cmoneycharge;

import java.util.List;

import com.cwp.cmoneycharge.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.View.OnFocusChangeListener;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.Toast;
import cwp.moneycharge.dao.AccountDAO;
import cwp.moneycharge.dao.ItypeDAO;
import cwp.moneycharge.dao.PtypeDAO;
import cwp.moneycharge.model.Tb_account;  
  
public class Register extends Activity {  
   
private EditText register_username;  
private EditText register_passwd;  
private EditText reregister_passwd;  
List<String> spdatalist;
private Button register_submit;  
AccountDAO accountDAO =new AccountDAO(Register.this);
PtypeDAO ptypeDAO = new PtypeDAO(Register.this);
ItypeDAO itypeDAO = new ItypeDAO(Register.this);

    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
      
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.register);  
        register_username=(EditText)findViewById(R.id.rusername);  
        register_passwd=(EditText)findViewById(R.id.rpassword);  
        reregister_passwd=(EditText)findViewById(R.id.rrpassword);  
       
        register_submit=(Button)findViewById(R.id.btnrRegister);  
        register_username.setOnFocusChangeListener(new OnFocusChangeListener()  
        {  
  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                if(!hasFocus){  
                    if(register_username.getText().toString().trim().length()<4){  
                        Toast.makeText(Register.this, "�û�������С��4���ַ�", Toast.LENGTH_SHORT).show();  
                    }  
                }  
            }  
              
        });  
        register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()  
        {  
  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                if(!hasFocus){  
                    if(register_passwd.getText().toString().trim().length()<6){  
                        Toast.makeText(Register.this, "���벻��С��8���ַ�", Toast.LENGTH_SHORT).show();  
                    }  
                }  
            }  
              
        });  
        reregister_passwd.setOnFocusChangeListener(new OnFocusChangeListener()  
        {  
  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                if(!hasFocus){  
                    if(!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){  
                        Toast.makeText(Register.this, "�����������벻һ��", Toast.LENGTH_SHORT).show();   
                    }  
                }  
            }  
              
        });  
        register_submit.setOnClickListener(new OnClickListener(){  
  
            @Override  
            public void onClick(View v) {  
                  
                if(!checkEdit()){  
                    return;  
                }  
                
                String username = register_username.getText().toString().trim();
                String passwd = register_passwd.getText().toString().trim();
                
                Tb_account tb_account = new Tb_account();
                tb_account.setUsername(username);
                tb_account.setPwd(passwd);
                
                int status =accountDAO.add(tb_account);
               
                if(status!=100000001) {
                	//Ϊ�������û����Ĭ�ϵļ�������
                	 ptypeDAO.initData(status);
                	 itypeDAO.initData(status);
                	 Toast.makeText(Register.this, "ע��ɹ���", Toast.LENGTH_SHORT).show(); 
                	 Intent intent=new Intent(Register.this,Login.class);  
                     startActivity(intent);  
                }else {
             	   Toast.makeText(Register.this, "ע��ʧ�ܣ�", Toast.LENGTH_SHORT).show(); 
                }
            }  
              
        });  
    }  
      
    private boolean checkEdit(){  
        if(register_username.getText().toString().trim().equals("")){  
            Toast.makeText(Register.this, "�û�������Ϊ��", Toast.LENGTH_SHORT).show();  
        }else if(register_passwd.getText().toString().trim().equals("")){  
            Toast.makeText(Register.this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();  
        }else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){  
            Toast.makeText(Register.this, "�����������벻һ��", Toast.LENGTH_SHORT).show();  
        }else{  
            return true;  
        }  
        return false;  
    }  
      
}  
