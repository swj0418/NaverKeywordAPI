package pro.jeong.naversearch.unittests;

import pro.jeong.naversearch.tracker.NaverSearchTracker;
import pro.jeong.naversearch.datapacket.SearchTrendPacket;

public class NaverSearchTrendSearchUnitTest {
    public static void main(String[] ar) {
        NaverSearchTracker tracker = new NaverSearchTracker();
        SearchTrendPacket trendPacket = tracker.getSearchTrends("Graduate School");
        for(Float value: trendPacket.getTrendData()) {
            System.out.println(value);
        }
        System.out.println("From : " + trendPacket.getFrom() + "  To : " + trendPacket.getTo());
    }
}
