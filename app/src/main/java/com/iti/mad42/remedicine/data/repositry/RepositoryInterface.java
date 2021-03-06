package com.iti.mad42.remedicine.data.repositry;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;
import com.iti.mad42.remedicine.data.pojo.MedicationPojo;
import com.iti.mad42.remedicine.data.pojo.RequestPojo;
import com.iti.mad42.remedicine.data.pojo.User;
import com.iti.mad42.remedicine.data.remoteDataSource.NetworkDelegate;

import java.util.List;

import io.reactivex.Single;

public interface RepositoryInterface {

    //functions for Room Database
    public LiveData<List<MedicationPojo>> getAllMedications();
    public void insertMedication(MedicationPojo medication);
    public Single<MedicationPojo> getSpecificMedication(String medName);
    public void updateMedication(MedicationPojo med);
    public void deleteMedication(MedicationPojo med);
    public LiveData<List<MedicationPojo>> getActiveMedications(long currentDate);
    public LiveData<List<MedicationPojo>> getInActiveMedication(long currentDate);
    public void updateActiveStateForMedication(long currentDate);
    public void registerListeners();
    public void unregisterListeners();
    public void handleFacebookToken(AccessToken token, NetworkDelegate networkDelegate, Context context);
    public void addMedicationToFirebase(MedicationPojo med);
    public void updateMedicationToFirebase(MedicationPojo med);
    public void deleteMedicationFromFirebase(MedicationPojo med);
    public void insertMedfriendUser(User user);
    public LiveData<List<User>> getAllUsers();
    //functions for Firebase-RealTime
    public void sendRequest(RequestPojo request);
    public void getAllRequests(String receiverEmail);
    public void setNetworkDelegate(NetworkDelegate networkDelegate);
    public void rejectRequest(RequestPojo request);
    public void updateRequestStateWhenAccept(RequestPojo request);
    Single<List<MedicationPojo>> getAllMedicationsList();
    public void getAllMedicationFromFBForCurrentMedOwner(String medOwnerEmail, OnlineDataInterface onlineDataInterface);
    public Single<List<MedicationPojo>> getMedicationsToRefillReminder(long refillTime);
    void deleteMedications();
    void deleteUsers();
//    public void getMedicationFromFB(String medOwnerEmail,String medName, OnlineDataInterface onlineDataInterface);
    public void deleteFromFirebase();

}
