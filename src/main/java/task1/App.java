package task1;

import task1.util.Secant;

public class App {
    public static void main(String[] args) {

        if (args.length != 2) {
            throw new RuntimeException("Usage: java App <double> <natural>");
        }

        try {
            double x = Double.parseDouble(args[0]);
            int n = Integer.parseInt(args[1]);
            double result = Secant.run(x, n);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + args[0] + ", use double as argument");
        }
    }
}
