package com.iti.mad42.remedicine.showMedicines.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.mad42.remedicine.data.pojo.MedicationPojo;
import com.iti.mad42.remedicine.utility.Utility;
import com.iti.mad42.remedicine.R;
import java.util.List;

public class ActiveMedicationsAdapter extends RecyclerView.Adapter<ActiveMedicationsAdapter.ViewHolder> {
    private final Context context;
    private List<MedicationPojo> myMeds;
    private OnItemClickListenerInterface mListener;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView medName, medSubtitle;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder( View layout) {
            super(layout);
            this.layout = layout;
            medName = layout.findViewById(R.id.txtViewHomeMedName);
            medSubtitle = layout.findViewById(R.id.txtViewHomeMedSubtitle);
            constraintLayout = layout.findViewById(R.id.constrainLayoutHomeChildItem);
        }

    }

    public ActiveMedicationsAdapter(Context _context, List<MedicationPojo> myMeds){
        this.context = _context;
        this.myMeds = myMeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View layout = inflater.inflate(R.layout.med_fragment_custom_cell, parent,false);
        ViewHolder myViewHolder = new ViewHolder(layout);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Log.i("TAG", "size is: "+myMeds.size());
        holder.medName.setText(myMeds.get(position).getName());
        holder.medSubtitle.setText(myMeds.get(position).getStrength()+" "+ Utility.medStrengthUnit[myMeds.get(position).getStrengthUnitIndex()] +" Take "+ myMeds.get(position).getMedDoseReminders().get(0).getMedDose()+" "+myMeds.get(position).getMedDoseReminders().get(0).getMedForm()+" (s)"+myMeds.get(position).getInstructions());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null) {
                    int pos = position;
                    if (pos != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(myMeds.get(position));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "size is: "+myMeds.size());
        if (myMeds.size() == 0)
            return 0;
        return myMeds.size();
    }
    public void setOnItemClickListener(OnItemClickListenerInterface listener){
        mListener = listener;
    }
    public void setList(List<MedicationPojo> meds){
        this.myMeds = meds;
        Log.e("mando", "setList: "+myMeds.size()+"||||||"+meds.size());
    }
}
