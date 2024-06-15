package com.poldroc.utils;

import cn.hutool.core.bean.BeanUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

/**
 * JDBC Util
 *
 * @author Poldroc
 * @date 2024/6/14
 */

public class JDBCUtil {

    private static HikariDataSource dataSource;

    private static ThreadLocal<Connection> threadLocalConnect = new ThreadLocal<>();

    private static Connection connection = threadLocalConnect.get();

    static {
        Properties properties = new Properties();
        try (InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            if (is == null) {
                throw new RuntimeException("jdbc.properties not found");
            }
            properties.load(is);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("jdbcUrl"));
            config.setUsername(properties.getProperty("username"));
            config.setPassword(properties.getProperty("password"));
            config.setConnectionTimeout(Long.parseLong(properties.getProperty("connectionTimeout")));
            config.setIdleTimeout(Long.parseLong(properties.getProperty("idleTimeout")));
            config.setMaxLifetime(Long.parseLong(properties.getProperty("maxLifetime")));
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("maximumPoolSize")));
            config.setAutoCommit(Boolean.parseBoolean(properties.getProperty("autoCommit")));
            dataSource = new HikariDataSource(config);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load jdbc.properties");
        }
    }

    /**
     * get connection from connection pool
     */
    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocalConnect.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get connection");
        }
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close ResultSet");
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close PreparedStatement");
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close Connection");
        }
    }

    /**
     * insert, update, delete
     */
    public static int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            System.out.println(ps.toString());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute update");
        } finally {
           close(null, ps, null);
        }
    }

    /**
     * query
     */
    public static ResultSet executeQuery(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            // 输出SQL语句
            System.out.println(ps.toString());
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query");
        }
    }

    /**
     * query to model
     */
    public static <T> List<T> executeQuery2Model(Class<T> cls, String sql, Object... params) {
        List<T> res = new ArrayList<>();
        ResultSet rs = executeQuery(sql, params);
        try {
            while (rs.next()) {
                T obj = cls.getDeclaredConstructor().newInstance();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    String columnName = underline2Camel(rs.getMetaData().getColumnName(i + 1));
                    BeanUtil.setFieldValue(obj, columnName, rs.getObject(i + 1));
                }
                res.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to map result to model");
        } finally {
            close(rs, null, null);
        }
        return res;
    }


    /**
     * fuzzy query
     */
    public static <T> List<T> FuzzyQuery(Class<T> cls, String tableName, String[] columns, String[] values) {
        StringBuilder sql = new StringBuilder("select * from " + tableName + " where ");
        for (int i = 0; i < columns.length; i++) {
            if (i != 0) {
                sql.append(" and ");
            }
            sql.append(columns[i]).append(" like '%").append(values[i]).append("%'");
        }
        return executeQuery2Model(cls, sql.toString());
    }

    public static void beginTransaction() {
        try {
            System.out.println("begin transaction");
            getConnection().setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to begin transaction");
        }
    }

    public static void commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to commit transaction");
        }
    }

    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to rollback transaction");
        }
    }


    /**
     * to camel case
     */
    public static String underline2Camel(String str) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (c == '_') {
                flag = true;
            } else {
                if (flag) {
                    sb.append(Character.toUpperCase(c));
                    flag = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

}
