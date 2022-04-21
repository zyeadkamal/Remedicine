package com.iti.mad42.remedicine.MyAccount.View;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.iti.mad42.remedicine.Model.database.ConcreteLocalDataSource;
import com.iti.mad42.remedicine.Model.pojo.Repository;
import com.iti.mad42.remedicine.Model.pojo.RequestPojo;
import com.iti.mad42.remedicine.Model.pojo.Utility;
import com.iti.mad42.remedicine.MyAccount.Presenter.MyAccountPresenter;
import com.iti.mad42.remedicine.MyAccount.Presenter.MyAccountPresenterInterface;
import com.iti.mad42.remedicine.R;
import com.iti.mad42.remedicine.Requests.View.RequestsViewActivity;
import com.iti.mad42.remedicine.data.FacebookAuthentication.RemoteDataSource;
import com.iti.mad42.remedicine.login.view.view.LoginActivity;


public class MyAccountFragment extends Fragment implements MyAccountFragmentInterface {

    ConstraintLayout myRequestsConst, addMedfriendConst, switchAcc, logoutConst;
    Dialog addMedfriendDialog, switchAccountDialog;
    Intent intent;
    MyAccountPresenterInterface presenter;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyAccountPresenter(getActivity().getApplicationContext(),this, Repository.getInstance(getContext(), ConcreteLocalDataSource.getInstance(getContext()), RemoteDataSource.getInstance(getContext(), new CallbackManager() {
            @Override
            public boolean onActivityResult(int i, int i1, @Nullable Intent intent) {
                return false;
            }
        })));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        initAccountScreenUI(view);
        goToMyRequestsScreen();
        goToAddMedFriendDialog();
        goToSwitchAccountScreen();
        doLogoutFromAccount();
        return view;
    }

    public void initAccountScreenUI(View view){
        myRequestsConst = view.findViewById(R.id.onClickRequests);
        addMedfriendConst = view.findViewById(R.id.onClickAddMedfriend);
        switchAcc = view.findViewById(R.id.onClickSwitch);
        logoutConst = view.findViewById(R.id.onClickLogout);
        addMedfriendDialog = new Dialog(getContext());
        switchAccountDialog = new Dialog(getContext());
    }

    private void goToMyRequestsScreen(){
        myRequestsConst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRequests = new Intent(view.getContext(),RequestsViewActivity.class);
                startActivity(goToRequests);
            }
        });
    }

    private void goToAddMedFriendDialog(){
        addMedfriendConst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMedfriendDialog();
            }
        });
    }

    private void goToSwitchAccountScreen(){
        switchAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSwitchAccountListDialog();
            }
        });
    }

    private void doLogoutFromAccount(){
        logoutConst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutFromApp();
            }
        });
    }

    public void openAddMedfriendDialog(){
        addMedfriendDialog.setContentView(R.layout.add_medfriend_dialog);
        addMedfriendDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button sendBtn, cancelBtn;
        EditText receiverEmailEdt;
        sendBtn = addMedfriendDialog.findViewById(R.id.sendMedBtn);
        cancelBtn = addMedfriendDialog.findViewById(R.id.cancelMedBtn);
        receiverEmailEdt = addMedfriendDialog.findViewById(R.id.sendRequestEdt);
        ImageView closeDialog = addMedfriendDialog.findViewById(R.id.dialogCloseBtn);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedfriendDialog.dismiss();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sendRequest(new RequestPojo(getString(Utility.myCredentials), receiverEmailEdt.getText().toString(), "notFriend"));
                addMedfriendDialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedfriendDialog.dismiss();
            }
        });

        addMedfriendDialog.show();
    }
    public void openSwitchAccountListDialog(){
        switchAccountDialog.setContentView(R.layout.medfriends_list);
        switchAccountDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView closeDialog = switchAccountDialog.findViewById(R.id.medfriensListCloseBtn);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchAccountDialog.dismiss();
            }
        });


        switchAccountDialog.show();
    }

    public String getString(String key){
        SharedPreferences sharedPreferences=
                getContext().getSharedPreferences("LoginTest",MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }

    private void logoutFromApp() {
        if (AccessToken.getCurrentAccessToken() != null && com.facebook.Profile.getCurrentProfile() != null){
            LoginManager.getInstance().logOut();
            Log.i("SharedPrefs", "onLogout: " + LoginManager.getInstance().getAuthType());

        }
        presenter.saveString(Utility.myCredentials,null);
        SharedPreferences prefs = getContext().getSharedPreferences("LoginTest", MODE_PRIVATE);
        String name = prefs.getString(Utility.myCredentials, null);//"No name defined" is the default value.
        Log.i("SharedPrefs", "onCreate: " + name);
        intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}