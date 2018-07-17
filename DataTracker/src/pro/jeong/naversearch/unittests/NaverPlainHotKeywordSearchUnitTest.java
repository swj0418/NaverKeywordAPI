package pro.jeong.naversearch.unittests;

import pro.jeong.naversearch.tracker.NaverSearchTracker;
import pro.jeong.naversearch.datapacket.SearchDataPacket;

public class NaverPlainHotKeywordSearchUnitTest {
    public static void main(String[] ar) {
        NaverSearchTracker tracker = new NaverSearchTracker();
        SearchDataPacket dataPacket = tracker.getPlainHotKeyword();
        for(String keyword : dataPacket.getKeywords()) {
            System.out.println(keyword);
        }
    }
}
