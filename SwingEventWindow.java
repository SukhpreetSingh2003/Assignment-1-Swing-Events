package gui_swing_events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwingEventWindow {
    private JRadioButton totalRadioButton;
    private JRadioButton averageRadioButton;
    private JRadioButton maxRadioButton;
    private JRadioButton minRadioButton;
    private JTextField inputField;
    private JButton calculateButton;
    private JTextField resultField;

    private int rdoChecked = 1; // Default selected radio button is Total

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Excel Functionality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        inputField = new JTextField(20);
        inputPanel.add(inputField);

        totalRadioButton = new JRadioButton("Total");
        averageRadioButton = new JRadioButton("Average");
        maxRadioButton = new JRadioButton("Maximum");
        minRadioButton = new JRadioButton("Minimum");

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        radioPanel.add(totalRadioButton);
        radioPanel.add(averageRadioButton);
        radioPanel.add(maxRadioButton);
        radioPanel.add(minRadioButton);

        calculateButton = new JButton("Calculate");
        resultField = new JTextField(20);
        resultField.setEditable(false);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                ArrayList<Double> numbers = parseNumbers(inputText);
                Excel excel = new Excel(numbers);

                double result;
                if (rdoChecked == 1) {
                    result = excel.findTotal();
                } else if (rdoChecked == 2) {
                    result = excel.findAvg();
                } else if (rdoChecked == 3) {
                    result = excel.findMax();
                } else {
                    result = excel.findMin();
                }

                resultField.setText(Double.toString(result));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calculateButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resultPanel.add(new JLabel("Result:"));
        resultPanel.add(resultField);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(totalRadioButton);
        buttonGroup.add(averageRadioButton);
        buttonGroup.add(maxRadioButton);
        buttonGroup.add(minRadioButton);

        totalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdoChecked = 1;
            }
        });

        averageRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdoChecked = 2;
            }
        });

        maxRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdoChecked = 3;
            }
        });

        minRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdoChecked = 4;
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(radioPanel, BorderLayout.CENTER);

        // Combine the buttonPanel and resultPanel into a single panel
        JPanel buttonAndResultPanel = new JPanel();
        buttonAndResultPanel.setLayout(new GridLayout(2, 1));
        buttonAndResultPanel.add(buttonPanel);
        buttonAndResultPanel.add(resultPanel);

        mainPanel.add(buttonAndResultPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Implement event handlers here if required

    private ArrayList<Double> parseNumbers(String inputText) {
        ArrayList<Double> numbers = new ArrayList<>();
        String[] inputValues = inputText.split(",");
        for (String value : inputValues) {
            try {
                double number = Double.parseDouble(value.trim());
                numbers.add(number);
            } catch (NumberFormatException e) {
                // Ignore non-numeric values
            }
        }
        return numbers;
    }
}
