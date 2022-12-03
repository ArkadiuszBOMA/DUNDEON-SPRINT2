package com.codecool.dungeoncrawl.utils;
import javafx.scene.control.Alert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtils {



    public static final String URLDB =  "jdbc:postgresql://pgsql14.server791429.nazwa.pl:5432/";
    public static final String DBADB =  "server791429_DUNGEON";
    public static final String PASDB =  "!!o1hkuzq3axEvzJf7";

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static byte[] get_SHA_256_SecurePassword(String passwordToHash,
                                                     byte[] salt) {
        byte[] generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            generatedPassword = md.digest(passwordToHash.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean signeUpUser(String firstname, String lastname, String email, String age, String password, String repassword, String username) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        boolean signeUp = false;
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        String ePassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{8,10}$";
        Pattern p = Pattern.compile(ePattern);
        Pattern pPassword = Pattern.compile(ePassword);
        Matcher matcher = p.matcher(email);
        Matcher matcherPassword = pPassword.matcher(password);

        if (firstname.length() < 2 || firstname.length() > 15) {
            System.out.println("First name must be min 2 letters max 15");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry your First name must be letters only and min 2 length max 15!");
            alert.show();
        }
        if (lastname.length() < 2 || lastname.length() > 15) {
            System.out.println("Last name must be min 2 letters max 15");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry your Last name must be letters only and min 2 length max 15!");
            alert.show();
        }
        if (!matcher.find()) {
            System.out.println("Your email address is wrong");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry Your email address is wrong!");
            alert.show();
        }
        try {
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            System.out.println("Your age is not a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry Your age is not a number!");
            alert.show();
        }

        if (Integer.parseInt(age)< 13) {
            System.out.println("Please contact your parents");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please contact your parents!");
            alert.show();
        }

        if (lastname.length() < 3) {
            System.out.println("User name minimum 3 characters");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User name minimum 3 characters!");
            alert.show();
        }

        if (!password.equals(repassword)) {
            System.out.println("Your password do not much repeated entry ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry Your password do not much repeated entry!");
            alert.show();

        } else if (password.length()<7 || password.length()>11 ) {
            System.out.println("Your password must be min 8 and max 10 letters long");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your password must be min 8 and max 10 letters long");
            alert.show();

        } else if (!matcherPassword.find()) {
            System.out.println("Your password letter combination is wrong ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your password letter combination is wrong " +
                    "\n pass must contains following character ! @ # $ % " +
                    "\n pass must contains small letter" +
                    "\n pass must contains capital letter" +
                    "\n pass must ba minimum 8 character long " +
                    "\n pass must ba max 10 character long "
            );

            alert.show();
        } else {

            byte[] salt = getSalt();
            byte[] securePassword = get_SHA_256_SecurePassword(password, salt);

            try {
                connection = DriverManager.getConnection(URLDB, DBADB, PASDB);
                psCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                psCheckUserExist.setString(1, username);
                resultSet = psCheckUserExist.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    System.out.println("User already exist");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You can not use this username!");
                    alert.show();
                }
                psInsert = connection.prepareStatement("INSERT INTO users (firstname, lastname, email, age, username, password, salt) VALUES (?,?,?,?,?,?,?)");
                psInsert.setString(1, firstname);
                psInsert.setString(2, lastname);
                psInsert.setString(3, email);
                psInsert.setInt(4, Integer.parseInt(age));
                psInsert.setString(5, username);
                psInsert.setBytes(6, securePassword);
                psInsert.setBytes(7, salt);
                psInsert.execute();
                signeUp = true;

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (psCheckUserExist != null) {
                    try {
                        psCheckUserExist.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (psInsert != null) {
                    try {
                        psInsert.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return signeUp;
    }

    public static boolean logInUser(String username, String password) {
        boolean logIn = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
            try {
                connection = DriverManager.getConnection(URLDB, DBADB, PASDB);
                preparedStatement = connection.prepareStatement("SELECT username, password, salt FROM users WHERE username = ?");
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("User not found in database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Your credentials are incorrect !");
                    alert.show();
                } else {
                    while (resultSet.next()) {
                        byte[] salt = resultSet.getBytes(3);
                        byte[] securePassword = get_SHA_256_SecurePassword(password, salt);
                        String pass_enter = Arrays.toString(securePassword);
                        String pass_db = Arrays.toString(resultSet.getBytes(2));
                        if (pass_enter.equals(pass_db)) {
                            logIn = true;
                        } else {
                            System.out.println("Password don't match!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Your credentials are incorrect ! !");
                            alert.show();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        return logIn;
    }
    }

