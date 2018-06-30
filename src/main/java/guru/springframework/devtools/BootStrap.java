package guru.springframework.devtools;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public BootStrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
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
        recipe.setSource("Simple Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
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

        Set<Ingredient> ingredients = new HashSet<>();

        recipe.setIngredients(ingredients);

        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Grill"));
        categoryRepository.saveAll(categories);
        recipe.setCategories(categories);

        recipeRepository.save(recipe);
    }


    private void createGuacamole() {
        Category sauce = new Category();
        sauce.setDescription("Sauce");
        Set<Category> categories = new HashSet<>();
        categories.add(sauce);
        categoryRepository.save(sauce);

        Recipe recipe = new Recipe();
        recipe.setDescription("How to Make Perfect Guacamole");
        recipe.setPrepTime(10);
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
        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient avocado = new Ingredient();
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setDescription("ripe avocados");
//        avocado.setRecipe(recipe);
        ingredients.add(avocado);

        Ingredient salt = new Ingredient(BigDecimal.valueOf(0.5), findUom("teaspoon"), "Kosher salt");
        ingredients.add(salt);

        Ingredient juice = new Ingredient(BigDecimal.ONE, findUom("tablespoon"), "of fresh lime juice or lemon juice");
        ingredients.add(juice);

        Ingredient onion = new Ingredient(BigDecimal.valueOf(2), findUom("tablespoon"), "to 1/4 cup of minced red onion or thinly sliced green onion");
        ingredients.add(onion);

        Ingredient chiles = new Ingredient(BigDecimal.ONE, null, "-2 serrano chiles, stems and seeds removed, minced");
        ingredients.add(chiles);

        Ingredient cilantro = new Ingredient(BigDecimal.valueOf(2), findUom("tablespoon"), "cilantro (leaves and tender stems), finely chopped");
        ingredients.add(cilantro);

        Ingredient pepper = new Ingredient(BigDecimal.ONE, null, "dash of freshly grated black pepper");
        ingredients.add(pepper);

        Ingredient tomato = new Ingredient(BigDecimal.valueOf(0.5), null, "ripe tomato, seeds and pulp removed, chopped");
        ingredients.add(tomato);
        ingredients.forEach(ingredient -> ingredient.setRecipe(recipe));

        recipe.setIngredients(ingredients);

        recipe.setDifficulty(Difficulty.MODERATE);

        Notes notes = new Notes();
        notes.setRecipeNotes("Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n" +
                "\n");
        recipe.setNotes(notes);

        recipe.setCategories(categories);

        recipeRepository.save(recipe);
    }
}
