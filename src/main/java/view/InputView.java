package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int inputInt() {
        return scanner.nextInt();
    }

    public String inputString() {
        return scanner.nextLine();
    }
}
