package org.launchcode.springfilterbasedauth.models.dao;

import org.launchcode.springfilterbasedauth.models.Opportunity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface OpportunityDao extends CrudRepository<Opportunity, Integer> {
}
