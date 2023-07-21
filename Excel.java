package gui_swing_events;

import java.util.ArrayList;

public class Excel {
    private ArrayList<Double> numbers;

    public Excel(ArrayList<Double> numbers) {
        this.numbers = numbers;
    }

    public double findTotal() {
        double total = 0;
        for (double number : numbers) {
            total += number;
        }
        return total;
    }

    public double findAvg() {
        double total = findTotal();
        return total / numbers.size();
    }

    public double findMax() {
        double max = Double.MIN_VALUE;
        for (double number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    public double findMin() {
        double min = Double.MAX_VALUE;
        for (double number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }
}
