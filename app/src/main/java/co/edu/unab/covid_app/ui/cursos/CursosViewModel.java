package co.edu.unab.covid_app.ui.cursos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CursosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CursosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cursos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}