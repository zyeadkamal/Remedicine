package com.iti.mad42.remedicine.showMedicines.Presenter;

import androidx.lifecycle.LiveData;

import com.iti.mad42.remedicine.data.pojo.MedicationPojo;

import java.util.List;

public interface ShowMedicationsPresenterInterface {
    public LiveData<List<MedicationPojo>> getActiveMedications(long currentDate);
    public LiveData<List<MedicationPojo>> getInActiveMedications(long currentDate);
    public String getSharedPref();
    public void getOnlineData(String medFriendEmail);
}
