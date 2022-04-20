package com.iti.mad42.remedicine.Model.pojo;

import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;
import com.iti.mad42.remedicine.data.FacebookAuthentication.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    //functions for Room Database
    public LiveData<List<MedicationPojo>> getAllMedications();
    public void insertMedication(MedicationPojo medication);
    public LiveData<MedicationPojo> getSpecificMedication(String medName);
    public void updateMedication(MedicationPojo med);
    public void deleteMedication(MedicationPojo med);
    public LiveData<List<MedicationPojo>> getActiveMedications(long currentDate);
    public LiveData<List<MedicationPojo>> getInActiveMedication(long currentDate);
    public void updateActiveStateForMedication(long currentDate);
    public void registerListeners();
    public void unregisterListeners();
    public void handleFacebookToken(AccessToken token, NetworkDelegate networkDelegate);
    public void addMedicationToFirebase(MedicationPojo med);
    public void updateMedicationToFirebase(MedicationPojo med);
    //functions for Firebase-RealTime
}