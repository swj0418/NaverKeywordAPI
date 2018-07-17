package pro.jeong.naversearch.datapacket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SearchDataPacket {
    LocalTime time = LocalTime.now();
    LocalDate date = LocalDate.now();

    ArrayList<String> keywords = new ArrayList<>();

    public SearchDataPacket(LocalDate date, LocalTime time) {
        this.time = time;
        this.date = date;
    }

    public void setHotKeywordArray(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }
}
