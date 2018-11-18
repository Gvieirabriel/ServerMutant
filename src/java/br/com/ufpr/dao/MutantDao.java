/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpr.dao;

import br.com.ufpr.bean.Mutant;
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
    
    public Mutant addMutant(Mutant mutant) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO Mutants (_name, _image)"
                    + " VALUES(?,?)");
            st.setString(1, mutant.getName());
            st.setString(2, null);
            st.executeUpdate();
            
            ResultSet rsID = st.getGeneratedKeys();
            if (rsID.next()) {
                mutant.setId(rsID.getInt("_id")); 
            }
            return mutant;
        } catch (SQLException ex) {
            Logger.getLogger(MutantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteMutant(Mutant mutant) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("DELETE FROM Mutants SET WHERE _name = ?");
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
            ps = con.prepareStatement("SELECT _id, _name  FROM Mutants WHERE _name like %?%");
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

    public long updateMutant(Mutant mutant) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE Mutants SET _name = ? WHERE _id = ?");
            st.setString(1, mutant.getName());
            st.executeUpdate();
            
            ResultSet rsID = st.getGeneratedKeys();
            if (rsID.next()) {
                mutant.setId(rsID.getInt("_id")); 
            }
            return mutant.getId();
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
            ps.setString(1, ability);
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
}
