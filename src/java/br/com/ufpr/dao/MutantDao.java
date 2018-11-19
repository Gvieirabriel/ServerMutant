/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpr.dao;

import br.com.ufpr.bean.Ability;
import br.com.ufpr.bean.Mutant;
import br.com.ufpr.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MutantDao {
    
    Connection con = new ConnectionFactory().getConnection();
    
    public void addMutant(Mutant mutant) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO Mutants (_name)"
                    + " VALUES(?)");
            st.setString(1, mutant.getName());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MutantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Mutant getMutant(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT _name, _image FROM Mutants WHERE _name = ? ");
            ps.setString(1, name);
            rs = ps.executeQuery();
            List<Mutant> list = new ArrayList<Mutant>();
            while (rs.next()) {
                Mutant m = new Mutant();
                m.setId(0);
                m.setName(rs.getString("_name"));
                list.add(m);
            }
            if(!list.isEmpty())
                return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteMutant(Mutant mutant) {
        AbilityDao dao = new AbilityDao();
        List<Ability> list = dao.getAllAbilityOfMutant(mutant);
        
        for(Ability a : list)
            dao.deleteAbility(a);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("DELETE FROM Mutants WHERE _name = ?");
            ps.setString(1, mutant.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List getAllMutants() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT _id, _name  FROM Mutants");
            rs = ps.executeQuery();
            List<Mutant> list = new ArrayList<Mutant>();
            while (rs.next()) {
                Mutant a = new Mutant();
                a.setId(rs.getInt("_id"));
                a.setName(rs.getString("_name"));
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List searchMutantName(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT _id, _name  FROM Mutants WHERE _name LIKE ?");
            ps.setString(1,"%"+name+"%");
            rs = ps.executeQuery();
            List<Mutant> list = new ArrayList<Mutant>();
            while (rs.next()) {
                Mutant a = new Mutant();
                a.setId(rs.getInt("_id"));
                a.setName(rs.getString("_name"));
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List searchMutantAb(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT m._id, m._name  FROM Mutants AS m JOIN Ability AS a ON a._ability_id = m._id  WHERE a._name LIKE ?");
            ps.setString(1,"%"+name+"%");
            rs = ps.executeQuery();
            List<Mutant> list = new ArrayList<Mutant>();
            while (rs.next()) {
                Mutant a = new Mutant();
                a.setId(rs.getInt("_id"));
                a.setName(rs.getString("_name"));
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public long updateMutant(String lastName, String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE Mutants SET _name = ? WHERE _name = ?");
            st.setString(1, name);
            st.setString(2, lastName);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MutantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Mutant> searchMutantAbility(String ability) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT a._name FROM Mutants AS m JOIN Ability AS a ON a._ability_id = m._id WHERE a._name = ? ");
            ps.setString(1, "%"+ability+"%");
            rs = ps.executeQuery();
            List<Mutant> list = new ArrayList<Mutant>();
            while (rs.next()) {
                Mutant m = new Mutant();
                m.setId(0);
                m.setName(rs.getString("_name"));
                list.add(m);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getlastMutant() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {   
            ps = con.prepareStatement("SELECT _id FROM Mutants ORDER BY _id DESC LIMIT 1");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("_id");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
