package com.example.administrator.mycaculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //计算按钮
    private Button jisuan;
    //体重输入框
    private EditText weight1;
    //男性选择框
    private RadioButton man;
    //女性选择框
    private RadioButton woman;
    //显示结果
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jisuan=(Button)findViewById(R.id.calculate);
        weight1=(EditText)findViewById(R.id.weight);
        man=(RadioButton)findViewById(R.id.man);
        woman=(RadioButton)findViewById(R.id.woman);
        result=(TextView)findViewById(R.id.result);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        jisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weight1.getText().toString().trim().equals("")){
                    if(man.isChecked()||woman.isChecked()){
                        Double weight2=Double.parseDouble(weight1.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("-------评估结果-----\n");

                        if(man.isChecked()){
                            sb.append("男性标准身高：");
                            //计算
                            double result=evaluateHeight(weight2,"男");
                            sb.append((int)result+"厘米");
                        }else{
                            sb.append("女性标准身高");
                            //计算
                            double result=evaluateHeight(weight2,"女");
                            sb.append((int)result+"厘米");
                        }
                        //计算结果
                        result.setText(sb.toString());
                    }else{
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请输入体重");
                }
            }
        });

    }
    private void showMessage(String message){
        AlertDialog alert =new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener(){
            public  void onClick(DialogInterface dialog,int which){

            }
        });
        alert.show();
    }
    private  double evaluateHeight(double weight2,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight2)/0.6;

        }else{
            height=158-(52-weight2)/0.5;

        }
        return height;
    }
    public  boolean onCreateOptionMenu(Menu menu){
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

