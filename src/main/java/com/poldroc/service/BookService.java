package com.poldroc.service;

import com.poldroc.dao.entity.BookDO;
import com.poldroc.dao.mapper.BookMapper;

import java.util.List;
/**
 * Book Service
 * @author Poldroc
 * @date 2024/6/14
 */

public class BookService {

    public static List<BookDO> selectBooksByISBN(String isbn) {
        return BookMapper.selectBooksByISBN(isbn);
    }

    public static List<BookDO> selectAllBooks() {
        return BookMapper.selectAll();
    }

}
