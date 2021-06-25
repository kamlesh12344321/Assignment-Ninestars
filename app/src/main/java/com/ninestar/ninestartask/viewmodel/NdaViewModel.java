package com.ninestar.ninestartask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ninestar.ninestartask.model.DocsItem;
import com.ninestar.ninestartask.model.NDAResponse;
import com.ninestar.ninestartask.repository.NdaRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.ninestar.ninestartask.utils.Constants.QUERY;

public class NdaViewModel extends AndroidViewModel {
    private NdaRepository ndaRepository;
    private LiveData<NDAResponse> ndaResponseLiveData;

    public NdaViewModel(@NonNull @NotNull Application application) {
        super(application);
        ndaRepository = new NdaRepository();
        this.ndaResponseLiveData = ndaRepository.getNdaResponse();
    }

    public LiveData<NDAResponse> getDocItemListLiveData() {
        return ndaResponseLiveData;
    }
}
