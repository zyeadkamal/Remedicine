package com.iti.mad42.remedicine.data.repositry;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;
import com.iti.mad42.remedicine.data.localDataSource.LocalDatabaseSourceInterface;
import com.iti.mad42.remedicine.data.pojo.MedicationPojo;
import com.iti.mad42.remedicine.data.pojo.RequestPojo;
import com.iti.mad42.remedicine.data.pojo.User;
import com.iti.mad42.remedicine.utility.Utility;
import com.iti.mad42.remedicine.data.remoteDataSource.NetworkDelegate;
import com.iti.mad42.remedicine.data.remoteDataSource.RemoteDataSourceInterface;

import java.util.List;

import io.reactivex.Single;

public class Repository implements RepositoryInterface, OnlineDataInterface{
    private Context context;
    private LocalDatabaseSourceInterface localDatabaseSource;
    private RemoteDataSourceInterface remoteDataSource;
    private NetworkDelegate networkDelegate;
    private static Repository repository = null;

    private Repository(Context context, LocalDatabaseSourceInterface localDatabaseSource, RemoteDataSourceInterface remoteDataSource){
        this.context = context;
        this.localDatabaseSource = localDatabaseSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(Context context, LocalDatabaseSourceInterface localDatabaseSource, RemoteDataSourceInterface remoteDataSource){
        if(repository == null){
            repository = new Repository(context, localDatabaseSource, remoteDataSource);
        }
        return repository;
    }

    public String getString(String key){
        SharedPreferences sharedPreferences=
                context.getSharedPreferences("LoginTest",MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }

    /// Room Database Methods
    @Override
    public LiveData<List<MedicationPojo>> getAllMedications() {
        return localDatabaseSource.getAllMedications();
    }


    @Override
    public void insertMedication(MedicationPojo medication) {
        localDatabaseSource.insertMedication(medication);
    }

    @Override
    public Single<MedicationPojo> getSpecificMedication(String medName) {
        return localDatabaseSource.getSpecificMedication(medName);
    }

    @Override
    public void updateMedication(MedicationPojo med) {

        localDatabaseSource.updateMedication(med);
    }

    @Override
    public void deleteMedication(MedicationPojo med) {

        localDatabaseSource.deleteMedication(med);
    }

    @Override
    public LiveData<List<MedicationPojo>> getActiveMedications(long currentDate) {
        return localDatabaseSource.getActiveMedication(currentDate);
    }

    @Override
    public LiveData<List<MedicationPojo>> getInActiveMedication(long currentDate) {
        return localDatabaseSource.getInActiveMedications(currentDate);
    }

    @Override
    public void updateActiveStateForMedication(long currentDate) {
        localDatabaseSource.updateActiveStateForMedication(currentDate);
    }

    //Firebase Methods

    public void registerListeners() {
        remoteDataSource.registerListeners();
    }

    public void unregisterListeners() {
        remoteDataSource.unregisterListeners();

    }

    public void handleFacebookToken(AccessToken token, NetworkDelegate networkDelegate, Context context) {
        remoteDataSource.handleFacebookToken(token,networkDelegate, context);
    }
    public void addMedicationToFirebase(MedicationPojo med){
        remoteDataSource.addMedicationToFirebase(med);
    }

    public void updateMedicationToFirebase(MedicationPojo med) {
            remoteDataSource.updateMedicationToFirebase(med);
    }

    @Override
    public void sendRequest(RequestPojo request) {
        remoteDataSource.sendRequest(request);
    }

    @Override
    public void getAllRequests(String receiverEmail) {
        remoteDataSource.getAllRequestsForReceiver(receiverEmail);
    }

    @Override
    public void setNetworkDelegate(NetworkDelegate networkDelegate) {
        this.networkDelegate = networkDelegate;
        remoteDataSource.setNetworkDelegate(networkDelegate);
    }



    @Override
    public Single<List<MedicationPojo>> getAllMedicationsList() {
        return localDatabaseSource.getAllMedicationsList();
    }

    @Override
    public void rejectRequest(RequestPojo request) {
        remoteDataSource.rejectRequest(request);
    }

    @Override
    public void updateRequestStateWhenAccept(RequestPojo request) {
        //remoteDataSource.getUserData(getString(Utility.currentMedFriend));
        Log.e("sandra", ""+getString(Utility.currentMedFriend));
        remoteDataSource.changeRequestStateWhenAccept(request);

    }


    public void deleteMedicationFromFirebase(MedicationPojo med) {
        remoteDataSource.deleteMedicationFromFirebase(med);
    }

    @Override
    public void insertMedfriendUser(User user) {
        Log.e("sanra", "USer from repo "+ user.getEmail());

        localDatabaseSource.insertMedfriendUser(user);
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        return localDatabaseSource.getAllUsers();
    }


    @Override
    public void getAllMedicationFromFBForCurrentMedOwner(String medOwnerEmail, OnlineDataInterface onlineDataInterface) {
        remoteDataSource.getAllMedicationFromFBForCurrentMedOwner(medOwnerEmail,onlineDataInterface);
    }

    @Override
    public Single<List<MedicationPojo>> getMedicationsToRefillReminder(long refillTime) {
        return localDatabaseSource.getMedicationsToRefillReminder(refillTime);
    }

    @Override
    public void deleteMedications() {
        localDatabaseSource.deleteMedications();
    }

    @Override
    public void deleteUsers() {
        localDatabaseSource.deleteUsers();
    }

//    @Override
//    public void getMedicationFromFB(String medOwnerEmail, String medName, OnlineDataInterface onlineDataInterface) {
//       remoteDataSource.getMedicationFromFB(medOwnerEmail,medName,onlineDataInterface);
//    }

    @Override
    public void deleteFromFirebase() {
        remoteDataSource.deleteFromFirebase();
    }

    @Override
    public void onlineDataResult(List<MedicationPojo> friendMedications) {

    }
//
//    @Override
//    public void medDataResult(MedicationPojo medicationPojo) {
//
//    }
}
