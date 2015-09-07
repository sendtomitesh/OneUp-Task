package in.vs2.oneuptask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.vs2.oneuptask.models.PreferenceData;

public class ThankYouActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

    }


    public void onLogout(View v){
        PreferenceData.clearPreference(this);
        finish();
    }
}
