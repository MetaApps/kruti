package com.a6studios.kruti.package_AskQuery;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.a6studios.kruti.R;
import com.a6studios.kruti.package_QueryForm.QueryFormActivity;

public class AskQueryActivity extends Activity implements View.OnClickListener {


    FloatingActionButton add_query_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_query);

        add_query_fab = (FloatingActionButton) findViewById(R.id.add_query_fab);
        add_query_fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == add_query_fab){
            Intent intent = new Intent(this, QueryFormActivity.class);
            startActivity(intent);
        }
    }
}
