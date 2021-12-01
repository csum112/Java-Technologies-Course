package ro.uaic.info.lab3.repositories;

import ro.uaic.info.lab3.entites.BibliographyItem;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class BibliographyRepository extends Dao<BibliographyItem> {
}
