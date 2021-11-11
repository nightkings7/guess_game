package com.zhangxu.guess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textNum, textGuess;
    EditText editInput;
    Button btn_1,btn_2;
    int guess, randomNum;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.sp1);
        textNum=findViewById(R.id.t2);
        textGuess=findViewById(R.id.t3);
        editInput=findViewById(R.id.ed1);
        btn_1=findViewById(R.id.bt1);
        btn_2=findViewById(R.id.bt2);
        randomNum=-1;
        guess=0;
        random=new Random();
        btn_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        randomNum=random.nextInt(11);
                        break;
                    case 1:
                        randomNum=random.nextInt(101);
                        break;
                    case 2:
                        randomNum=random.nextInt(1001);
                        break;
                    default:
                        break;
                }
                guess=0;
                textGuess.setText(String.format(Locale.getDefault(),"您已经尝试了%d次",guess));
                textNum.setTextColor(Color.argb(255,0,0,255));
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(randomNum==-1)
                {
                    Toast.makeText(MainActivity.this,"请先生成随机数",Toast.LENGTH_SHORT).show();
                    return;
                }
                int num;
                try{
                    num=Integer.parseInt(editInput.getText().toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"请在输入框中输入整数",Toast.LENGTH_SHORT).show();
                    return;
                }
                guess++;
                textGuess.setText(String.format(Locale.getDefault(),"您已经尝试了%d次",guess));
                if(num>randomNum)
                {
                    Toast.makeText(MainActivity.this,"您所输入的数字大于随机数",Toast.LENGTH_SHORT).show();
                }
                else if(num<randomNum)
                {
                    Toast.makeText(MainActivity.this,"您所输入的数字小于随机数",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("猜数游戏");
                    builder.setMessage("恭喜你猜对了！");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                    randomNum=-1;
                    textNum.setTextColor(Color.argb(255,255,0,0));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.about){
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setIcon(R.mipmap.ic_launcher);
               builder.setTitle("关于");
               builder.setMessage("这是一个简易的猜数游戏");
               builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
               });
               builder.create().show();
        }
        else if (item.getItemId()==R.id.quit) {
            finish();
        }
        return true;
    }

}