package be.redlab.examples.databasetesting.dao;

import java.util.List;

import be.redlab.examples.databasetesting.entity.Drink;
import org.springframework.data.repository.CrudRepository;

public interface DrinkRepository extends CrudRepository<Drink, Long> {

    List<Drink> findAllByPrice(double price);
}
