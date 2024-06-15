package com.poldroc.dao.mapper;

import com.poldroc.dao.entity.ReaderDO;
import com.poldroc.utils.JDBCUtil;
import java.util.List;

/**
 * Reader Mapper
 * @author Poldroc
 * @date 2024/6/16
 */

public class ReaderMapper {

    public static ReaderDO getReaderByName(ReaderDO reader) {
        String sql = "SELECT * FROM t_reader WHERE first_name = ? AND last_name = ?";
        List<ReaderDO> readerList = JDBCUtil.executeQuery2Model(ReaderDO.class, sql, reader.getFirstName(), reader.getLastName());
        if (readerList.isEmpty()) {
            return null;
        } else {
            return readerList.get(0);
        }
    }

    /**
     * query limits
     */
    public static int queryLimits(Integer readerId) {
        String sql = "SELECT limits FROM t_reader WHERE reader_id = ? limit 1";
        List<ReaderDO> readerList = JDBCUtil.executeQuery2Model(ReaderDO.class, sql, readerId);
        if (readerList.isEmpty()) {
            return -1;
        } else {
            return readerList.get(0).getLimits();
        }
    }

    /**
     * update limits +=
     */
    public static int updateLimits(Integer readerId, int updateLimits) {

        String sql = "UPDATE t_reader SET limits = limits + ? WHERE reader_id = ?";
        return JDBCUtil.executeUpdate(sql, updateLimits, readerId);
    }

}
