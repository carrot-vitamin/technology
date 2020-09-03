package com.project.java.technology.primary.jdbc.demo;

import com.mysql.cj.jdbc.Driver;
import com.project.java.technology.primary.jdbc.utils.DBUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Objects;

public class BigDataDemo {

    private static String txtPath = Objects.requireNonNull(BigDataDemo.class.getClassLoader().getResource("test.txt")).getPath();
    private static String photoPath = Objects.requireNonNull(BigDataDemo.class.getClassLoader().getResource("test.jpg")).getPath();
    private static String destTxtPath = Objects.requireNonNull(BigDataDemo.class.getClassLoader().getResource("dest.txt")).getPath();
    private static String destBinaryPath = Objects.requireNonNull(BigDataDemo.class.getClassLoader().getResource("dest.jpg")).getPath();

    /**
     * 写入海量数据 CLOB  BLOB
     * @throws Exception
     */
    public static void writeBigData() throws Exception {
        Connection connection = DBUtil.getConnection(Driver.class);
        String insertSql = "insert into user (name, birth, note, photo) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        //以流的形式将大文本数据存入数据库
        File txtFile = new File(txtPath);
        File photoFile = new File(photoPath);
        preparedStatement.setString(1, "小王");
        preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
        preparedStatement.setAsciiStream(3, new FileInputStream(txtFile), (int) txtFile.length());
        preparedStatement.setBinaryStream(4, new FileInputStream(photoFile), (int) photoFile.length());
        preparedStatement.executeUpdate();
        DBUtil.close(preparedStatement, connection);
    }

    /**
     * 读取海量数据 CLOB  BLOB
     * @throws Exception
     */
    public static void readBigData() throws Exception {
        Connection connection = DBUtil.getConnection(Driver.class);
        String selectSql = "select note, photo from user where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, "小王");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            //取出数据库存入的流
            InputStream txtStream = resultSet.getAsciiStream("note");
            InputStream binaryStream = resultSet.getBinaryStream("photo");
            //亦可通过以下方式取得
//            Clob clob = resultSet.getClob("note");
//            InputStream inputStream3 = clob.getAsciiStream();

//            Blob blob = resultSet.getBlob("photo");
//            InputStream inputStream4 = blob.getBinaryStream();

            //对取出的流的处理

            OutputStream txtOs = new FileOutputStream(destTxtPath);
            OutputStream binaryOs = new FileOutputStream(destBinaryPath);

            int txtTemp, binaryTemp;
            byte [] bytes = new byte[1024];
            while ((txtTemp = txtStream.read(bytes, 0, bytes.length)) != -1) {
                txtOs.write(bytes, 0, txtTemp);
            }

            while ((binaryTemp = binaryStream.read(bytes, 0, bytes.length)) != -1) {
                binaryOs.write(bytes, 0, binaryTemp);
            }
            DBUtil.close(binaryOs, txtOs, binaryStream, txtStream);
        }

        DBUtil.close(preparedStatement, preparedStatement, connection);
    }

    public static void main(String[] args) throws Exception {
//        writeBigData();
        readBigData();
    }

}
