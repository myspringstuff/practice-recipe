package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Slf4j
@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootStrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading Spring Bootstrap data...");
        createGuacamole();
        createChickenTaco();
    }

    private UnitOfMeasure findUom(String unit) {
        return unitOfMeasureRepository.findByUnit(unit).get();
    }

    private void createChickenTaco() {
        Recipe recipe = new Recipe();
        recipe.setDescription("Spicy Grilled Chicken Tacos Recipe");

        recipe.setPrepTime(20);
        recipe.setCookTime(15);
        recipe.setServings(4);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setSource("Simple Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe.setNotes(new Notes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!" +
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!"));
        recipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(8), null, "small corn tortillas"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(3), findUom("cup"), "packed baby arugula (3 ounces)"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(2), null, "medium ripe avocados, sliced"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(4), null, "radishes, thinly sliced"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), findUom("pint"), "cherry tomatoes, halved"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(0.25), null, "red onion, thinly sliced"));
        recipe.addIngredient(new Ingredient(BigDecimal.ONE, null, "Roughly chopped cilantro"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), findUom("cup"), "sour cream thinned with 1/4 cup milk"));
        recipe.addIngredient(new Ingredient(BigDecimal.ONE, null, "lime, cut into wedges"));

        Category american = categoryRepository.findByDescription("American").get();
        recipe.addCategory(american);

        recipeRepository.save(recipe);
    }


    private void createGuacamole() {

        Recipe recipe = new Recipe();
        recipe.setDescription("How to Make Perfect Guacamole");
        recipe.setPrepTime(10);
        recipe.setCookTime(0);
        recipe.setServings(2);
        recipe.setSource("Simple Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");


        String directions = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.";
        recipe.setDirections(directions);

        //ingredients
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(2), null, "ripe avocados"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), findUom("teaspoon"), "Kosher salt"));
        recipe.addIngredient(new Ingredient(BigDecimal.ONE, findUom("tablespoon"), "of fresh lime juice or lemon juice"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(2), findUom("tablespoon"), "to 1/4 cup of minced red onion or thinly sliced green onion"));
        recipe.addIngredient(new Ingredient(BigDecimal.ONE, null, "-2 serrano chiles, stems and seeds removed, minced"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(2), findUom("tablespoon"), "cilantro (leaves and tender stems), finely chopped"));
        recipe.addIngredient(new Ingredient(BigDecimal.ONE, null, "dash of freshly grated black pepper"));
        recipe.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), null, "ripe tomato, seeds and pulp removed, chopped"));

        recipe.setDifficulty(Difficulty.MODERATE);

        recipe.setNotes(new Notes("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "EASY PEASY GUACAMOLE\n" +
                "Guacamole is so easy. All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity— will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "\n" +
                "Once you have basic guacamole down, you may experiment with variations including strawberries, pineapple, mangoes, even watermelon. You can get creative with homemade guacamole!\n" +
                "\n" +
                "GUACAMOLE TIP: USE RIPE AVOCADOS\n" +
                "The trick to making perfect guacamole is using good, ripe avocados.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using."
            ));

        Category american = categoryRepository.findByDescription("American").get();
        log.debug("Loaded category: {}", american);
        recipe.getCategories().add(american);

        recipeRepository.save(recipe);
    }
}
