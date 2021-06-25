package com.ninestar.ninestartask.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ninestar.ninestartask.R;
import com.ninestar.ninestartask.model.DocsItem;
import com.ninestar.ninestartask.utils.Constants;
import com.ninestar.ninestartask.utils.Utility;

import java.util.stream.Collectors;

public class NDADetailActivity extends BaseActivity {
    private DocsItem mDocsItem;
    private TextView mJournal, mDate_detail, mArticle_type, mAuthor, mTitle, mDescription;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_ndadetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        fillData();
    }

    private void fillData() {
        mJournal.setText(mDocsItem.getJournal());
        mDate_detail.setText(Utility.getDate1(mDocsItem.getPublicationDate()));
        mArticle_type.setText(mDocsItem.getArticleType());
        mAuthor.setText(Utility.getAuthorString(mDocsItem.getAuthorDisplay()));
        mTitle.setText(mDocsItem.getTitleDisplay());
        String string = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
             string = mDocsItem.getJsonMemberAbstract().stream().map(Object::toString).collect(Collectors.joining(""));
        }
        mDescription.setText(string);
    }

    private void initialization() {
        /* Getting DocItem intent data for detail screen setting data to relevant item */
        if (getIntent() != null) {
            mDocsItem = (DocsItem) getIntent().getSerializableExtra(Constants.KEY_DOC_INTENT);
        }
        mJournal = findViewById(R.id.tv_journal);
        mDate_detail = findViewById(R.id.tv_date_detail);
        mArticle_type = findViewById(R.id.tv_article_type);
        mAuthor = findViewById(R.id.tv_author);
        mTitle = findViewById(R.id.tv_title);
        mDescription = findViewById(R.id.tv_description);
    }
}