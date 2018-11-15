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
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
    @Path("/addMutant/{mutant}")
    @Produces(MediaType.TEXT_PLAIN)
    public void addMutant(@PathParam("mutant") String mutant) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        Mutant m = new Mutant();
        m.setName(mutant);
        dao.addMutant(m);
    }
    
    @GET
    @Path("/getMutant/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mutant getMutant(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.getMutant(name);
    }
    
    @POST
    @Path("/deleteMutant/{mutant}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMutant(@PathParam("mutant") Mutant mutant) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        dao.deleteMutant(mutant);
    }
    
    @GET
    @Path("/getAllMutants")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMutants() throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        List<Mutant> mutants = dao.getAllMutants();
        GenericEntity<List<Mutant>> lista = new GenericEntity<List<Mutant>>(mutants) {};
        return Response.status(Response.Status.OK).entity(lista).build(); 
    }
    
    @GET
    @Path("/getMutantsByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMutantsByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        List<Mutant> mutants =  dao.searchMutantName(name);
        GenericEntity<List<Mutant>> lista = new GenericEntity<List<Mutant>>(mutants) {};
        return Response.status(Response.Status.OK).entity(lista).build(); 
    }
    
    @GET
    @Path("/getMutantsByAbility/{ability}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMutantsByAbility(@PathParam("ability") String ability) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        List<Mutant> mutants =  dao.searchMutantAbility(ability);
        GenericEntity<List<Mutant>> lista = new GenericEntity<List<Mutant>>(mutants) {};
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @POST
    @Path("/updateMutant/{mutant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long updateMutant(@PathParam("mutant") Mutant mutant) throws SQLException, ClassNotFoundException {
        MutantDao dao = new MutantDao();
        return dao.updateMutant(mutant);
    }
    
    @POST
    @Path("/addAbility/{ability}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addAbility(@PathParam("ability") Ability ability) throws SQLException, ClassNotFoundException {
        AbilityDao dao = new AbilityDao();
        dao.addAbility(ability);
    }
    
    @POST
    @Path("/deleteAbility/{ability}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAbility(@PathParam("ability") Ability ability) throws SQLException, ClassNotFoundException {
        AbilityDao dao = new AbilityDao();
        dao.deleteAbility(ability);
    }
    
    @GET
    @Path("/getAllAbilities/{mutant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAbilitiesByMutant(@PathParam("mutant") Mutant mutant) throws SQLException, ClassNotFoundException {
        AbilityDao dao = new AbilityDao();
        List<Ability> abilities = dao.getAllAbilityOfMutant(mutant);
        GenericEntity<List<Ability>> lista = new GenericEntity<List<Ability>>(abilities) {};
        return Response.status(Response.Status.OK).entity(lista).build();
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
