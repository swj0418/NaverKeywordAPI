package pro.jeong.naversearch.searches;

import pro.jeong.naversearch.datapacket.SearchDataPacket;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NaverPlainKeywordSearch {

    public NaverPlainKeywordSearch() {

    }

    public SearchDataPacket searchNow() {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        SearchDataPacket dataPacket = new SearchDataPacket(date, time);
        time = time.minusSeconds(30);

        String queryString = buildPlainQueryString(date, time);
        Document doc = getPlainResponse(queryString);
        ArrayList<String> keywords = getPlainKeywordsCurrent(doc);

        dataPacket.setHotKeywordArray(keywords);

        return dataPacket;
    }

    private String buildPlainQueryString(LocalDate date, LocalTime time) {
        String timeQuery = time.toString().split("\\.")[0];
        String queryString = "https://datalab.naver.com/keyword/realtimeList.naver?datetime=" + date + "T"
                + timeQuery.split(":")[0] + ":" + timeQuery.split(":")[1] + ":" + "00";

        return queryString;
    }

    private Document getPlainResponse(String queryString) {
        URL url = null;
        try {
            url = new URL(queryString);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = Jsoup.parse(url, 1000);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private ArrayList<String> getPlainKeywordsCurrent(Document doc) {
        ArrayList<String> retArray = new ArrayList<>();
        Elements rank_list = doc.getElementsByAttributeValue("class", "keyword_rank select_date");
        Element tmpelement = rank_list.first();
        Elements keywords = tmpelement.getElementsByAttributeValue("class", "title");
        for (Element element : keywords) {
            retArray.add(element.text());
        }
        return retArray;
    }
}
