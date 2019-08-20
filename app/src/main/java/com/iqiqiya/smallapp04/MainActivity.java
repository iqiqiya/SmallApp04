package com.iqiqiya.smallapp04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtPhone;
    private EditText mEtPasswd;
    private CheckBox mCBPSD;
    private String TAG = "MainActivity";
    private String SP_PHONE = "sp_phone";
    private String SP_PASSWD = "sp_passwd";
    private String  SP_IS_REMEMBER_PSD = "sp_is_remember_psd";
    private SharedPreferences sharedPreferences;
    private boolean mIsChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initUI();
        //初始化数据
        initData();
    }
    private void initData(){
        //实例化
        if(sharedPreferences==null){
            sharedPreferences = getApplicationContext().getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        //回显数据
        mEtPhone.setText(sharedPreferences.getString(SP_PHONE,""));
        mEtPasswd.setText(sharedPreferences.getString(SP_PASSWD,""));

        mIsChecked = sharedPreferences.getBoolean(SP_IS_REMEMBER_PSD,false);
        mCBPSD.setChecked(mIsChecked);
    }

    private void initUI(){
        mEtPhone = findViewById(R.id.et_account);
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //文本改变之后记录用户账号
                if (mIsChecked){
                    if (sharedPreferences == null){
                        sharedPreferences = getApplicationContext().getSharedPreferences("config",Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_PHONE,mEtPhone.getText().toString());
                    edit.commit();
                }
            }
        });

        mEtPasswd = findViewById(R.id.et_passwd);
        mEtPasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //文本改变之后记录用户密码
                if (mIsChecked){
                    if (sharedPreferences == null){
                        sharedPreferences = getApplicationContext().getSharedPreferences("config",Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_PASSWD,mEtPasswd.getText().toString());
                    edit.commit();
                }
            }
        });

        //获取多选框控件
        mCBPSD = findViewById(R.id.cb_remember_psd);
        mCBPSD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.d(TAG,"状态为" + isChecked);
            mIsChecked = isChecked;
            if (isChecked){
                //实例化SharedPreference的对象
                if (sharedPreferences == null){
                    sharedPreferences = getApplicationContext().getSharedPreferences("config",Context.MODE_PRIVATE);
                }
                //实例化SharedPreference的编辑者对象
                SharedPreferences.Editor edit = sharedPreferences.edit();
                //存储数据
                edit.putString(SP_PHONE,mEtPhone.getText().toString());
                edit.putString(SP_PASSWD,mEtPasswd.getText().toString());
                edit.putBoolean(SP_IS_REMEMBER_PSD,isChecked);
                //提交
                edit.commit();
            }
        }
        });
    }
}
