package constant;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;

public interface CalendarValue {
    // Once event: value is current timestamp
    long CurrentTimestampValue = System.currentTimeMillis();

    Random rand = new Random();
    long weeklyValue = rand.nextInt(6);
    long monthlyValue = rand.nextInt((30-1)+1)+1;

    // Get current hour, mintute
    int currentHour = LocalDateTime.now().getHour();
    int currentMintute = LocalDateTime.now().getMinute();

    LocalDateTime LaterTime = LocalDateTime.now().plusMinutes(30);
    int endHour = LaterTime.getHour();
    int endMinute = LaterTime.getMinute();
}
