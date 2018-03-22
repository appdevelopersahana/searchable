package com.example.user.searchablerecyclerview.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.user.searchablerecyclerview.Adapters.ApplianceAdapter;
import com.example.user.searchablerecyclerview.NetCalls.MakeNetCalls;
import com.example.user.searchablerecyclerview.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplianceActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MakeNetCalls.CallBack {
    @BindView(R.id.applianceRecyclerView)
    RecyclerView mApplianceRecyclerView;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private RecyclerView.LayoutManager mLayoutManager;
    private ApplianceAdapter mApplianceAdapter;
    private Timer mTimer;
    String mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);
        ButterKnife.bind(this);
       // Intent intent = getIntent();
       // mInput = intent.getExtras().getString("search input");
        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        mApplianceRecyclerView.setLayoutManager(mLayoutManager);
        mApplianceRecyclerView.setHasFixedSize(true);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent=getIntent();
                mInput=intent.getExtras().getString("search input");
                if (mInput.equals("tv")) {
                    mProgressBar.setVisibility(View.GONE);
                    MakeNetCalls.getInstance().getProductValues(ApplianceActivity.this, "http://www.mocky.io/v2/5ab377462f00006600ca3750");
                    mSwipeRefreshLayout.setOnRefreshListener(ApplianceActivity.this);
                    mTimer.cancel();
                } else if (mInput.equals("ac")) {
                    mProgressBar.setVisibility(View.GONE);
                    MakeNetCalls.getInstance().getProductValues(ApplianceActivity.this, "http://www.mocky.io/v2/5ab379ce2f00006600ca376e");
                    mSwipeRefreshLayout.setOnRefreshListener(ApplianceActivity.this);
                    mTimer.cancel();
                }

            }
        };

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1000, 1000);
        mApplianceAdapter = new ApplianceAdapter();
        mApplianceRecyclerView.setAdapter(mApplianceAdapter);

    }

    @Override
    public void onRefresh() {
        if (MakeNetCalls.getInstance() != null) {
            switch (mInput) {
                case "tv":
                    MakeNetCalls.getInstance().getProductValues(ApplianceActivity.this, "http://www.mocky.io/v2/5ab377462f00006600ca3750");
                    break;
                case "ac":
                    MakeNetCalls.getInstance().getProductValues(ApplianceActivity.this, "http://www.mocky.io/v2/5ab379ce2f00006600ca376e");
                    break;
            }
        }
    }

    @Override
    public void onSuccess(final List<Object> sellersData) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                if (mApplianceAdapter != null)
                    mApplianceAdapter.setData(sellersData);
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}
