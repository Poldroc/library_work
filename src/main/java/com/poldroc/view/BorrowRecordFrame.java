package com.poldroc.view;

import com.poldroc.common.UserContext;
import com.poldroc.dao.entity.RecordDO;

import com.poldroc.service.RecordService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;

import java.util.List;

/**
 * 借阅记录界面
 *
 * @author Poldroc
 * @date 2024/6/14
 */

public class BorrowRecordFrame extends JFrame {

    private JPanel contentPane;
    private JScrollPane scrollPane;
    private static JTable recordTable;

    private static Integer readerId;


    public BorrowRecordFrame() {
        readerId = UserContext.getReaderId();
        if (readerId == null) {
            JOptionPane.showMessageDialog(null, "请先登录", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        initializeFrame();
        initializeComponents();
        addComponentsToPane();
        setFreshData();
    }

    private void initializeFrame() {
        setTitle("借阅记录");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }

    private void initializeComponents() {
        recordTable = new JTable();
        scrollPane = new JScrollPane(recordTable);
        scrollPane.setBounds(0, 0, 800, 600);
    }

    private void addComponentsToPane() {
        add(scrollPane);
        String[] columnNames = {"借阅编号", "ISBN", "借阅时间", "归还时间", "操作"};
        recordTable.setFillsViewportHeight(true);
        recordTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                columnNames
        ));
        scrollPane.setViewportView(recordTable);
    }


    public static void setFreshData() {
        List<RecordDO> recordDOS = RecordService.selectRecordsByReaderId(readerId);
        fillTable(recordDOS);
    }


    private static void fillTable(List<RecordDO> recordDOS) {
        DefaultTableModel model = (DefaultTableModel) recordTable.getModel();
        model.setRowCount(0);
        if (recordDOS.isEmpty()) {
            recordDOS.add(new RecordDO());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (RecordDO recordDO : recordDOS) {
            model.addRow(new Object[]{
                    recordDO.getRecordId(),
                    recordDO.getIsbn(),
                    recordDO.getBorrowingDate() == null ? "" : dateFormat.format(recordDO.getBorrowingDate()),
                    recordDO.getReturnDate() == null ? "" : dateFormat.format(recordDO.getReturnDate()),
                    "归还"

            });
        }
        // 设置自定义渲染器和编辑器 设置借阅按钮
        recordTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        recordTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
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

    private static class ButtonEditor extends DefaultCellEditor {
        private final JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped();
                returnBook();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                System.out.println("click button to return book");
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    private static void returnBook() {
        int recordId = (int) recordTable.getValueAt(recordTable.getSelectedRow(), 0);
        System.out.println("recordId = " + recordId);
        boolean b = RecordService.returnBook(recordId, readerId);
        if (b) {
            JOptionPane.showMessageDialog(null, "归还成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            // 刷新表格
            setFreshData();
            ReaderMainFrame.refreshLimits();
        } else {
            JOptionPane.showMessageDialog(null, "归还失败", "失败", JOptionPane.ERROR_MESSAGE);
        }
    }

}
