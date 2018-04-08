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
                        Toast.makeText(Register.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();  
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
                        Toast.makeText(Register.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();  
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
                        Toast.makeText(Register.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();   
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
                	//为新增的用户添加默认的记账类型
                	 ptypeDAO.initData(status);
                	 itypeDAO.initData(status);
                	 Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show(); 
                	 Intent intent=new Intent(Register.this,Login.class);  
                     startActivity(intent);  
                }else {
             	   Toast.makeText(Register.this, "注册失败！", Toast.LENGTH_SHORT).show(); 
                }
            }  
              
        });  
    }  
      
    private boolean checkEdit(){  
        if(register_username.getText().toString().trim().equals("")){  
            Toast.makeText(Register.this, "用户名不能为空", Toast.LENGTH_SHORT).show();  
        }else if(register_passwd.getText().toString().trim().equals("")){  
            Toast.makeText(Register.this, "密码不能为空", Toast.LENGTH_SHORT).show();  
        }else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){  
            Toast.makeText(Register.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();  
        }else{  
            return true;  
        }  
        return false;  
    }  
      
}  
