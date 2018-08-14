package org.launchcode.springfilterbasedauth.models.dao;

import org.launchcode.springfilterbasedauth.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {

}
