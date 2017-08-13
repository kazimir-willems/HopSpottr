package hopspottr.luca.com.hopspottr.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import hopspottr.luca.com.hopspottr.R;

public class LoginActivity extends AppCompatActivity {

    private LoginButton btnFbLogin;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnFbLogin = (LoginButton) findViewById(R.id.btn_fb_login);
        facebookSDKInitialize();

        AccessToken token = AccessToken.getCurrentAccessToken();

        if (token != null ) {
            LoginManager.getInstance().logOut();
        }

        btnFbLogin.setReadPermissions(Arrays.asList("email", "user_photos", "user_friends", "public_profile"));
        btnFbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("facebook login : ", "success");
                AccessToken accessToken = loginResult.getAccessToken();
                setLoginAction(accessToken);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void setLoginAction(AccessToken token) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }
}
