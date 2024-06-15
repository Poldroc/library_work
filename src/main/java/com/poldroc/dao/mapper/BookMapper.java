package com.poldroc.dao.mapper;

import com.poldroc.dao.entity.BookDO;
import com.poldroc.utils.JDBCUtil;

import java.util.List;
/**
 * Book Mapper
 * @author Poldroc
 * @date 2024/6/16
 */

public class BookMapper {

    /**
     * select book by isbn
     */
    public static List<BookDO> selectBooksByISBN(String isbn) {
        String sql = "SELECT * FROM t_books WHERE isbn = ?";
        return JDBCUtil.executeQuery2Model(BookDO.class, sql, isbn);
    }

    /**
     * select all books
     */
    public static List<BookDO> selectAll() {
        String sql = "SELECT * FROM t_books";
        return JDBCUtil.executeQuery2Model(BookDO.class, sql);
    }
}
