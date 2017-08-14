package hopspottr.luca.com.hopspottr.task;

import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import hopspottr.luca.com.hopspottr.event.SignUpEvent;
import hopspottr.luca.com.hopspottr.proxy.SignUpProxy;
import hopspottr.luca.com.hopspottr.vo.SignUpResponseVo;

public class SignUpTask extends AsyncTask<String, Void, SignUpResponseVo> {

    private String id;
    private String firstName;
    private String lastName;
    private String cover;

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected SignUpResponseVo doInBackground(String... params) {
        SignUpProxy simpleProxy = new SignUpProxy();
        id = params[0];
        firstName = params[1];
        lastName = params[2];
        cover = params[3];
        try {
            final SignUpResponseVo responseVo = simpleProxy.run(id, firstName, lastName, cover);

            return responseVo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(SignUpResponseVo responseVo) {
        EventBus.getDefault().post(new SignUpEvent(responseVo));
    }
}