package com.poldroc.view;

import com.alibaba.fastjson2.JSON;
import com.poldroc.common.UserContext;
import com.poldroc.dao.entity.ReaderDO;
import com.poldroc.service.ReaderService;

import javax.swing.*;
import java.awt.*;

import static com.poldroc.utils.JComponentUtil.createButton;
import static com.poldroc.utils.JComponentUtil.createLabel;
/**
 * Reader Main Frame
 * @author Poldroc
 * @date 2024/6/14
 */

public class ReaderMainFrame extends JFrame {

    private JPanel contentPane;
    public static String readerName;
    public static int readerId;

    private JLabel headerLabel;
    private JLabel welcomeLabel;
    private JLabel infoLabel;
    private JLabel readerIdLabel;
    private JLabel readerNameLabel;
    private JLabel readerAddressLabel;
    private JLabel readerPhoneLabel;
    private static JLabel readerLimitsLabel;
    private JButton borrowBooksButton;

    private JButton borrowRecordsButton;

    public ReaderMainFrame() {
        initializeFrame();
        initializeComponents();
        addComponentsToPane();
        initializeReaderInfo();
    }

    private void initializeFrame() {
        setTitle("用户主界面");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 455, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }

    private void initializeComponents() {
        headerLabel = createLabel("  读者操作：", new Font("SansSerif", Font.PLAIN, 16), 6, 6, 88, 23);
        welcomeLabel = createLabel("尊敬的读者：", new Font("SansSerif", Font.PLAIN, 16), 6, 212, 168, 23);
        infoLabel = createLabel("您目前处于读者主界面，点击相应按钮即可进入操作界面。", new Font("SansSerif", Font.PLAIN, 15), 6, 410, 427, 52);
        readerIdLabel = createLabel("", new Font("SansSerif", Font.BOLD, 16), 6, 230, 422, 86);
        readerNameLabel = createLabel("", new Font("SansSerif", Font.BOLD, 16), 6, 260, 422, 86);
        readerAddressLabel = createLabel("", new Font("SansSerif", Font.BOLD, 16), 6, 290, 422, 86);
        readerPhoneLabel = createLabel("", new Font("SansSerif", Font.BOLD, 16), 6, 320, 422, 86);
        readerLimitsLabel = createLabel("", new Font("SansSerif", Font.BOLD, 16), 6, 350, 422, 86);


        borrowBooksButton = createButton("图书借阅", 50, 60, 125, 67, e -> {
            System.out.println("图书借阅");
            new BookBorrowFrame().setVisible(true);
        });
        borrowRecordsButton = createButton("借阅记录", 250, 60, 125, 67, e -> {
            System.out.println("借阅记录");
            new BorrowRecordFrame().setVisible(true);

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
        contentPane.add(readerIdLabel);
        contentPane.add(readerNameLabel);
        contentPane.add(readerAddressLabel);
        contentPane.add(readerPhoneLabel);
        contentPane.add(readerLimitsLabel);
        contentPane.add(borrowBooksButton);
        contentPane.add(borrowRecordsButton);
    }

    private void initializeReaderInfo() {
        ReaderDO readerDO = JSON.parseObject(UserContext.getUser(), ReaderDO.class);
        readerName = readerDO.getLastName() + readerDO.getFirstName();
        readerId = readerDO.getReaderId();
        String address = readerDO.getAddress();
        String phone = readerDO.getPhoneNumber();
        String limit = String.valueOf(readerDO.getLimits());
        readerIdLabel.setText("读者编号：" + readerId);
        readerNameLabel.setText("读者姓名：" + readerName);
        readerAddressLabel.setText("读者地址：" + address);
        readerPhoneLabel.setText("读者电话：" + phone);
        readerLimitsLabel.setText("借阅限制：" + limit);
    }

    /**
     * refresh limits
     */
    public static void refreshLimits() {
        ReaderDO readerDO = JSON.parseObject(UserContext.getUser(), ReaderDO.class);
        int limit = ReaderService.queryLimits(readerDO.getReaderId());
        readerLimitsLabel.setText("借阅限制：" + limit);
        readerDO.setLimits(limit);
        UserContext.remove();
        UserContext.setUser(JSON.toJSONString(readerDO));
    }

}
