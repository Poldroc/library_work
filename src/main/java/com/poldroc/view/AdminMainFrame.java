package com.poldroc.view;

import com.alibaba.fastjson2.JSON;
import com.poldroc.common.UserContext;
import com.poldroc.dao.entity.AdminDO;

import javax.swing.*;
import java.awt.*;

import static com.poldroc.utils.JComponentUtil.createButton;
import static com.poldroc.utils.JComponentUtil.createLabel;
/**
 * Admin Main Frame
 * @author Poldroc
 * @date 2024/6/14
 */

public class AdminMainFrame extends JFrame {

    private JPanel contentPane;
    public static String adminName;


    private JLabel headerLabel;
    private JLabel welcomeLabel;
    private JLabel infoLabel;
    private JLabel nameLabel;
    private JButton borrowBooksButton;

    private JButton borrowRecordsButton;

    public AdminMainFrame() {
        initializeFrame();
        initializeComponents();
        addComponentsToPane();
        initializeAdminInfo();
    }

    private void initializeFrame() {
        setTitle("管理员主界面");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 455, 417);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }

    private void initializeComponents() {
        headerLabel = createLabel(" 管理员操作：", new Font("SansSerif", Font.PLAIN, 14), 6, 6, 120, 23);
        welcomeLabel = createLabel("尊敬的管理员者：", new Font("SansSerif", Font.PLAIN, 16), 6, 212, 240, 23);
        infoLabel = createLabel("您目前处于管理员主界面，点击相应按钮即可进入操作界面。", new Font("SansSerif", Font.PLAIN, 15), 6, 320, 427, 52);
        nameLabel = createLabel("", new Font("SansSerif", Font.BOLD, 48), 6, 240, 422, 86);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        borrowBooksButton = createButton("读者管理", 50, 60, 125, 67, e -> {
            System.out.println("读者管理");
            JOptionPane.showMessageDialog(null, "未开发", "提示", JOptionPane.INFORMATION_MESSAGE);

        });
        borrowRecordsButton = createButton("图书管理", 250, 60, 125, 67, e -> {
            System.out.println("图书管理");
            JOptionPane.showMessageDialog(null, "未开发", "提示", JOptionPane.INFORMATION_MESSAGE);
        });

        JLabel borderLabel = new JLabel();
        borderLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        borderLabel.setBounds(6, 6, 422, 194);
        contentPane.add(borderLabel);
    }

    private void addComponentsToPane() {
        contentPane.add(headerLabel);
        contentPane.add(welcomeLabel);
        contentPane.add(infoLabel);
        contentPane.add(nameLabel);
        contentPane.add(borrowBooksButton);
        contentPane.add(borrowRecordsButton);
    }

    private void initializeAdminInfo() {
        AdminDO adminDO = JSON.parseObject(UserContext.getUser(), AdminDO.class);
        adminName = adminDO.getUsername();
        nameLabel.setText(adminName);
    }

}
