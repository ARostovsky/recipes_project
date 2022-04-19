# Recipes project

An urge to cook something special is too hard to resist sometimes. But what if you lost the recipe? Or your beloved grandma is too busy to answer a call and remind you of your favorite cake recipe? Let's make a program that stores all recipes in one place. Create a multi-user web service with Spring Boot that allows storing, retrieving, updating, and deleting recipes.

* `GET /api/recipe/{id}` returns a recipe with a specified id as a JSON object.


* `POST /api/recipe/new` receives a recipe as a JSON object and returns a JSON object with one id field;


* `DELETE /api/recipe/{id}` endpoint. It deletes a recipe with a specified {id}. 


* `PUT /api/recipe/{id}` receives a recipe as a JSON object and updates a recipe with a specified id. Also, update the date field too. 


* `GET /api/recipe/search` takes one of the two mutually exclusive query parameters:

  * category – if this parameter is specified, it returns a JSON array of all recipes of the specified category. Search is case-insensitive, sort the recipes by date (newer first);
  * name – if this parameter is specified, it returns a JSON array of all recipes with the names that contain the specified parameter. Search is case-insensitive, sort the recipes by date (newer first).