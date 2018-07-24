package org.web3j.tx;

import java.sql.*;

/**
 * Created by tangjc on 2018/7/23.
 */
public class NonceHandle
{
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/blockchain?useSSL=true";
    private static String user = "root";
    private static String password = "sata";
    Connection connection;

    public void conncet() throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);

    }

    public void close() throws SQLException
    {
        if (connection != null)
            connection.close();
    }


    public int getNonce(String address) throws SQLException
    {
        int nonce = 0;
        String sql = "select max(nonce) as nonce from tx_nonce WHERE address=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, address);
        ResultSet ret = stmt.executeQuery();
        while (ret.next())
        {
            nonce = ret.getInt("nonce");
        }
        return nonce;
    }

    public void saveNonce(String address, int nonce) throws SQLException
    {
        String sql = "INSERT into tx_nonce(address, nonce,createTime) VALUE (?,?,now()) ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, address);
        stmt.setInt(2, nonce);
        stmt.executeUpdate();
    }


}
