package com.nasa.pictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.agrawalsuneet.dotsloader.loaders.LinearDotsLoader;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class splashScreen extends AppCompatActivity {

    splashScreen splashActivity;
    Context context;
    AppUpdateManager UpdateManager;
    LinearDotsLoader containerLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        context = splashActivity = this;
        UpdateManager = AppUpdateManagerFactory.create(context);
        UpdateApp();

        containerLL = (LinearDotsLoader) findViewById(R.id.bestloader2);


        LinearDotsLoader loader = new LinearDotsLoader(this);
        loader.setDefaultColor(ContextCompat.getColor(this, R.color.loader_defalut));
        loader.setSelectedColor(ContextCompat.getColor(this, R.color.loader_selected));
        loader.setSingleDir(true);
        loader.setNoOfDots(5);
        loader.setSelRadius(40);
        loader.setExpandOnSelect(true);
        loader.setRadius(30);
        loader.setDotsDistance(20);
        loader.setAnimDur(100);
        loader.setShowRunningShadow(true);
        loader.setFirstShadowColor(ContextCompat.getColor(this, R.color.blue_delfault));
        loader.setSecondShadowColor(ContextCompat.getColor(this, R.color.blue_selected));


    }


    @Override
    protected void onResume() {
        super.onResume();

        splashActivity = this;

        UpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    UpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, IMMEDIATE, splashActivity, 101);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void HomeScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void UpdateApp() {
        try {
            Task<AppUpdateInfo> appUpdateInfoTask = UpdateManager.getAppUpdateInfo();
            appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)) {
                    try {
                        UpdateManager.startUpdateFlowForResult(
                                appUpdateInfo, IMMEDIATE, splashActivity, 101);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                } else {
                    HomeScreen();
                }
            }).addOnFailureListener(e -> {
                e.printStackTrace();
                HomeScreen();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode != RESULT_OK) {
                HomeScreen();
            } else {
                HomeScreen();
            }
        }
    }


}