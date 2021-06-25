package com.ninestar.ninestartask.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninestar.ninestartask.R;
import com.ninestar.ninestartask.adapter.NdaAdapter;
import com.ninestar.ninestartask.database.NdaDatabase;
import com.ninestar.ninestartask.model.DocsItem;
import com.ninestar.ninestartask.utils.Constants;
import com.ninestar.ninestartask.viewmodel.NdaViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private RecyclerView mNdaRecyclerView;
    private ProgressBar mProgressBar;
    private LinearLayoutManager mLayoutManager;
    private NdaAdapter mNdaAdapter;
    private ArrayList<DocsItem> mNdaArrayList = new ArrayList<>();
    private NdaViewModel mNdaViewModel;
    private TextView mInternetStatusTV;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        if (isConnectionAvailable(HomeActivity.this)) {
            getDatabaseDocItems();
        } else {
            mProgressBar.setVisibility(View.GONE);
            mInternetStatusTV.setVisibility(View.VISIBLE);
        }
        mNdaAdapter.setOnItemClickListener((position, v, docsItem) -> {
            Intent intent = new Intent(HomeActivity.this, NDADetailActivity.class);
            intent.putExtra(Constants.KEY_DOC_INTENT, (Serializable) docsItem);
            startActivity(intent);
        });
    }

    private void initialization() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mNdaRecyclerView = findViewById(R.id.nda_recycler_view);
        mInternetStatusTV = findViewById(R.id.tv_internet);
        mLayoutManager = new LinearLayoutManager(HomeActivity.this);
        mNdaRecyclerView.setLayoutManager(mLayoutManager);
        mNdaRecyclerView.hasFixedSize();
        mNdaAdapter = new NdaAdapter(HomeActivity.this, mNdaArrayList);
        mNdaRecyclerView.setAdapter(mNdaAdapter);
        /* Initializing NDAViewModel to making API call */
        mNdaViewModel = new ViewModelProvider(HomeActivity.this).get(NdaViewModel.class);
    }

    /* Checking internet connection availability */
    private boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isConnectionAvailable(getApplicationContext())) {
            mInternetStatusTV.setVisibility(View.GONE);
        }
    }
    /* Observing DocItem from database and loading to recyclerView */
    public void getDatabaseDocItems() {
        NdaDatabase ndaDatabase;
        ndaDatabase = NdaDatabase.getInstance();
        ndaDatabase.mDao_docsItem().getAllDocData().observe(this, docsItems -> {
            if (!docsItems.isEmpty()) {
                mProgressBar.setVisibility(View.GONE);
                List<DocsItem> ndaList = docsItems;
                mNdaArrayList.addAll(ndaList);
                mNdaAdapter.notifyDataSetChanged();

            }
        });
    }
}