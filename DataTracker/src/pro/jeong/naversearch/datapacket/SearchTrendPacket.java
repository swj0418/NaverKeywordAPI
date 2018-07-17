package pro.jeong.naversearch.datapacket;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchTrendPacket {
    LocalDate from = null;
    LocalDate to = null;

    ArrayList<Float> trendData = new ArrayList<>();

    public SearchTrendPacket() {

    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public void setTrendData(ArrayList<Float> trendData) {
        this.trendData = trendData;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public ArrayList<Float> getTrendData() {
        return trendData;
    }
}
