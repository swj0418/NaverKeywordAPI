package pro.jeong.naversearch.tracker;

import pro.jeong.naversearch.searches.NaverPlainKeywordSearch;
import pro.jeong.naversearch.searches.NaverSearchTrendSearch;
import pro.jeong.naversearch.Version;
import pro.jeong.naversearch.datapacket.SearchDataPacket;
import pro.jeong.naversearch.datapacket.SearchTrendPacket;

import java.util.ArrayList;

public class NaverSearchTracker {
    NaverPlainKeywordSearch plainKeywordSearch = new NaverPlainKeywordSearch();
    NaverSearchTrendSearch searchTrendSearch = null;

    public NaverSearchTracker() {
        System.out.println("Naver Search Tracker");
        Version.printVersion();
    }

    public SearchDataPacket getPlainHotKeyword() {
        return plainKeywordSearch.searchNow();
    }

    public SearchTrendPacket getSearchTrends(String keyword) {
        searchTrendSearch = new NaverSearchTrendSearch();
        searchTrendSearch.setKeyword(keyword);
        ArrayList<Float> trendData = searchTrendSearch.getTrends();

        SearchTrendPacket trendPacket = new SearchTrendPacket();
        trendPacket.setTrendData(trendData);
        trendPacket.setFrom(searchTrendSearch.getFrom());
        trendPacket.setTo(searchTrendSearch.getTo());

        return trendPacket;
    }
}
