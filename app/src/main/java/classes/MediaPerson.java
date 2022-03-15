package classes;

import java.io.Serializable;

public class MediaPerson implements Serializable {
    private int channelPlace;
    private String channelName;
    private String channelSubscribers;

    // constructor
    public MediaPerson(int channelPlace, String channelName, String channelSubscribers) {
        this.channelPlace = channelPlace;
        this.channelName = channelName;
        this.channelSubscribers = channelSubscribers;
    }
    public MediaPerson(String channelName, String channelSubscribers) {
        this.channelName = channelName;
        this.channelSubscribers = channelSubscribers;
    }

    // getter
    public int getChannelPlace() {
        return this.channelPlace;
    }
    public String getChannelName() {
        return this.channelName;
    }
    public String getChannelSubscribers() {
        return this.channelSubscribers;
    }

    // setter
    public void setChannelPlace(int channelPlace) {
        this.channelPlace = channelPlace;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public void setChannelSubscribers(String channelSubscribers) {
        this.channelSubscribers = channelSubscribers;
    }
}
