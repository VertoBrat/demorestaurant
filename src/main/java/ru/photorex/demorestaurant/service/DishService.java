package ru.photorex.demorestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.to.DishTo;

@Service
@Transactional(readOnly = true)
public class DishService {

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private DishRepo dishRepo;

    public Resources<DishTo> getAll() {
        return null;
    }
}
