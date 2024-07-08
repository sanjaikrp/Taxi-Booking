public class booking_details {
    int booking_id;
    int customer_id;
    int taxi_id;
    char from;
    char to;
    int start_time;
    int end_time;
    static int id_no = 0;
    booking_details(int c_id, int t_id, char f_from, char f_to, int start, int end) {
        booking_id = id_no++;
        customer_id = c_id;
        taxi_id = t_id;
        from = f_from;
        to = f_to;
        start_time = start;
        end_time = end;
    }
}
