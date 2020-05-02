package hw7.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    final int MAX_TEXT_LENGTH = 10;
    private Double result = null;

    private enum ActionType {DIGIT, OPERATION, FINISH};
    private ActionType lastActionType = null;
    private Operation lastOperaion = null;

    private JTextField fullTextField = new JTextField();
    private JTextField textField = new JTextField();
    private JTextField memoField = new JTextField();

    GridBagConstraints constraints = new GridBagConstraints();
    private JButton[] digitButtons = new JButton[10];

    private JButton mcButton = new GreyButton("MC");
    private JButton mrButton = new GreyButton("MR");
    private JButton memoPlusButton = new GreyButton("M+");
    private JButton memoMinusButton = new GreyButton("M-");

    private JButton percentButton = new LigthGrayButton("%");
    private JButton ceButton = new LigthGrayButton("CE");
    private JButton cButton = new LigthGrayButton("C");
    private JButton backButton = new LigthGrayButton("<<");

    private JButton divXButton = new LigthGrayButton("1/x");
    private char squareChar = 178;
    private JButton squareButton = new LigthGrayButton("x" + squareChar);
    private char rootChar = 0x221A;
    private JButton rootButton = new LigthGrayButton(rootChar + "x");

    private JButton plusMinusButton = new LigthGrayButton("+/-");
    private JButton dotButton = new LigthGrayButton(",");

    private JButton divideButton = new LigthGrayButton("/");
    private JButton multiplyButton = new LigthGrayButton("*");
    private JButton minusButton = new LigthGrayButton("-");
    private JButton plusButton = new LigthGrayButton("+");
    private JButton equalsButton = new GreyButton("=");

    private JTextArea history = new JTextArea(17, 20);

    static String convertDouble(Double d) {
        if (d.doubleValue() == d.intValue()) {
            return "" + d.intValue();
        } else {
            return d.toString();
        }
    }

    abstract class Operation implements ActionListener {
        private String actionText;

        Operation (String actionText) {
            this.actionText = actionText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            String fullText = fullTextField.getText();
            try {
                if (lastOperaion != null) {
                    result = lastOperaion.doAction();
                    textField.setText(convertDouble(result));
                } else {
                    result = Double.parseDouble(text);
                    fullText = "";
                }
                fullText += "  " + text;
                fullText += "  " + actionText;
                fullTextField.setText(fullText);

                lastActionType = ActionType.OPERATION;
                lastOperaion = this;

                onActionPerformed();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(Calculator.this,
                        ex.getMessage(),
                        "Ошибка вычисления",
                        JOptionPane.ERROR_MESSAGE);

            }
        }

        void onActionPerformed() {};

        abstract Double doAction();
    };

    class CalcButton extends JButton {
        CalcButton(String text) {
            super(text);
            this.setFont(new Font(null, 0, 30));
        }
    }

    class GreyButton extends JButton {
        GreyButton(String text) {
            super(text);
            this.setFont(new Font(null, Font.BOLD, 30));
            this.setBackground(Color.GRAY);
        }
    }

    class LigthGrayButton extends JButton {
        LigthGrayButton(String text) {
            super(text);
            this.setFont(new Font(null, Font.BOLD, 30));
            this.setBackground(Color.LIGHT_GRAY);
        }
    }

    Calculator() {
        setTitle("Калькулятор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 638, 506);
        setResizable(false);
        setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridy = 0;
        constraints.gridwidth = 4;
        Font f = new Font(null, 0, 20);
        fullTextField.setFont(f);
        fullTextField.setEditable(false);
        fullTextField.setHorizontalAlignment(JTextField.RIGHT);
        add(fullTextField, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 4;
        f = new Font(null, Font.BOLD, 50);
        textField.setFont(f);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setText("0");
        add(textField, constraints);

        constraints.gridy = 2;
        constraints.gridwidth = 1;
        f = new Font(null, 0, 24);
        JLabel memoLabel = new JLabel("M:");
        memoLabel.setHorizontalAlignment(JLabel.RIGHT);
        memoLabel.setFont(f);
        memoLabel.setBackground(Color.LIGHT_GRAY);
        add(memoLabel, constraints);

        constraints.gridwidth = 3;
        memoField.setFont(f);
        memoField.setEditable(false);
        add(memoField, constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 1;

        mcButton.addActionListener(e -> {
            memoField.setText("");
        });
        add(mcButton, constraints);

        mrButton.addActionListener(e -> {
            String memo = memoField.getText();
            if (!memo.equals("")) {
                textField.setText(memo);
            }
            lastActionType = ActionType.OPERATION;
        });
        add(mrButton, constraints);

        memoPlusButton.addActionListener(e -> {
            String memo = memoField.getText();
            if (memo.equals("")) {
                memo = "0";
            }
            Double d = Double.parseDouble(memo) + Double.parseDouble(textField.getText());
            memoField.setText(convertDouble(d));
            lastActionType = ActionType.OPERATION;
        });
        add(memoPlusButton, constraints);

        memoMinusButton.addActionListener(e -> {
            String memo = memoField.getText();
            if (memo.equals("")) {
                memo = "0";
            }
            Double d = Double.parseDouble(memo) - Double.parseDouble(textField.getText());
            memoField.setText(convertDouble(d));
            lastActionType = ActionType.OPERATION;
        });
        add(memoMinusButton, constraints);

        constraints.gridy = 4;
        percentButton.addActionListener(e -> {
            Double d = Double.parseDouble(textField.getText());
            textField.setText(convertDouble(result * d / 100));
            lastActionType = ActionType.OPERATION;
        });
        add(percentButton, constraints);

        ceButton.addActionListener(e -> {
            textField.setText("0");
        });
        add(ceButton, constraints);

        cButton.addActionListener(e -> {
            lastActionType = ActionType.FINISH;
            lastOperaion = null;
            result = null;
            fullTextField.setText("");
            textField.setText("0");
        });
        add(cButton, constraints);

        backButton.addActionListener(e -> {
            if (lastActionType == ActionType.DIGIT) {
                String text = textField.getText();
                if (text.length() == 1) {
                    text = "0";
                } else {
                    text = text.substring(0, text.length() - 1);
                }

                textField.setText(text);
            }
        });
        add(backButton, constraints);

        constraints.gridy = 5;

        divXButton.addActionListener(e -> {
            Double d = Double.parseDouble(textField.getText());
            textField.setText(convertDouble(1/d));
            lastActionType = ActionType.OPERATION;
        });
        add(divXButton, constraints);

        squareButton.addActionListener(e -> {
            Double d = Double.parseDouble(textField.getText());
            textField.setText(convertDouble(d*d));
            lastActionType = ActionType.OPERATION;
        });
        add(squareButton, constraints);

        rootButton.addActionListener(e -> {
            Double d = Double.parseDouble(textField.getText());
            if (d < 0) {
                JOptionPane.showMessageDialog(Calculator.this,
                        "Нельзя извлекать корень из отрицательного числа",
                        "Ошибка вычисления",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                textField.setText(convertDouble(Math.sqrt(d)));
                lastActionType = ActionType.OPERATION;
            }
        });
        add(rootButton, constraints);

        constraints.gridy = 9;
        plusMinusButton.addActionListener(e -> {
            Double d = Double.parseDouble(textField.getText());
            textField.setText(convertDouble(d*-1));
        });
        add(plusMinusButton, constraints);

        for (int i = 0; i < digitButtons.length; i++) {
            constraints.gridy = (9-i)/3 + 6;
            digitButtons[i] = new CalcButton("" + i);
            final int j = i;
            digitButtons[i].addActionListener(e -> {
                String text = textField.getText();
                if (text.equals("0") || lastActionType == ActionType.OPERATION || lastActionType == ActionType.FINISH) {
                    text = "";
                }
                if (text.length() < MAX_TEXT_LENGTH) {
                    text += j;
                }
                textField.setText(text);
                lastActionType = ActionType.DIGIT;
            });
            add(digitButtons[i], constraints);
        }

        constraints.gridy = 5;
        divideButton.addActionListener(new Operation("/") {
            @Override
            Double doAction() {
                if (Double.parseDouble(textField.getText()) == 0) {
                    throw new IllegalArgumentException("Делить на ноль нельзя!");
                } else {
                    return result / Double.parseDouble(textField.getText());
                }
            }
        });
        add(divideButton, constraints);

        constraints.gridy = 6;
        multiplyButton.addActionListener(new Operation("*") {
            @Override
            Double doAction() {
                return result * Double.parseDouble(textField.getText());
            }
        });
        add(multiplyButton, constraints);

        constraints.gridy = 7;
        minusButton.addActionListener(new Operation("-") {
            @Override
            Double doAction() {
                return result - Double.parseDouble(textField.getText());
            }
        });
        add(minusButton, constraints);

        constraints.gridy = 8;
        plusButton.addActionListener(new Operation("+") {
            @Override
            Double doAction() {
                return result + Double.parseDouble(textField.getText());
            }
        });
        add(plusButton, constraints);

        constraints.gridy = 9;
        dotButton.addActionListener(e -> {
            String text = textField.getText();
            if (lastActionType != ActionType.FINISH && text.indexOf(".") == -1) {
                text += ".";
                textField.setText(text);
            }
        });
        add(dotButton, constraints);

        Operation equalsOperation = new Operation("=") {
            @Override
            Double doAction() {
                return result;
            }

            void onActionPerformed() {
                lastOperaion = null;
                lastActionType = ActionType.FINISH;
                history.append(fullTextField.getText() + "  " + convertDouble(result) + "\n");
            }
        };
        equalsButton.addActionListener(equalsOperation);
        add(equalsButton, constraints);

        constraints.gridy = 0;
        constraints.gridheight = 9;
        history.setFont(new Font(null, 0, 19));
        history.setEditable(false);
        add(new JScrollPane(history), constraints);

        constraints.gridy = 9;
        JButton clearHistory = new CalcButton("Очистить историю");
        clearHistory.addActionListener(e -> {
            history.setText("");
        });
        add(clearHistory, constraints);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
