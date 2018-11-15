/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpr.webservice;

import br.com.ufpr.bean.Ability;
import br.com.ufpr.bean.Mutant;
import br.com.ufpr.bean.User;
import br.com.ufpr.dao.AbilityDao;
import br.com.ufpr.dao.MutantDao;
import br.com.ufpr.dao.UserDao;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


/**
 * REST Web Service
 */
@Path("app")
public class AppResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AppResource
     */
    public AppResource() {
    }

    @GET
    @Path("/login/{email}/{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public User buscaLogin(@PathParam("email") String email, @PathParam("senha") String senha) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();
        User user = new User();
        user = dao.getAuthenticateString(email, senha);
        return user;
    }
    
    @POST
    @Path("/addMutant")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addMutant(@PathParam("mutant") String mutant) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        Mutant m = new Mutant();
        m.setName(mutant);
        dao.addMutant(m);
    }
    
    @GET
    @Path("/getMutant")
    @Produces(MediaType.APPLICATION_JSON)
    public Mutant getMutant(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.getMutant(name);
    }
    
    @POST
    @Path("/deleteMutant")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteMutant(@PathParam("mutant") Mutant mutant) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        dao.deleteMutant(mutant);
    }
    
    @GET
    @Path("/getAllMutants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mutant> getAllMutants() throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.getAllMutants();
    }
    
    @GET
    @Path("/getMutantsByName")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mutant> getMutantsByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.searchMutantName(name);
    }
    
    @GET
    @Path("/getMutantsByAbility")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mutant> getMutantsByAbility(@PathParam("ability") String ability) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.searchMutantAbility(ability);
    }
    
    @POST
    @Path("/updateMutant")
    @Consumes(MediaType.APPLICATION_JSON)
    public Long updateMutant(@PathParam("mutant") Mutant mutant) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.updateMutant(mutant);
    }
    
    @POST
    @Path("/addMutant")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAbility(@PathParam("ability") Ability ability) throws SQLException, ClassNotFoundException {
        AbilityDao dao = new AbilityDao();
        dao.addAbility(ability);
    }
    
    @POST
    @Path("/deleteAbility")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAbility(@PathParam("ability") Ability ability) throws SQLException, ClassNotFoundException {
        AbilityDao dao = new AbilityDao();
        dao.deleteAbility(ability);
    }
    
    @GET
    @Path("/getAllMutants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ability> getAllAbilitiesByMutant(@PathParam("mutant") Mutant mutant) throws SQLException, ClassNotFoundException {
        AbilityDao dao = new AbilityDao();
        return dao.getAllAbilityOfMutant(mutant);
    }
    
    /**
     * Retrieves representation of an instance of br.com.ufpr.webservice.AppResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AppResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
