package hopspottr.luca.com.hopspottr.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.Arrays;

import dmax.dialog.SpotsDialog;
import hopspottr.luca.com.hopspottr.R;
import hopspottr.luca.com.hopspottr.event.SignUpEvent;
import hopspottr.luca.com.hopspottr.task.SignUpTask;
import hopspottr.luca.com.hopspottr.vo.SignUpResponseVo;

public class LoginActivity extends AppCompatActivity {

    private LoginButton btnFbLogin;
    private CallbackManager callbackManager;

    private AlertDialog progressDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDlg = new SpotsDialog(LoginActivity.this);

        btnFbLogin = (LoginButton) findViewById(R.id.btn_fb_login);
        facebookSDKInitialize();

        AccessToken token = AccessToken.getCurrentAccessToken();

        if (token != null ) {
            setLoginAction(token);
            //LoginManager.getInstance().logOut();
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
        GraphRequest request = GraphRequest.newMeRequest(
                token, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {
                        hideProgressDialog();

                        if (response.getError() != null) {
                            // handle error
                        } else {
                            Log.v("JSON Data", response.toString());
                            String email = me.optString("email");
                            String id = me.optString("id");
                            String firstName = me.optString("first_name");
                            String lastName = me.optString("last_name");
                            String source = "";
                            if(me.optString("cover") != null) {
                                source = me.optString("cover");
                            }

                            SignUpTask task = new SignUpTask();
                            task.execute(id, firstName, lastName, source);

                            progressDlg.show();

                            // send email and id to your web server
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location, name, age_range, cover");
        request.setParameters(parameters);

        progressDlg.show();

        request.executeAsync();
    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onSignupEvent(SignUpEvent event) {
        hideProgressDialog();
        SignUpResponseVo responseVo = event.getResponse();

        if(responseVo != null) {
            if(responseVo.success == 1) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(intent);
                finish();
            } else {
                Log.v("Login Result", responseVo.result);
            }
        } else {
            Toast.makeText(LoginActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void hideProgressDialog() {
        if(progressDlg.isShowing())
            progressDlg.dismiss();
    }
}
