package com.ninestar.ninestartask.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ninestar.ninestartask.model.DocsItem;

import java.util.List;

@Dao
public interface DAO_DocsItem {

    @Insert
    void insertDocItem(DocsItem docsItem);

    @Query("SELECT * from DocsItem")
    List<DocsItem> getDocsItems();

    @Query("DELETE FROM DocsItem")
    void deleteAll();

    @Query("SELECT * FROM DocsItem")
    LiveData<List<DocsItem>> getAllDocData();
}
