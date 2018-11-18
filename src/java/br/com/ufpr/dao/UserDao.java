/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpr.dao;

import br.com.ufpr.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    
    Connection con = new ConnectionFactory().getConnection();

    public User getAuthenticateString (String name, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT _login FROM Usuario WHERE _login = ? AND _password = ? ");
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString("_login"));
                list.add(u);
            }
            if(list != null)
                return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
