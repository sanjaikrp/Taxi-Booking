import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Book a taxi with customer_id, from, to, time
        // return booked stat or not available
        // Print the Booking_details list
        // Print interval_list
        // exit
        taxi obj1 = new taxi();

        boolean flag = true;
        while(flag){
            System.out.println("Enter a number");
            System.out.println("1. Book a taxi"); //return booked stat or not available
            System.out.println("2. Print booking_details_list");
            System.out.println("3. Print taxi_time_interval_list");
            System.out.println("4. Exit");
            System.out.println("5. salary");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1: System.out.println("Enter customer id"); int customer_id = scanner.nextInt();
                    System.out.println("Enter your start point"); char from = scanner.next().charAt(0);
                    System.out.println("Enter your destination"); char to = scanner.next().charAt(0);
                    System.out.println("Enter your journey start time"); int time = scanner.nextInt();
                    obj1.book(customer_id, from, to, time);
                    break;
                case 2: obj1.print_booking_details();
                    break;
                case 3: obj1.print_taxi_time_interval();
                    break;
                case 4: flag = false;
                        break;
                case 5: obj1.print_taxi_salary();
                        break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
        
    }
}