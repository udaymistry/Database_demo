package com.iamawsome.database_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    MyDatabaseAdapter myDatabaseAdapter;
    EditText username,password,getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseAdapter = new MyDatabaseAdapter(this);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        getPassword = (EditText) findViewById(R.id.userpass);
    }


    public void addnewuser(View view)

    {
        String name  = username .getText().toString();
        String passwrd = password.getText().toString();

        long id =myDatabaseAdapter.insertdata(name,passwrd);

        if (id < 0)
        {
            Message.message(this,"Unsucessfull");
        }
        else
        {
            Message.message(this,"Row Insertion Completed Successfully");
        }


    }

public void viewalldata(View view)
{
    String dta = myDatabaseAdapter.getdata();

    Message.message(this,dta);
}

    public void magic(View view)
    {
        String susan = getPassword.getText().toString();
        String merry = myDatabaseAdapter.fetchData(susan);
        Message.message(this,merry);
    }
}
