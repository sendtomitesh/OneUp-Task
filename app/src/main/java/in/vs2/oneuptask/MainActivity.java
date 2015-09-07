package in.vs2.oneuptask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import in.vs2.oneuptask.adapters.ProductAdapter;
import in.vs2.oneuptask.models.PreferenceData;
import in.vs2.oneuptask.models.Product;

public class MainActivity extends AppCompatActivity {


    String name,email;
    ListView listView;

    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        name = PreferenceData.getStringPrefs(PreferenceData.KEY_NAME,this);
        email = PreferenceData.getStringPrefs(PreferenceData.KEY_EMAIL,this);
        getSupportActionBar().setTitle(name);
        //getSupportActionBar().setSubtitle(email);



        adapter = new ProductAdapter(Product.getProducts(),MainActivity.this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Product product = (Product)listView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this,ProductActivity.class);
                intent.putExtra("Product",product);

                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (!PreferenceData.userExist(MainActivity.this)){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */

    public void onLogout(View v){
        PreferenceData.clearPreference(this);
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }
}
