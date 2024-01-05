package kitopi.agnieszka.tkaczyk;

import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);
    final char currency = '$';
    int rows;
    int seats;
    char[][] seatsMatrix;
    int purchasedTickets;
    int currentIncome;

    public Cinema(int rows, int seats){
        this.rows = rows;
        this.seats = seats;
        this.seatsMatrix = new char[rows][seats];
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.seats; j++){
                this.seatsMatrix[i][j] ='S';
            }
        }
        this.purchasedTickets = 0;
        this.currentIncome = 0;
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int dim1 = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int dim2 = scanner.nextInt();
        Cinema cinema = new Cinema(dim1, dim2);

        int input = -1;
        while(input != 0){
            cinema.displayMenu();
            input = scanner.nextInt();

            switch(input){
                case 0:
                    break;
                case 1:
                    System.out.println();
                    cinema.displayArrangement();
                    break;
                case 2:
                    System.out.println();
                    cinema.selectSeat();
                    break;
                case 3:
                    System.out.println();
                    cinema.showStatistics();
                    break;
                default:
                    cinema.displayMenu();
            }
        }
        //cinema.calculateProfit();
    }

    public void displayArrangement(){
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i=1; i<=this.seats; i++){
            System.out.printf("%d ", i);
        }
        System.out.println();

        for(int i=0; i<this.rows; i++){
            System.out.printf("%d ", i+1);
            for(int j=0; j< this.seats; j++){
                System.out.printf("%c ", this.seatsMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public int calculateProfit(){
        if(this.rows * this.seats <= 60){
            return 10 * this.rows * this.seats;
        }
        else{
            int halfRows = this.rows / 2;
            return halfRows * this.seats * 10 + (this.rows - halfRows) * this.seats * 8;
        }
    }

    public void selectSeat(){
        boolean correctInput = false;

        while(!correctInput) {
            System.out.println();
            System.out.println("Enter a row number:");
            int selectedRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int selectedSeat = scanner.nextInt();

            if (selectedRow < 0 || selectedSeat < 0 || selectedRow > this.rows || selectedSeat > this.seats){
                System.out.print("\nWrong input!\n");
            }
            else if(this.seatsMatrix[selectedRow - 1][selectedSeat - 1] == 'B') {
                System.out.print("\nThat ticket has already been purchased!\n");
            }
            else {
                correctInput = true;
                this.seatsMatrix[selectedRow - 1][selectedSeat - 1] = 'B';

                int ticketPrice;
                if (this.rows * this.seats <= 60) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = selectedRow <= this.rows / 2 ? 10 : 8;
                }

                this.currentIncome += ticketPrice;
                this.purchasedTickets += 1;
                System.out.printf("\nTicket price: %c%d\n", this.currency, ticketPrice);
            }
        }
    }

    public void showStatistics(){
        float pctPurchasedTickets = (float) this.purchasedTickets / (this.rows * this.seats) * 100;
        int totalIncome = this.calculateProfit();

        System.out.printf("Number of purchased tickets: %d\n", this.purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", pctPurchasedTickets);
        System.out.printf("Current income: %c%d\n", this.currency, this.currentIncome);
        System.out.printf("Total income: %c%d\n", this.currency, totalIncome);


    }

    public void displayMenu(){
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}