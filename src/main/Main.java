package main;

import helper.JDBC;

public class Main  {



    public static void main(String[] args) {


        JDBC.openConnection();
        JDBC.closeConnection();
    }
}