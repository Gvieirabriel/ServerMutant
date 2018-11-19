/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpr.dao;

import br.com.ufpr.bean.Ability;
import br.com.ufpr.bean.Mutant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbilityDao {

    Connection con = new ConnectionFactory().getConnection();

    public Ability addAbility(Ability ability) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO Ability (_name, _ability_id)"
                    + " VALUES(?,?)");
            st.setString(1, ability.getName());
            st.setInt(2, ability.getIdMutant());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbilityDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ability;
    }
    
    public void deleteAbility(Ability ability) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("DELETE FROM Ability WHERE _id = ?");
            ps.setInt(1, ability.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List getAllAbilityOfMutant(Mutant mutant) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT a._id, a._name  FROM Ability AS a JOIN Mutants AS m ON a._ability_id = m._id WHERE m._name = ?");
            ps.setString(1, mutant.getName());
            rs = ps.executeQuery();
            List<Ability> list = new ArrayList<Ability>();
            while (rs.next()) {
                Ability a = new Ability();
                a.setId(rs.getInt("_id"));
                a.setName(rs.getString("_name"));
                a.setIdMutant(mutant.getId());
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
