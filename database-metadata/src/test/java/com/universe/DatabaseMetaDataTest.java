package com.universe;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class DatabaseMetaDataTest {

    @Test
    public void test01 () throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/ihrm?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";

        Properties properties = new Properties();
        properties.put("remarksReporting","true");
        properties.put("user",username);
        properties.put("password",password);

        // 注册驱动
        Class.forName(driver);
        // 获取数据库连接
        Connection connection = DriverManager.getConnection(url, properties);
        // 获取元数据
        DatabaseMetaData metaData = connection.getMetaData();

        // ResultSet catalogs = metaData.getCatalogs();
        // while (catalogs.next()) {
        //     System.out.println(catalogs.getString(1)); //获取所有数据库名称
        // }

        // ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        // while (tables.next()) {
        //     System.out.println(tables.getString("TABLE_NAME")); // 获取数据库中所有表
        // }

        // ResultSet bs_user = metaData.getColumns(null, null, "bs_user", null);
        // while (bs_user.next()) {
        //     System.out.println(bs_user.getString("column_name"));  // 获取表中所有字段名
        // }

        // PreparedStatement preparedStatement = connection.prepareStatement("select * from bs_user");
        // ResultSet resultSet = preparedStatement.executeQuery();
        // ResultSetMetaData res = resultSet.getMetaData();
        // int count = res.getColumnCount();  // 获取查询数据的字段数量
        //
        // for (int i = 1; i <= count; i++) {
        //     String columnName = res.getColumnName(i); // 获取字段名
        //     String columnTypeName = res.getColumnTypeName(i); // 获取数据库字段类型
        //     String columnClassName = res.getColumnClassName(i); // 获取字段对应的java数据类型
        //
        //     System.out.println(columnName+"------"+ columnTypeName + "------" + columnClassName);
        //
        // }



        connection.close();
    }
}
