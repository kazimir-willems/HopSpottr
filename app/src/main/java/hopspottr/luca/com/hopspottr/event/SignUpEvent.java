package hopspottr.luca.com.hopspottr.event;

import hopspottr.luca.com.hopspottr.vo.SignUpResponseVo;

/**
 * Created by Arthur on 8/14/2017.
 */

public class SignUpEvent {
    private SignUpResponseVo responseVo;

    public SignUpEvent(SignUpResponseVo responseVo) {
        this.responseVo = responseVo;
    }

    public SignUpResponseVo getResponse() {
        return responseVo;
    }
}
