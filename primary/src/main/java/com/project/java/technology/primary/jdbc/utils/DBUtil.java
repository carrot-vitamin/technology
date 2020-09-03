package com.project.java.technology.primary.jdbc.utils;

import com.project.java.technology.primary.jdbc.common.Constants;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBUtil {

    private DBUtil() {}

    /**
     * 加载数据库驱动
     * @param clazz
     */
    public static void loadDriver(Class<?> clazz) throws ClassNotFoundException {
        String driverName = clazz.getName();
        Class.forName(driverName);
        log.info("load driver success, {}", driverName);
    }

    /**
     * 获取Connection实例 单例
     * @return
     */
    public static Connection getConnection(Class<?> clazz) throws SQLException, ClassNotFoundException {
        log.info("connection info: className = {}", clazz.getName());
        loadDriver(clazz);
        Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
        log.info("connection success");
        return connection;
    }

    /**
     * 关闭连接
     * @param autoCloseables
     * @throws Exception
     */
    public static void close(AutoCloseable...autoCloseables) throws Exception {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (autoCloseable != null) {
                autoCloseable.close();
            }
        }
        log.info("close io success");
    }
}
