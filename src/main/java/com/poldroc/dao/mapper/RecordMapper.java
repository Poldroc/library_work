package com.poldroc.dao.mapper;

import com.poldroc.dao.entity.RecordDO;
import com.poldroc.utils.JDBCUtil;
import java.util.Date;
import java.util.List;

public class RecordMapper {

    /**
     * select records by isbn
     */
    public static RecordDO selectRecordsByISBN(String isbn) {
        String sql = "SELECT * FROM t_records WHERE isbn = ?";
        List<RecordDO> recordList = JDBCUtil.executeQuery2Model(RecordDO.class, sql, isbn);
        if (recordList.isEmpty()) {
            return null;
        } else {
            return recordList.get(0);
        }
    }

    /**
     * insert
     */
    public static int insertRecord(Integer readerId, String isbn,Date returnDate) {

        String sql = "INSERT INTO t_records (isbn, reader_id, borrowing_date, return_date) VALUES (?, ?, ?, ?)";
        return JDBCUtil.executeUpdate(sql, isbn, readerId, new Date() , /*30天后*/returnDate);
    }

    /**
     * select all records by reader_id
     */
    public static List<RecordDO> selectRecordsByReaderId(Integer readerId) {
        String sql = "SELECT * FROM t_records WHERE reader_id = ?";
        return JDBCUtil.executeQuery2Model(RecordDO.class, sql, readerId);
    }

    /**
     * delete
     */
    public static int deleteRecord(Integer recordId) {
        String sql = "DELETE FROM t_records WHERE record_id = ?";
        return JDBCUtil.executeUpdate(sql, recordId);
    }
}
