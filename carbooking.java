import java.util.*;

class RentalCar {
    private String carNumber;
    private boolean isBooked;

    public RentalCar(String carNumber) {
        this.carNumber = carNumber;
        this.isBooked = false;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookCar() {
        isBooked = true;
    }

    public void releaseCar() {
        isBooked = false;
    }
}

class CarPark {
    private List<RentalCar> rentalCars;
    private List<RentalCar> bookedCars;

    public CarPark(int totalCars) {
        rentalCars = new ArrayList<>();
        bookedCars = new ArrayList<>();

        for (int i = 1; i <= totalCars; i++) {
            rentalCars.add(new RentalCar("CAR" + i));
        }
    }

    public void displayAvailableCars() {
        System.out.println("Available Rental Cars:");
        for (RentalCar car : rentalCars) {
            System.out.println(car.getCarNumber());
        }
    }

    public void displayBookedCars() {
        System.out.println("Booked Rental Cars:");
        for (RentalCar car : bookedCars) {
            System.out.println(car.getCarNumber());
        }
    }

    public void bookCar(String carNumber) {
        for (RentalCar car : rentalCars) {
            if (car.getCarNumber().equalsIgnoreCase(carNumber) && !car.isBooked()) {
                car.bookCar();
                bookedCars.add(car);
                rentalCars.remove(car);
                System.out.println("Car " + carNumber + " booked successfully.");
                return;
            }
        }
        System.out.println("Car " + carNumber + " not available for booking.");
    }

    public void releaseCar(String carNumber) {
        for (RentalCar car : bookedCars) {
            if (car.getCarNumber().equalsIgnoreCase(carNumber)) {
                car.releaseCar();
                rentalCars.add(car);
                bookedCars.remove(car);
                System.out.println("Car " + carNumber + " released successfully.");
                return;
            }
        }
        System.out.println("Car " + carNumber + " not found in the booked cars list.");
    }
}

public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of rental cars: ");
        int totalCars = scanner.nextInt();

        CarPark carPark = new CarPark(totalCars);

        int choice;
        do {
            System.out.println("1. Display Available Cars");
            System.out.println("2. Display Booked Cars");
            System.out.println("3. Book a Car");
            System.out.println("4. Release a Car");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    carPark.displayAvailableCars();
                    break;
                case 2:
                    carPark.displayBookedCars();
                    break;
                case 3:
                    System.out.print("Enter the car number to book: ");
                    String carToBook = scanner.next();
                    carPark.bookCar(carToBook);
                    break;
                case 4:
                    System.out.print("Enter the car number to release: ");
                    String carToRelease = scanner.next();
                    carPark.releaseCar(carToRelease);
                    break;
                case 5:
                    System.out.println("Exiting the Car Rental System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
