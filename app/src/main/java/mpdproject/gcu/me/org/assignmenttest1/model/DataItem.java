package mpdproject.gcu.me.org.assignmenttest1.model;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by djones201 on 3/27/2018.
 */

public class DataItem implements Parcelable {


    private String title;
    private double coor;
    private String description;
    private String pubDate;
    private String comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCoor() {
        return coor;
    }

    public void setCoor(double coor) {
        this.coor = coor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeDouble(this.coor);
        dest.writeString(this.description);
        dest.writeString(this.pubDate);
        dest.writeString(this.comments);
    }

    public DataItem() {
    }

    protected DataItem(Parcel in) {
        this.title = in.readString();
        this.coor = in.readDouble();
        this.description = in.readString();
        this.pubDate = in.readString();
        this.comments = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}