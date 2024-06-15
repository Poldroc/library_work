package com.poldroc.view;

import com.mysql.cj.util.StringUtils;
import com.poldroc.dao.entity.AdminDO;
import com.poldroc.dao.entity.ReaderDO;
import com.poldroc.service.AdminService;
import com.poldroc.service.ReaderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * login frame
 * @author Poldroc
 * @date 2024/6/14
 */

public class LoginFrame extends JFrame {

    private final JLabel lblHomeLabel = new JLabel("图书管理系统");
    private final JRadioButton readerRadioButton = new JRadioButton("读者登录");
    private final JRadioButton adminRadioButton = new JRadioButton("管理员登录");
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JLabel adminUsernameLabel = new JLabel("用户名：");
    private final JTextField adminUsernameTextField = new JTextField();
    private final JLabel adminPasswordLabel = new JLabel("密 码：");
    private final JPasswordField adminPasswordField = new JPasswordField();
    private final JButton loginButton = new JButton("登录");
    private final JLabel readerFirstNameLabel = new JLabel("名：");
    private final JTextField readerFirstNameTextField = new JTextField("三");
    private final JLabel readerLastNameLabel = new JLabel("姓：");
    private final JTextField readerLastNameTextField = new JTextField("张");
    private int action = 1;


    public LoginFrame() {


        setTitle("登录界面");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 400, 350);
        getContentPane().setLayout(null);

        ((JComponent) this.getContentPane()).setOpaque(false); // 设置透明
        setLocationRelativeTo(null);
        setHomeLabel();

        setReaderRadioButton();

        setAdminRadioButton();

        setAdminLoginFormLabel();

        setLoginButton();


    }

    private void setLoginButton() {
        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (1 == action) {
                    System.out.println("admin login");
                    adminLogin();
                } else if (2 == action) {
                    System.out.println("reader login");
                    readerLogin();
                } else {
                    JOptionPane.showMessageDialog(null, "请选择登录方式！");
                }
            }
        });
        this.loginButton.setBounds(150, 235, 90, 30);
        this.loginButton.setForeground(Color.BLACK);
        getContentPane().add(this.loginButton);
    }

    private void adminLogin() {
        // 判空
        if (StringUtils.isNullOrEmpty(adminUsernameTextField.getText()) || StringUtils.isNullOrEmpty(new String(adminPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
            return;
        }
        AdminDO adminDO = AdminDO.builder()
                .username(adminUsernameTextField.getText())
                .password(new String(adminPasswordField.getPassword()))
                .build();
        System.out.println(adminDO);
        boolean login = AdminService.login(adminDO);
        if (login) {
            JOptionPane.showMessageDialog(null, "登录成功！");
            new AdminMainFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "登录失败！");
        }
    }

    private void readerLogin() {
        // 判空
        if (StringUtils.isNullOrEmpty(readerLastNameTextField.getText()) || StringUtils.isNullOrEmpty(readerFirstNameTextField.getText())) {
            JOptionPane.showMessageDialog(null, "读者名字不能为空！");
            return;
        }
        ReaderDO readerDO = ReaderDO.builder()
                .firstName(readerFirstNameTextField.getText())
                .lastName(readerLastNameTextField.getText())
                .build();
        System.out.println(readerDO);
        boolean login = ReaderService.login(readerDO);
        if (login) {
            JOptionPane.showMessageDialog(null, "登录成功！");
            new ReaderMainFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "登录失败！");
        }
    }

    private void setHomeLabel() {
        this.lblHomeLabel.setForeground(Color.BLACK);
        this.lblHomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 27));
        this.lblHomeLabel.setToolTipText("");
        this.lblHomeLabel.setBounds(110, 6, 216, 37);
        getContentPane().add(this.lblHomeLabel);
    }

    private void setAdminRadioButton() {
        this.adminRadioButton.setForeground(Color.BLACK);
        this.adminRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = 1;
                // 管理员登录时隐藏读者名字输入框
                getContentPane().remove(readerFirstNameLabel);
                getContentPane().remove(readerFirstNameTextField);
                getContentPane().remove(readerLastNameLabel);
                getContentPane().remove(readerLastNameTextField);
                // 添加管理员用户名输入框
                getContentPane().add(adminUsernameLabel);
                getContentPane().add(adminUsernameTextField);
                // 添加管理员密码输入框
                getContentPane().add(adminPasswordLabel);
                getContentPane().add(adminPasswordField);
                // 重新绘制
                getContentPane().repaint();
            }
        });
        this.adminRadioButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.adminRadioButton.setBounds(207, 55, 94, 20);
        // 默认选中管理员登录
        this.adminRadioButton.setSelected(true);
        getContentPane().add(this.adminRadioButton);
        buttonGroup.add(adminRadioButton);
    }

    private void setReaderRadioButton() {
        this.readerRadioButton.setForeground(Color.BLACK);
        setReaderLoginFormLabel();
        this.readerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = 2;
                // 隐藏管理员用户名输入框
                getContentPane().remove(adminUsernameLabel);
                getContentPane().remove(adminUsernameTextField);
                // 隐藏管理员密码输入框
                getContentPane().remove(adminPasswordLabel);
                getContentPane().remove(adminPasswordField);
                // 读者登录时显示读者名字输入框
                getContentPane().add(readerLastNameLabel);
                getContentPane().add(readerLastNameTextField);
                getContentPane().add(readerFirstNameLabel);
                getContentPane().add(readerFirstNameTextField);
                getContentPane().repaint();
            }
        });
        this.readerRadioButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.readerRadioButton.setBounds(81, 55, 80, 20);
        getContentPane().add(this.readerRadioButton);
        buttonGroup.add(readerRadioButton);
    }

    private void setAdminLoginFormLabel() {
        this.adminUsernameLabel.setForeground(Color.BLACK);
        this.adminUsernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        this.adminUsernameLabel.setBounds(18, 112, 83, 29);
        getContentPane().add(this.adminUsernameLabel);
        this.adminPasswordLabel.setForeground(Color.BLACK);
        this.adminPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        this.adminPasswordLabel.setBounds(18, 165, 72, 28);
        getContentPane().add(this.adminPasswordLabel);
        this.adminPasswordField.setColumns(10);
        this.adminPasswordField.setBounds(94, 165, 216, 30);
        getContentPane().add(this.adminPasswordField);
        this.adminUsernameTextField.setBounds(94, 115, 216, 30);
        this.adminUsernameTextField.setColumns(10);
        getContentPane().add(this.adminUsernameTextField);

    }

    private void setReaderLoginFormLabel() {

        this.readerLastNameLabel.setForeground(Color.BLACK);
        this.readerLastNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        this.readerLastNameLabel.setBounds(18, 112, 72, 28);
        this.readerFirstNameLabel.setForeground(Color.BLACK);
        this.readerFirstNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        this.readerFirstNameLabel.setBounds(18, 165, 83, 29);
        this.readerLastNameTextField.setBounds(94, 115, 216, 30);
        this.readerLastNameTextField.setColumns(10);
        this.readerFirstNameTextField.setBounds(94, 165, 216, 30);
        this.readerFirstNameTextField.setColumns(10);

    }
}
