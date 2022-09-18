package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner scanner = new Scanner(System.in);
    private static int numberOfPurchasedTickets = 0;
    private static int income = 0;
    private static int totalIncome = 0;
    private static int totalPlaces = 0;


    private static void printSeats(char[][] Seats) {
        System.out.print("Cinema:\n ");
        for (int i = 0; i < Seats[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < Seats.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < Seats[0].length; j++) {
                System.out.print(" " + Seats[i][j]);
            }
            System.out.println();
        }
    }

    private static void buyATicket(char[][] Seats) {
        while (true) {
            try {
                System.out.println("Enter a row number:");
                int row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int seatInRow = scanner.nextInt();

                if (Seats[row - 1][seatInRow - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    int ticketPrice;
                    if (Seats.length * Seats[0].length > 60) {
                        ticketPrice = row <= Seats.length / 2 ? 10 : 8;
                    } else {
                        ticketPrice = 10;
                    }

                    Seats[row - 1][seatInRow - 1] = 'B';
                    income = income + ticketPrice;
                    numberOfPurchasedTickets++;
                    System.out.println("Ticket price: $" + ticketPrice);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }


    }

    private static void showStatistics(char[][] Seats) {
        System.out.printf("""
                        Number of purchased tickets: %d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d
                        """,
                numberOfPurchasedTickets, (double) numberOfPurchasedTickets / (double) totalPlaces * 100,
                income, totalIncome);
    }

    public static void main(String[] args) {
        //Задаем кол-во мест

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        char[][] Seats = new char[rows][seatsInRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                Seats[i][j] = 'S';
            }
        }
        totalPlaces = rows * seatsInRow;
        if (totalPlaces > 60) {
            totalIncome = rows / 2 * 10 * seatsInRow + (rows - rows / 2) * 8 * seatsInRow;
        } else {
            totalIncome = totalPlaces * 10;
        }

        boolean exit = false;

        while (!exit) {

            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            switch (scanner.nextInt()) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    printSeats(Seats);
                    break;
                case 2:
                    buyATicket(Seats);
                {
                    break;
                }
                case 3:
                    showStatistics(Seats);
                    break;

                default:
                    System.out.println("Your should enter a number");
            }
        }
    }
}