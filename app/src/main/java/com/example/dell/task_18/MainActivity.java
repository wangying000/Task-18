package com.example.dell.task_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=(Button)findViewById(R.id.button);
        Button button1=(Button)findViewById(R.id.button2);
        Button button2=(Button)findViewById(R.id.button3);
        final EditText editText=(EditText)findViewById(R.id.editText);
        final EditText editText1=(EditText)findViewById(R.id.editText2);
        final TextView textView=(TextView)findViewById(R.id.textView);
        final TextView textView1=(TextView)findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try{
                    FileOutputStream fileOutputStream=openFileOutput("MyFileName",MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String name=editText.getText().toString();
                    String num=editText1.getText().toString();
                    try{
                        out.write(name.getBytes(StandardCharsets.UTF_8));
                        out.write(num.getBytes(StandardCharsets.UTF_8));

                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"保存成功！",Toast.LENGTH_SHORT).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                String a=editText.getText().toString();
                String b=editText1.getText().toString();
                textView.setText("姓名："+a);
                textView1.setText("学号："+b);
                try{
                    FileInputStream fileInputStream=openFileInput("MyFileName");
                    in=new BufferedInputStream(fileInputStream);
                    byte[]buff=new byte[1024];
                    int c=0;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while((c=in.read(buff))>0){
                            stringBuilder.append(new String(buff,0,c));
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        if (in!=null)
                            in.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                editText1.setText("");
            }
        });
    }
}
