package com.poldroc.service;

import com.alibaba.fastjson2.JSON;
import com.poldroc.common.UserContext;
import com.poldroc.dao.entity.ReaderDO;
import com.poldroc.dao.mapper.ReaderMapper;

/**
 * reader service
 * @author Poldroc
 * @date 2024/6/14
 */

public class ReaderService {

    /**
     * login
     */
    public static boolean login(ReaderDO reader) {
        ReaderDO readerDO = ReaderMapper.getReaderByName(reader);
        if (readerDO == null) {
            return false;
        }
        UserContext.setUser(JSON.toJSONString(readerDO));
        UserContext.setReaderId(readerDO.getReaderId());
        return true;
    }

    /**
     * query limits
     */
    public static int queryLimits(Integer readerId) {
        return ReaderMapper.queryLimits(readerId);
    }
}
