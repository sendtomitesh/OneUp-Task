package in.vs2.oneuptask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationType;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;

import in.vs2.oneuptask.models.PreferenceData;
import in.vs2.oneuptask.models.Product;

public class ProductActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    TextView textTitle,textPrice;
    ImageView imageProduct;
    Product product;

    // Change to your publishable key!!!
    public static final String PUBLISHABLE_KEY = "pk_test_6pRNASCoBOKtIshFeQd4XMUh";

    // Unique identifiers for asynchronous requests:
    private static final int LOAD_MASKED_WALLET_REQUEST_CODE = 1000;
    private static final int LOAD_FULL_WALLET_REQUEST_CODE = 1001;

    private SupportWalletFragment walletFragment;
    private boolean isCalled=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        textTitle = (TextView) findViewById(R.id.title);
        textPrice = (TextView) findViewById(R.id.desc);
        imageProduct = (ImageView) findViewById(R.id.icon);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().hasExtra("Product")){

            product = (Product)getIntent().getSerializableExtra("Product");

            textTitle.setText(product.getName());
            textPrice.setText(product.getPrice());
            imageProduct.setImageResource(product.getImageResource());
        }


        //Wallet code
        walletFragment =(SupportWalletFragment)getSupportFragmentManager().findFragmentById(R.id.wallet_fragment);

        MaskedWalletRequest maskedWalletRequest = MaskedWalletRequest.newBuilder()

                // Request credit card tokenization with Stripe by specifying tokenization parameters:
                .setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters.newBuilder()
                        .setPaymentMethodTokenizationType(PaymentMethodTokenizationType.PAYMENT_GATEWAY)
                        .addParameter("gateway", "stripe")
                        .addParameter("stripe:publishableKey", PUBLISHABLE_KEY)
                        .addParameter("stripe:version", com.stripe.Stripe.VERSION)
                        .build())

                        // You want the shipping address:
                .setShippingAddressRequired(true)

                        // Price set as a decimal:
                .setEstimatedTotalPrice("20.00")
                .setCurrencyCode("USD")
                .build();

        // Set the parameters:
        WalletFragmentInitParams initParams = WalletFragmentInitParams.newBuilder()
                .setMaskedWalletRequest(maskedWalletRequest)
                .setMaskedWalletRequestCode(LOAD_MASKED_WALLET_REQUEST_CODE)
                .build();

        // Initialize the fragment:
        walletFragment.initialize(initParams);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!PreferenceData.userExist(this)){
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
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("result", "" + resultCode);
        if(!isCalled) {
            startActivity(new Intent(ProductActivity.this, ThankYouActivity.class));
            isCalled=true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
