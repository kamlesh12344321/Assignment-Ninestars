package com.ninestar.ninestartask.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ninestar.ninestartask.database.DAO_DocsItem;
import com.ninestar.ninestartask.database.NdaDatabase;
import com.ninestar.ninestartask.model.DocsItem;
import com.ninestar.ninestartask.model.NDAResponse;
import com.ninestar.ninestartask.network.RestApiService;
import com.ninestar.ninestartask.network.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NdaRepository {
    private RestApiService apiRequest;

    public NdaRepository() {
        apiRequest = RetrofitInstance.getRetrofitInstance().create(RestApiService.class);
    }
    /* Making API call and getting response output and storing in mutable livedata */
    public LiveData<NDAResponse> getNdaResponse() {
        final MutableLiveData<NDAResponse> data = new MutableLiveData<>();
        apiRequest.getNdaResponse().enqueue(new Callback<NDAResponse>() {
            @Override
            public void onResponse(Call<NDAResponse> call, Response<NDAResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                    insertData(response.body());
                }
            }

            @Override
            public void onFailure(Call<NDAResponse> call, Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }
    /* Storing NdaResponse to database in form of DocItem list*/
    public void insertData(NDAResponse ndaResponse) {
        Observable<NDAResponse> observable;
        observable = Observable.just(ndaResponse);
        observable.subscribeOn(Schedulers.io()).subscribe(new Observer<NDAResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                Log.i("", "");
            }

            @Override
            public void onNext(@NotNull NDAResponse ndaResponse) {
                List<DocsItem> docsItems = ndaResponse.getResponse().getDocs();
                NdaDatabase db = NdaDatabase.getInstance();
                DAO_DocsItem dao_docsItem = db.mDao_docsItem();
                List<DocsItem> docsItemsFromDB = dao_docsItem.getDocsItems();
                if (docsItemsFromDB != null && docsItemsFromDB.size() > 0) {
                    dao_docsItem.deleteAll();
                }
                for (DocsItem item : docsItems) {
                    dao_docsItem.insertDocItem(item);
                }


            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.i("", "");
            }

            @Override
            public void onComplete() {
                Log.i("", "");
            }
        });
    }

}
