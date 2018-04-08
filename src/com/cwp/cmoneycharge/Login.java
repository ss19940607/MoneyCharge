package com.cwp.cmoneycharge;

import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.ParseException;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
  
import com.cwp.cmoneycharge.R;
import com.umeng.fb.util.Log;

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
import cwp.moneycharge.model.Tb_account;  
  
public class Login extends Activity implements OnClickListener {  
private EditText login_username;  
private EditText login_password;  
private Button user_login_button;  
private Button user_register_button;  
AccountDAO accountDAO =new AccountDAO(Login.this);

  
    @Override  
protected void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
    
    setContentView(R.layout.login);  
    initWidget();  
  
}  
    private void initWidget()  
    {  
        login_username=(EditText)findViewById(R.id.username);  
        login_password=(EditText)findViewById(R.id.password);  
        user_login_button=(Button)findViewById(R.id.btnLogin);  
        user_register_button=(Button)findViewById(R.id.btnRegister);  
        user_login_button.setOnClickListener(this);  
        user_register_button.setOnClickListener(this);  
        login_username.setOnFocusChangeListener(new OnFocusChangeListener()  
        {  
        	//验证用户名输入的格式是否正确
  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
               
                if(!hasFocus){  
                    String username=login_username.getText().toString().trim();  
                    if(username.length()<4){  
                        Toast.makeText(Login.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT);  
                    }  
                }  
            }  
              
        });  
        login_password.setOnFocusChangeListener(new OnFocusChangeListener()  
        {  
        	//验证密码输入的格式是否正确
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
               
                if(!hasFocus){  
                    String password=login_password.getText().toString().trim();  
                    if(password.length()<4){  
                        Toast.makeText(Login.this, "密码不能小于4个字符", Toast.LENGTH_SHORT);  
                    }  
                }  
            }  
              
        });  
    }  
      
  
    @Override  
    public void onClick(View v) {  
     
        switch(v.getId())  
        {  
        case R.id.btnLogin:  
            if(checkEdit())  
            {  
                login();  
            }  
              
            break;  
        case R.id.btnRegister:  
            Intent intent2=new Intent(Login.this,Register.class);  
            startActivity(intent2);  
            break;  
        }  
    }  
      
    private boolean checkEdit(){  
        if(login_username.getText().toString().trim().equals("")){  
            Toast.makeText(Login.this, "用户名不能为空", Toast.LENGTH_SHORT).show();  
        }else if(login_password.getText().toString().trim().equals("")){  
            Toast.makeText(Login.this, "密码不能为空", Toast.LENGTH_SHORT).show();  
        }else{  
            return true;  
        }  
        return false;  
    }  
      
    private void login(){  
           
           String name = login_username.getText().toString().trim();
           String password = login_password.getText().toString().trim();
           
           //单例类,是通过这个单例类来保存用户名和密码数据
          
           
           Tb_account temp = accountDAO.find(name, password);
           if(temp!=null) {
        	   AccountName accountName = AccountName.getInstance();
               accountName.setCurrentAccountId(temp.get_id());
        	   Intent intent3=new Intent(Login.this,MainActivity.class); 
        	   intent3.putExtra("cwp.id", temp.get_id());
               
        	   try {
               startActivity(intent3);
        	   }catch (Throwable e) {
        		  
        		   Toast.makeText(Login.this, "e="+e.getMessage(), Toast.LENGTH_LONG).show(); 
				// TODO: handle exception
			}
           }else {
        	   Toast.makeText(Login.this, "登录失败！", Toast.LENGTH_SHORT).show(); 
           }
    }  
}  
