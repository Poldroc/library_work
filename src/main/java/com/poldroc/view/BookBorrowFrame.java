package com.poldroc.view;

import com.poldroc.common.UserContext;
import com.poldroc.dao.entity.BookDO;
import com.poldroc.dao.entity.RecordDO;
import com.poldroc.service.BookService;
import com.poldroc.service.ReaderService;
import com.poldroc.service.RecordService;
import com.poldroc.utils.JDBCUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import static com.poldroc.utils.JComponentUtil.*;

/**
 * Book Borrow Frame
 * @author Poldroc
 * @date 2024/6/14
 */

public class BookBorrowFrame extends JFrame {

    private JPanel contentPane;

    private JLabel fuzzyQueryLabel;
    private JLabel bookNameLabel;
    private JLabel authorLabel;
    private JLabel publisherLabel;
    private JLabel editionLabel;
    private JLabel categoryLabel;
    private JLabel preciseQueryLabel;
    private JLabel bookIdLabel;
    private JLabel borderLabel1;
    private JLabel borderLabel2;

    private JTextField bookNameTextField;
    private JTextField authorTextField;
    private JTextField publisherTextField;
    private JTextField editionTextField;
    private JTextField categoryTextField;
    private JTextField bookIdTextField;

    private JButton fuzzyQueryButton;
    private JButton preciseQueryButton;

    private JScrollPane scrollPane;
    private JTable bookTable;

    public BookBorrowFrame() {
        initializeFrame();
        initializeComponents();
        addComponentsToPane();
        setDefaultData();
    }

    private void initializeFrame() {
        setResizable(false);
        setTitle("图书查询");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 570);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }


    private void initializeComponents() {
        fuzzyQueryLabel = createLabel("模糊查询：", new Font("SansSerif", Font.BOLD, 18), 18, 16, 93, 26);
        bookNameLabel = createLabel("图 书 名：", new Font("SansSerif", Font.PLAIN, 14), 18, 62, 72, 20);
        authorLabel = createLabel("作   者：", new Font("SansSerif", Font.PLAIN, 14), 18, 105, 70, 20);
        publisherLabel = createLabel("出 版 社：", new Font("SansSerif", Font.PLAIN, 14), 18, 148, 64, 20);
        editionLabel = createLabel("版   次：", new Font("SansSerif", Font.PLAIN, 14), 18, 191, 70, 20);
        categoryLabel = createLabel("类   型：", new Font("SansSerif", Font.PLAIN, 14), 18, 230, 55, 18);
        preciseQueryLabel = createLabel("精确查询：", new Font("SansSerif", Font.BOLD, 18), 18, 337, 93, 26);
        bookIdLabel = createLabel("图书编号：", new Font("SansSerif", Font.PLAIN, 14), 18, 375, 70, 20);
        borderLabel1 = createBorderLabel(6, 6, 310, 310);
        borderLabel2 = createBorderLabel(6, 328, 310, 197);

        bookNameTextField = createTextField(95, 58, 165, 30);
        authorTextField = createTextField(95, 101, 165, 30);
        publisherTextField = createTextField(94, 144, 165, 30);
        editionTextField = createTextField(95, 187, 64, 30);
        categoryTextField = createTextField(95, 230, 165, 30);
        bookIdTextField = createTextField(95, 371, 163, 30);

        fuzzyQueryButton = createButton("查询", 95, 270, 110, 30, e -> fuzzyQuery());
        preciseQueryButton = createButton("查询", 95, 413, 110, 43, e -> preciseQuery());


        scrollPane = new JScrollPane();
        bookTable = new JTable();

    }

    private void addComponentsToPane() {
        // Add Labels
        contentPane.add(fuzzyQueryLabel);
        contentPane.add(bookNameLabel);
        contentPane.add(authorLabel);
        contentPane.add(publisherLabel);
        contentPane.add(editionLabel);
        contentPane.add(categoryLabel);
        contentPane.add(preciseQueryLabel);
        contentPane.add(bookIdLabel);
        contentPane.add(borderLabel1);
        contentPane.add(borderLabel2);

        // Add Text Fields
        contentPane.add(bookNameTextField);
        contentPane.add(authorTextField);
        contentPane.add(publisherTextField);
        contentPane.add(editionTextField);
        contentPane.add(categoryTextField);
        contentPane.add(bookIdTextField);

        // Add Buttons
        contentPane.add(fuzzyQueryButton);
        contentPane.add(preciseQueryButton);

        // Add Table
        contentPane.add(scrollPane);
        scrollPane.setBounds(328, 6, 730, 519);
        String[] columnNames = {"图书ISBN", "图书名", "图书作者", "出版社", "版次", "出版日期", "类型", "操作"};
        bookTable.setFillsViewportHeight(true);
        bookTable.setModel(new DefaultTableModel(new Object[][]{}, columnNames) {
        });
        scrollPane.setViewportView(bookTable);
    }

    private void setDefaultData() {
        fillTable(new ArrayList<>());
    }


    private void fuzzyQuery() {
        // 模糊查询逻辑
        String title = bookNameTextField.getText();
        String authors = authorTextField.getText();
        String publisher = publisherTextField.getText();
        String edition = editionTextField.getText();
        String type = categoryTextField.getText();
        if (title.isEmpty() && authors.isEmpty() && publisher.isEmpty() && edition.isEmpty() && type.isEmpty()) {
            List<BookDO> bookDOS = BookService.selectAllBooks();
            fillTable(bookDOS);
            return;
        }
        // key：字段名，value：字段值
        Map<String, String> map = Map.of(
                "title", title,
                "authors", authors,
                "publisher", publisher,
                "edition_number", edition,
                "type", type
        );
        // 字段值不为空的字段拼接成sql，模糊查询
        String[] columns = map.keySet().stream().filter(k -> !Objects.equals("", map.get(k))).toArray(String[]::new);
        String[] values = map.values().stream().filter(v -> !Objects.equals("", v)).toArray(String[]::new);
        List<BookDO> bookDOS = JDBCUtil.FuzzyQuery(BookDO.class, "t_books", columns, values);
        System.out.println(bookDOS);
        fillTable(bookDOS);

    }

    private void preciseQuery() {
        // 精确查询逻辑
        String isbn = bookIdTextField.getText();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入图书编号！");
            return;
        }
        List<BookDO> bookDOS = BookService.selectBooksByISBN(isbn);
        System.out.println(bookDOS);
        fillTable(bookDOS);

    }

    private void fillTable(List<BookDO> bookDOS) {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
        if (bookDOS.isEmpty()) {
            bookDOS.add(new BookDO());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (BookDO bookDO : bookDOS) {
            model.addRow(new Object[]{
                    bookDO.getIsbn(),
                    bookDO.getTitle(),
                    bookDO.getAuthors(),
                    bookDO.getPublisher(),
                    bookDO.getEditionNumber(),
                    bookDO.getPublicationDate() != null ? dateFormat.format(bookDO.getPublicationDate()) : null,
                    bookDO.getType(),
                    "借阅"
            });
        }
        // 设置自定义渲染器和编辑器 设置借阅按钮
        bookTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        bookTable.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));

    }


    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        private final JButton button;
        private String label;
        private boolean isClicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            // 按钮点击事件处理
            button.addActionListener(e -> {
                fireEditingStopped();

            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isClicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isClicked) {
                System.out.println("Button clicked in row " + bookTable.getSelectedRow());
                borrowBook();
            }
            isClicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isClicked = false;
            return super.stopCellEditing();
        }
    }

    private void borrowBook() {
        String isbn = (String) bookTable.getValueAt(bookTable.getSelectedRow(), 0);
        System.out.println("Button clicked ISBN " + isbn);
        RecordDO recordDO = RecordService.selectRecordsByISBN(isbn);
        if (recordDO != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            JOptionPane.showMessageDialog(null, "该书已被借出，归还时间" + dateFormat.format(recordDO.getReturnDate()));
            return;
        }
        Integer readerId = UserContext.getReaderId();
        if (readerId == null) {
            JOptionPane.showMessageDialog(null, "请先登录！");
            return;
        }
        int limits = ReaderService.queryLimits(readerId);
        if (limits <= 0) {
            JOptionPane.showMessageDialog(null, "已超过您的最大借阅数目！");
            return;
        }
        // 借书
        boolean b = RecordService.borrowBook(readerId, isbn);
        if (b) {
            JOptionPane.showMessageDialog(null, "借书成功！");
            ReaderMainFrame.refreshLimits();
            BorrowRecordFrame.setFreshData();
        } else {
            JOptionPane.showMessageDialog(null, "借书失败！");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookBorrowFrame frame = new BookBorrowFrame();
            frame.setVisible(true);
        });
    }
}

