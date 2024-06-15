package com.poldroc.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JComponentUtil {

    public static JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }

    public static  JButton createButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        return button;
    }

    public static JLabel createBorderLabel(int x, int y, int width, int height) {
        JLabel label = new JLabel("");
        label.setBounds(x, y, width, height);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return label;
    }

    public static JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setColumns(10);
        return textField;
    }



}
