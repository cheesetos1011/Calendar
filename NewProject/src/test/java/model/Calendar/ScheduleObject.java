package model.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ScheduleObject {
    public String type;
    public List<Long> value;
    public boolean is_all_day;
    public int from_date;
    public int start_hour;
    public int start_minute;
    public int end_hour;
    public int end_minute;
    public List<Integer> remind_before;
    public String remind_note;
    public boolean ignore_notify;
    public int duration;
    public String recurrence;

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(List<Long> value) {
        this.value = value;
    }

    public void setIs_all_day(boolean is_all_day) {
        this.is_all_day = is_all_day;
    }

    public void setFrom_date(int from_date) {
        this.from_date = from_date;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public void setStart_minute(int start_minute) {
        this.start_minute = start_minute;
    }

    public void setEnd_hour(int end_hour) {
        this.end_hour = end_hour;
    }

    public void setEnd_minute(int end_minute) {
        this.end_minute = end_minute;
    }

    public void setRemind_before(List<Integer> remind_before) {
        this.remind_before = remind_before;
    }

    public void setRemind_note(String remind_note) {
        this.remind_note = remind_note;
    }

    public void setIgnore_notify(boolean ignore_notify) {
        this.ignore_notify = ignore_notify;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }
}
