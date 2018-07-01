package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients=new HashSet<>();
    @Lob
    private Byte[] image;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @ManyToMany
    @JoinTable(name = "map_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories=new HashSet<>();

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
        if (categories == null) {
            this.categories = new HashSet<>();
        }
    }

    public Recipe addCategory(Category category) {
        this.categories.add(category);
        return this;
    }


    public void setNotes(Notes notes) {
        this.notes = notes;
        if (notes != null) {
            notes.setRecipe(this);
        }
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        if (ingredients == null) {
            this.ingredients = new HashSet<>();
        }
    }

    public Recipe addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        if (ingredient != null) {
            ingredient.setRecipe(this);
        }
        return this;
    }
}
