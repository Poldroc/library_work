package com.poldroc.service;

import com.poldroc.common.Contants;
import com.poldroc.dao.entity.RecordDO;
import com.poldroc.dao.mapper.ReaderMapper;
import com.poldroc.dao.mapper.RecordMapper;
import com.poldroc.utils.JDBCUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Poldroc
 * @date 2024/6/14
 */

public class RecordService {

    /**
     * borrow book
     */
    public static boolean borrowBook(Integer readerId, String isbn) {
        JDBCUtil.beginTransaction();
        Date returnDate = new Date(System.currentTimeMillis() + Contants.BORROWING_MILLIS);
        int insertRecord = RecordMapper.insertRecord(readerId, isbn, returnDate);
        int updateLimits = ReaderMapper.updateLimits(readerId, -1);
        if (insertRecord == 1 && updateLimits == 1) {
            JDBCUtil.commit();
            return true;
        } else {
            JDBCUtil.rollback();
            return false;
        }


    }

    /**
     * select records by isbn
     */
    public static RecordDO selectRecordsByISBN(String isbn) {
        return RecordMapper.selectRecordsByISBN(isbn);
    }

    /**
     * select all records by reader_id
     */
    public static List<RecordDO> selectRecordsByReaderId(Integer readerId) {
        return RecordMapper.selectRecordsByReaderId(readerId);
    }

    public static boolean returnBook(Integer recordId,Integer readerId) {
        JDBCUtil.beginTransaction();
        int deleteRecord = RecordMapper.deleteRecord(recordId);
        int updateLimits = ReaderMapper.updateLimits(readerId, 1);
        if (deleteRecord == 1 && updateLimits == 1) {
            JDBCUtil.commit();
            return true;
        } else {
            JDBCUtil.rollback();
            return false;
        }
    }
}
