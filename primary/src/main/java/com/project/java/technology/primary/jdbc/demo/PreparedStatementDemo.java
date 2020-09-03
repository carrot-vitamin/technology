package com.project.java.technology.primary.jdbc.demo;

import com.mysql.cj.jdbc.Driver;
import com.project.java.technology.primary.jdbc.utils.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 预处理操作
 */
@Slf4j
public class PreparedStatementDemo {

    public static void main(String[] args) throws Exception {
        Connection connection = DBUtil.getConnection(Driver.class);
        String insertSql = "insert into user(name, birth) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, "小王");
        preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.executeUpdate();

        //查询操作
        String selectSql = "select id, name, birth from user where id=?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(selectSql);
        preparedStatement1.setInt(1, 1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()) {
            //实际上所有的数据都可用getString()方法取出
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Date birth = resultSet.getDate(3);
            log.info("user info: id:{}, name:{}, birth:{}", id, name, birth);
        }
        DBUtil.close(resultSet, preparedStatement1, preparedStatement, connection);
    }
}
