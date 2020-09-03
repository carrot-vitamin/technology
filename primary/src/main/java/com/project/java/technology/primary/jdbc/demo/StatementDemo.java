package com.project.java.technology.primary.jdbc.demo;

import com.mysql.cj.jdbc.Driver;
import com.project.java.technology.primary.jdbc.utils.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

@Slf4j
public class StatementDemo {

    public static void main(String[] args) throws Exception {
        Connection connection = DBUtil.getConnection(Driver.class);
        Statement statement = connection.createStatement();
        String updateSql = "update user set name='小王' where id=1";
        //增、删、改操作
        statement.executeUpdate(updateSql);

        //查询操作。查询时将查询结果放入结果集，注意结果集存于内存中，数据量过大会引发问题
        String selectSql = "select id,name,birth from user";
        ResultSet resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            //实际上所有的数据都可用getString()方法取出
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date birth = resultSet.getDate("birth");

            //亦可按照列标号依次取出
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            Date birth = resultSet.getDate(3);
            log.info("user info: id:{}, name:{}, birth:{}", id, name, birth);
        }

        //关闭连接
        DBUtil.close(resultSet, statement, connection);
    }
}
