package com.iti.mad42.remedicine.AddNewMedicine.Presenter;

import com.iti.mad42.remedicine.Model.pojo.MedicationPojo;
import com.iti.mad42.remedicine.Model.pojo.MedicineDose;

import java.util.List;

public interface AddNewMedicineActivityPresenterInterface {
    public void setMedicineForm(int pos);
    public void setInterval(int pos);
    public void setMedStrength(int pos);
    public List<MedicineDose> getMedDose(int pos);
    public void openDatePicker(String state);
    public void getData();
    public void openTimePicker();
    public void insertMedication();
}
