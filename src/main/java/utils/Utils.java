package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Utils {
    public static void waitForSeconds(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Dotenv dotEnv(){
        return Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

}
