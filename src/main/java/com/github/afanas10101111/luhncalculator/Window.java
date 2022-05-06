package com.github.afanas10101111.luhncalculator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private final JTextField textField = new JTextField("4000 0000 0000 000");

    public static void main(String[] args) {
        new Window();
    }

    public Window() {
        super("Luhn calculator");
        setSize(240, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(textField);
        JButton doItButton = new JButton("Calculate full card number");
        doItButton.addActionListener(this);
        panel.add(doItButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            textField.setText(Luhn.getFullCardNumber(textField.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Error...", JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
