package in.vs2.oneuptask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.auth0.core.Token;
import com.auth0.core.UserProfile;
import com.auth0.lock.Lock;
import com.auth0.lock.LockActivity;

import in.vs2.oneuptask.models.PreferenceData;

/**
 * Created by antarix on 07/09/15.
 */
public class LoginActivity extends AppCompatActivity {

    private LocalBroadcastManager broadcastManager;

    private BroadcastReceiver authenticationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null){
                Log.e("OnReceieve","Intent is null");
                return;
            }
            UserProfile profile = intent.getParcelableExtra("profile");
            Token token = intent.getParcelableExtra("token");
            Log.e("OnReceive", "User " + profile.getName() + " logged in");

            Log.e("OnReceive", "Email " + profile.getEmail());

            Log.e("OnReceive", "Token " + token.getAccessToken());

            PreferenceData.setStringPrefs(PreferenceData.KEY_TOKEN,LoginActivity.this,token.getAccessToken());
            PreferenceData.setStringPrefs(PreferenceData.KEY_NAME,LoginActivity.this,profile.getName());
            PreferenceData.setStringPrefs(PreferenceData.KEY_EMAIL,LoginActivity.this,profile.getEmail());

            Intent lockIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(lockIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Customize your activity

        setContentView(R.layout.activity_login);



        if (PreferenceData.userExist(LoginActivity.this)){
            Intent lockIntent = new Intent(this, MainActivity.class);
            startActivity(lockIntent);
            finish();
        }else{
            broadcastManager = LocalBroadcastManager.getInstance(this);
            broadcastManager.registerReceiver(authenticationReceiver, new IntentFilter(Lock.AUTHENTICATION_ACTION));

            Intent lockIntent = new Intent(this, LockActivity.class);
            startActivity(lockIntent);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastManager != null){
            broadcastManager.unregisterReceiver(authenticationReceiver);
        }

    }
}
