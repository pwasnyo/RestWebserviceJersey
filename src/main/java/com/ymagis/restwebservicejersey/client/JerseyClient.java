package com.ymagis.restwebservicejersey.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.ymagis.restwebservicejersey.model.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pwasnyo
 */
public class JerseyClient {

    public static void main(String[] args) {

        try {
            List<Person> persons = new ArrayList<Person>();
            persons.add(new Person("Jean", 20, 1));
            persons.add(new Person("Peter", 30, 2));
            persons.add(new Person("Alves", 20, 3));

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/person-rest/rest/person/add");

            for (Person person : persons) {
                ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, person);
                System.out.println("Le statut est: " + response.getStatus());

                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                System.out.println("************************ Output from Server ************************* \n");
                
                // affiche au format du toString de ma classe MyResponse 
                //System.out.println(response.getEntity(MyResponse.class));
                
                // affiche la reponse de addPerson au format xml comme déclaré dans webResource.type("application/xml")
                System.out.println(response.getEntity(String.class));
                // on ne peut pas afficher les 2 formats (response.getEntity(MyResponse.class) ou String.class) en même temps il faut commenter l'un ou l'autre.
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
