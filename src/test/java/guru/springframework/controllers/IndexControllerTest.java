package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

public class IndexControllerTest {
    private IndexController indexController;
    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);

    }

    @Test
    public void getIndexPage() {

        Model model = Mockito.mock(Model.class);

        assertEquals("index", indexController.getIndexPage(model));
        verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes());
//        verify(indexController, times(1)).getIndexPage(model);
    }
}