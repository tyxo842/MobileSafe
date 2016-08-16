package tyxo.functions.weather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 2016/5/4.
 */
public class DailyForecast {
    @SerializedName("date")
    public String date;
    @SerializedName("tmp")
    public Temperature tmp;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Temperature getTmp() {
        return tmp;
    }

    public void setTmp(Temperature tmp) {
        this.tmp = tmp;
    }
}
