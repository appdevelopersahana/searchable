package com.example.user.searchablerecyclerview.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.searchablerecyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.findById;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.searchEditText)
    EditText mSearchEditText;
    @BindView(R.id.searchButton)
    Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String searchInput=mSearchEditText.getText().toString();
        Intent intent=new Intent(SearchActivity.this, ApplianceActivity.class);
        intent.putExtra("search input",searchInput);
        startActivity(intent);

    }
}
