package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipesService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipesService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Nullable
    public Recipe findRecipeById(Long id) {
        return recipeRepository
                .findById(id)
                .orElse(null);
    }

    public List<Recipe> findRecipesByCategory(String category) {
        return recipeRepository.findRecipesByCategory(category);
    }

    public List<Recipe> findRecipesByNameContains(String name) {
        return recipeRepository.findRecipesByNameContains(name);
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    /**
     * @return true if entity was deleted, otherwise - false
     */
    public boolean delete(long id) {
        if (!recipeRepository.existsById(id)) {
            return false;
        }
        recipeRepository.deleteById(id);
        return true;
    }
}