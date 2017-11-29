package com.ymagis.restwebservicejersey.service;

import com.ymagis.restwebservicejersey.model.Person;
import com.ymagis.restwebservicejersey.model.MyResponse;

/**
 *
 * @author pwasnyo
 */
public interface PersonService {

    public MyResponse addPerson(Person p);

    public MyResponse deletePerson(int id);

    public Person getPerson(int id);

    public Person[] getAllPersons();

}
