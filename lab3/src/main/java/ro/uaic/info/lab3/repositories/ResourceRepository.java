package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.Resource;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class ResourceRepository extends Dao<Resource> {
}
