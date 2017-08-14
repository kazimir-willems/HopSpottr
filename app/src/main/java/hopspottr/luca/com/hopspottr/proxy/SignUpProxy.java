package hopspottr.luca.com.hopspottr.proxy;

import com.google.gson.Gson;

import java.io.IOException;

import hopspottr.luca.com.hopspottr.util.URLManager;
import hopspottr.luca.com.hopspottr.vo.SignUpResponseVo;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class SignUpProxy extends BaseProxy {

    public SignUpResponseVo run(String id, String firstName, String lastName, String cover) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("id", id);
        formBuilder.add("firstname", firstName);
        formBuilder.add("lastname", lastName);
        formBuilder.add("coverphoto", cover);

        RequestBody formBody = formBuilder.build();

        String contentString = postPlain(URLManager.getSingUpURL(), formBody);

        SignUpResponseVo responseVo = new Gson().fromJson(contentString, SignUpResponseVo.class);

        return responseVo;
    }
}
