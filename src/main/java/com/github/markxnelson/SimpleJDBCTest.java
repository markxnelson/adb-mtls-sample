// Copyright 2022, Oracle and/or its affiliates.

package com.github.markxnelson;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class SimpleJDBCTest {
    // set the database JDBC URL - note that the alias ("myquickstart_high" in this example) and 
    // the location of the wallet must be changed to match your own environment
    private static String url = "jdbc:oracle:thin:@myquickstart_high?TNS_ADMIN=/home/mark/blog";
    
    // the username to connect to the database with
    private static String username = "admin";

    public static void main(String[] args) {

        try {
            // read the password from the DB_PASSWORD environment variable
            String password = System.getenv("DB_PASSWORD");

            // create a data source
            PoolDataSource ds = PoolDataSourceFactory.getPoolDataSource();
            ds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            ds.setURL(url);
            // when using mutual TLS the username and password are not required and you can 
            // comment out or delete the following two lines
            ds.setUser(username);
            ds.setPassword(password);

            // try to connect to the database
            System.out.println("Trying to connect...");
            Connection conn = ds.getConnection();
            if (conn != null) {
                System.out.println("Connected!");
            }

            // clean up
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
