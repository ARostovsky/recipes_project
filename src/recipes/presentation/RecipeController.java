package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.Recipe;
import recipes.businesslayer.RecipesService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@Validated
public class RecipeController {
    private static final String idKey = "id";
    private final RecipesService recipesService;

    RecipeController(@Autowired RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/api/recipe/{id}")
    Recipe getRecipe(@PathVariable long id) {
        final Recipe recipe = recipesService.findRecipeById(id);
        if (recipe == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return recipe;
    }

    @GetMapping("/api/recipe/search")
    List<Recipe> search(@RequestParam(required = false) @Valid String category,
                        @RequestParam(required = false) @Valid String name) {
        if (category == null && name == null) {
            throw new ResponseStatusException(BAD_REQUEST);
        } else if (category != null) {
            return recipesService.findRecipesByCategory(category);
        } else {
            return recipesService.findRecipesByNameContains(name);
        }
    }

    @PostMapping("/api/recipe/new")
    Map<String, Long> postRecipe(@RequestBody @Valid Recipe recipe) {
        final Recipe saved = recipesService.save(recipe);
        return Map.of(idKey, saved.getId());
    }

    @DeleteMapping("/api/recipe/{id}")
    ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        boolean isDeleted = recipesService.delete(id);
        final HttpStatus status = isDeleted ? NO_CONTENT : NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    @PutMapping("/api/recipe/{id}")
    ResponseEntity<Void> putRecipe(@PathVariable long id,
                                   @RequestBody @Valid Recipe recipe) {
        final Recipe existingRecipe = getRecipe(id);
        existingRecipe.setName(recipe.getName());
        existingRecipe.setCategory(recipe.getCategory());
        existingRecipe.setDescription(recipe.getDescription());
        existingRecipe.setIngredients(recipe.getIngredients());
        existingRecipe.setDirections(recipe.getDirections());
        recipesService.save(recipe);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
