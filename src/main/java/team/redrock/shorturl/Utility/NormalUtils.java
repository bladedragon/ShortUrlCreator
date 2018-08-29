package team.redrock.shorturl.Utility;

public class NormalUtils {
    public static boolean IsNull(String str) {
        return str == null || str.trim().equals("") || str.isEmpty();
    }
}
