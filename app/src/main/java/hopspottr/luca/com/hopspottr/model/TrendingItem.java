package hopspottr.luca.com.hopspottr.model;

/**
 * Created by Arthur on 8/16/2017.
 */

public class TrendingItem {
    private String photoUrl;
    private int follower;
    private String name;
    private float distance;
    private String address;
    private int crowdLevel;
    private int noiseLevel;
    private int waitLevel;

    public TrendingItem(String arg0, int arg1, String arg2, float arg3, String arg4, int arg5, int arg6, int arg7) {
        this.photoUrl = arg0;
        this.follower = arg1;
        this.name = arg2;
        this.distance = arg3;
        this.address = arg4;
        this.crowdLevel = arg5;
        this.noiseLevel = arg6;
        this.waitLevel = arg7;
    }

    public void setPhotoUrl(String value) {
        this.photoUrl = value;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setFollower(int value) {
        this.follower = value;
    }

    public int getFollower() {
        return follower;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return address;
    }

    public void setDistance(float value) {
        this.distance = value;
    }

    public float getDistance() {
        return distance;
    }

    public void setCrowdLevel(int value) {
        this.crowdLevel = value;
    }

    public int getCrowdLevel() {
        return crowdLevel;
    }

    public void setNoiseLevel(int value) {
        this.noiseLevel = value;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public void setWaitLevel(int value) {
        this.waitLevel = value;
    }

    public int getWaitLevel() {
        return waitLevel;
    }
}
