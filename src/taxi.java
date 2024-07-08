import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class taxi {
    // create storage list for booking_details
    ArrayList <booking_details> booking_details_list = new ArrayList<>();

    // create list for intervals
    ArrayList <time_booked_interval> interval_list = new ArrayList<>();

    //create taxi list and their earnings details
    ArrayList <taxi_wage> taxi_details = new ArrayList<>();

// taxi - 1 -> b_id - 1 (s - 0 e - 0) | b_id - 2 (s - 4 e - 6)
// taxi - 2 -> b_id - 4 (s - 2 e - 5) | b_id - 6 (s - 4 e - 6)
// taxi - 3 -> b_id - 5 (s - 0 e - 0) | b_id - 7 (s - 4 e - 6)

    //No of taxis
    int no_of_taxi = 4;

    taxi(){
        booking_details_list.add(new booking_details(0, 0, 'A', 'A', 0, 0));
        for(int i=0;i<no_of_taxi;i++){
            interval_list.add(new time_booked_interval(i+1,0,0,0));
            interval_list.get(i).interval.add(new book_interval_pair(i+1,24,24));
            taxi_details.add(new taxi_wage(i+1));
        }
    }
    // Check availability function
    public boolean check_availability(int start_time, int end_time, HashMap <Integer, Character> taxi_id_and_prev_destination){
        //check end time
        for (int i=0;i<no_of_taxi;i++){
            for(int j=0;j<interval_list.get(i).interval.size() - 1;j++){
                book_interval_pair curr_interval_pair = interval_list.get(i).interval.get(j);
                book_interval_pair next_interval_pair = interval_list.get(i).interval.get(j + 1);

                // Checking if taxi available for that time interval
                if(curr_interval_pair.end_time <= start_time && end_time <= next_interval_pair.start_time ){
                    int prev_ride_id = curr_interval_pair.booking_id;
                    taxi_id_and_prev_destination.put(interval_list.get(i).taxi_id, booking_details_list.get(prev_ride_id).to);
                    break;
                }
            }
        }
        //If available return array of taxi_id + prev booking_id -> to get the destination of their prev journey
        if(taxi_id_and_prev_destination.size() > 0){
            return true;
        }
        return false;
    }
    // Pick a taxi function
    public int pick_a_taxi(char from, HashMap <Integer, Character> taxi_id_and_prev_destination){
        //Getting the closest destination
        int min_dist = Integer.MAX_VALUE;
        for(int key: taxi_id_and_prev_destination.keySet()){
            char taxi_location = taxi_id_and_prev_destination.get(key);
            if(min_dist > Math.abs(from - taxi_location)){
                min_dist = Math.abs(from - taxi_location);
            }
        }
        System.out.println("min_destination"+ min_dist);
        //getting the list of taxi's that are at same closest point
        ArrayList <Integer> taxis_in_min_destination = new ArrayList<>();
        for(int key: taxi_id_and_prev_destination.keySet()){
            char taxi_location = taxi_id_and_prev_destination.get(key);
            if(min_dist == Math.abs(from - taxi_location)){
                taxis_in_min_destination.add(key);
            }
        }
        System.out.println("list of drivers"+ taxis_in_min_destination);
        //checking their wages selecting the one with min wages
        int min_wage = Integer.MAX_VALUE;
        int chosen_taxi = 0;
        for (Integer taxi_id : taxis_in_min_destination) {
            //getting taxi's wage at same destination
            int earning_of_taxi = taxi_details.get(taxi_id - 1).earnings;
            if (min_wage > earning_of_taxi) {
                min_wage = earning_of_taxi;
                chosen_taxi = taxi_id;
            }
        }
        System.out.println("Chosen" + chosen_taxi);
        return chosen_taxi; //returning taxi_id 1
    }
    // Book a taxi
    public void book(int customer_id, char from, char to, int time){
        int end_time = Math.abs(from - to) + time; //Adjacent locations are 15km apart
        HashMap <Integer, Character> taxi_id_and_prev_destination = new HashMap<>();

        // Checking availability returns key : value of tax_id and prev_destination
        boolean avail = check_availability(time, end_time, taxi_id_and_prev_destination);

        for(int key: taxi_id_and_prev_destination.keySet()){
            System.out.println("taxi_id: " + key+ " prev_destination: " + taxi_id_and_prev_destination.get(key));
        }

        if(avail){
            // from the array of taxi_id and prev_booking_id pick a taxi
            int taxi = pick_a_taxi(from, taxi_id_and_prev_destination);

            // add to booking list
            booking_details new_booking = new booking_details(customer_id, taxi, from, to, time, end_time % 24);
            booking_details_list.add(new_booking);

            //add to intervals list
            book_interval_pair new_interval = new book_interval_pair(new_booking.booking_id, new_booking.start_time, new_booking.end_time);
            interval_list.get(taxi - 1).interval.add(new_interval);
            interval_list.get(taxi - 1).interval.sort(Comparator.comparingInt(a -> a.start_time));

            // Add the earning to the taxi driver's account
            int total_km = Math.abs(from - to) * 15;
            int total_amount = 100 + (total_km - 5) * 10;
            taxi_details.get(taxi-1).earnings += total_amount;

            print_booking_details();
            print_taxi_time_interval();
        }
        else{
            System.out.println("No taxis are available at the moment");
        }
    }
    // calc amount function
    // print_booking_details
    public void print_booking_details(){
        for (booking_details bookingDetails : booking_details_list) {
            System.out.println("B_id: " + bookingDetails.booking_id+ " | C_id: " + bookingDetails.customer_id + " | t_id: " +
                    bookingDetails.taxi_id + " | from: " + bookingDetails.from + " to: " + bookingDetails.to + " | start: " +
                    bookingDetails.start_time + " | end: " + bookingDetails.end_time);
        }
    }

    // print_taxi_time_interval
    public void print_taxi_time_interval(){
        for (time_booked_interval timeBookedInterval : interval_list) {
            System.out.print(timeBookedInterval.taxi_id + " ");
            for (int i = 0; i<timeBookedInterval.interval.size(); i++){
                System.out.print(timeBookedInterval.interval.get(i).booking_id + " |" + timeBookedInterval.interval.get(i).start_time + " " +
                        timeBookedInterval.interval.get(i).end_time + "| ");
            }
            System.out.println();
        }
    }

    public void print_taxi_salary(){
        System.out.println("Taxi earnings");
        for (int i=0;i<taxi_details.size();i++){
            System.out.println("taxi_id: "+ taxi_details.get(i).taxi_id+" earnings: "+ taxi_details.get(i).earnings);
        }
    }

}

