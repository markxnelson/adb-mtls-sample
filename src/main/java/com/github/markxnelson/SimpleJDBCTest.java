// Copyright 2022, Oracle and/or its affiliates.

package com.github.markxnelson;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class SimpleJDBCTest {
    private static String url = "jdbc:oracle:thin:@myquickstart_high?TNS_ADMIN=/home/mark/blog";
    private static String username = "admin";

    public static void main(String[] args) {

        try {
            String password = System.getenv("DB_PASSWORD");
            PoolDataSource ds = PoolDataSourceFactory.getPoolDataSource();
            ds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            ds.setURL(url);
            ds.setUser(username);
            ds.setPassword(password);
            System.out.println("Trying to connect...");
            Connection conn = ds.getConnection();
            if (conn != null) {
                System.out.println("Connected!");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
