package com.ymagis.restwebservicejersey.service;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.ymagis.restwebservicejersey.model.Person;
import com.ymagis.restwebservicejersey.model.MyResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// je ne met pas /person mais plutôt person parceque dans le web.xml /rest/* donnera ..../rest/person
@Path("person")
@Api(value="person")
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class PersonServiceImpl implements PersonService {

    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

    @Override
    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @ApiOperation(value="add a person in Map")
    public MyResponse addPerson(@ApiParam(value = "Add person to the Map", required = true)Person p) {
        MyResponse response = new MyResponse();
        if (persons.get(p.getId()) != null) {
            response.setStatus(false);
            response.setMessage("Person Already Exists");
            return response;
        }
        persons.put(p.getId(), p);
        response.setStatus(true);
        response.setMessage("Person created successfully");
        return response;
    }

    @Override
    @GET
    @Path("/{id}/delete")
    @ApiOperation(value="delete person with id paramater in Map")
    public MyResponse deletePerson(@ApiParam(value="id",required=true)@PathParam("id") int id) {
        MyResponse response = new MyResponse();
        if (persons.get(id) == null) {
            response.setStatus(false);
            response.setMessage("Person Doesn't Exists");
            return response;
        }
        persons.remove(id);
        response.setStatus(true);
        response.setMessage("Person deleted successfully");
        return response;
    }

    @Override
    @GET
    @Path("/{id}/get")
    @ApiOperation(value="get person with id paramater in Map or a person Name Dummy if there is not person corresponding")
    public Person getPerson(@ApiParam(value="id",required=true)@PathParam("id") int id) {
        if (!persons.containsKey(id)) { // if we d'ont do so http://localhost:8080/person-rest/rest/person/3/get will not display nothing if id= 3 not exist
            Person p = new Person();
            p.setAge(99);
            p.setName("Dummy");
            p.setId(id);
            return p;
        }
        return persons.get(id);
    }

    @GET
    @Path("/{id}/getDummy")
    @ApiOperation(value="get Dummy person ")
    public Person getDummyPerson(@PathParam("id") int id) {
        Person p = new Person();
        p.setAge(99);
        p.setName("Dummy");
        p.setId(id);
        return p;
    }

    @Override
    @GET
    @Path("/getAll")
    @ApiOperation(value="return all persons in Map")
    public Person[] getAllPersons() {
        Set<Integer> ids = persons.keySet();
        Person[] p = new Person[ids.size()];
        int i = 0;
        for (Integer id : ids) {
            p[i] = persons.get(id);
            i++;
        }
        return p;
    }
}
