package be.redlab.examples.databasetesting.dao;

import java.util.List;

import be.redlab.examples.databasetesting.entity.Bar;
import be.redlab.examples.databasetesting.entity.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
class DrinkRepositoryTest {

    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private BarRepository barRepository;

    @BeforeEach
    void init() {
        Bar b  = new Bar();
        b.setName("The corner");
        b.setOwner("You");
        Bar savedBar = barRepository.save(b);
        Drink drink = new Drink();
        drink.setName("Duvel");
        drink.setPrice(2.0);
        drink.setBar(savedBar);
        drinkRepository.save(drink);
    }

    @Test
    void getDrink() {
        List<Drink> allByPrice = drinkRepository.findAllByPrice(2.0);
        assertAll(
                () -> assertEquals(1, allByPrice.size()),
                () -> assertTrue(allByPrice.stream().allMatch(d -> d.getPrice() == 2.0))
        );
    }
}