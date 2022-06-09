package be.redlab.examples.databasetesting.dao;

import be.redlab.examples.databasetesting.entity.Bar;
import org.springframework.data.repository.CrudRepository;

public interface BarRepository extends CrudRepository<Bar, Long> {
}
