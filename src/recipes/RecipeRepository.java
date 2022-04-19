package recipes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Query("SELECT r from Recipe r WHERE LOWER(r.category) LIKE LOWER(:category) ORDER BY r.date DESC")
    List<Recipe> findRecipesByCategory(@Param("category") String category);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY r.date DESC")
    List<Recipe> findRecipesByNameContains(@Param("name") String name);
}