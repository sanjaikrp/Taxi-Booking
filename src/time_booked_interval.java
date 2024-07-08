import java.util.ArrayList;

public class time_booked_interval {
    int taxi_id;
    ArrayList <book_interval_pair> interval = new ArrayList<>();
    time_booked_interval(int t_id, int b_id, int start_time, int end_time){
        taxi_id = t_id;
        interval.add(new book_interval_pair(b_id, start_time, end_time));
    }
}

// taxi - 1 -> b_id - 1 (s - 0 e - 0) | b_id - 2 (s - 4 e - 6)


