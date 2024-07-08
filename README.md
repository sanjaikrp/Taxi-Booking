# Taxi Booking Application

## Description
This project implements a taxi booking application designed to allocate taxis efficiently based on user requests and current taxi availability at specified points along a straight line of locations.

### Key Features
- **Dynamic Taxi Allocation**: Taxis are dynamically allocated based on availability at the pickup point or the nearest available point.
- **Distance and Time Estimation**: Calculates travel distance and time between points to estimate fares and manage bookings.
- **Fare Calculation**: Charges fares based on the distance traveled, starting with a minimum charge and incremental rates.
- **Time Management**: Accepts absolute time for booking to schedule pickups and drop-offs.

## Design Details
- **Number of Taxis**: The application supports any number of taxis (currently set to 4 for simplicity).
- **Points**: Six points (A, B, C, D, E, F) arranged linearly, each 15 kilometers apart.
- **Travel Time**: Takes 60 minutes to travel between consecutive points.
- **Fare Structure**: Rs. 100 minimum for the first 5 kilometers, Rs. 10 for subsequent kilometers.
- **Initial Taxi Location**: All taxis are initially stationed at point A.
- **Booking Logic**:
  - Allocates a free taxi at the pickup point.
  - If no taxi is available at the pickup point, allocates the nearest available taxi.
  - Prioritizes taxis with lower earnings if multiple taxis are free at the same point.
  - Rejects bookings if no taxi is available at the specified time.

## Implementation
The application is implemented in [language/framework] and structured as follows:
- **Main Components**: Taxi management, booking system, fare calculation.
- **Data Structures**: Arrays/lists for taxis and bookings, maps for managing points and distances.
- **Algorithms**: Distance calculation, time estimation, taxi allocation based on availability and earnings.

## Usage
1. **Setup**:
   - Clone the repository:
     ```bash
     git clone https://github.com/sanjaikrp/Taxi-Booking.git
     cd Taxi-Booking
     ```
   - Install dependencies and configure environment variables as necessary.

2. **Run the Application**:
   - Start the application:
     ```bash
     npm start
     ```
   - Access the application via [method] (e.g., command line interface, web interface).

3. **Booking a Taxi**:
   - Enter pickup point, drop point, and pickup time (absolute time).
   - Receive confirmation or rejection based on taxi availability.

## Contributing
Contributions are welcome. Please follow these steps to contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/yourfeature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/yourfeature`).
5. Create a new Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.