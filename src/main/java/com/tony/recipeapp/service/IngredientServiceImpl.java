package com.tony.recipeapp.service;

import com.tony.recipeapp.commands.IngredientCommand;
import com.tony.recipeapp.converters.IngredientCommandToIngredient;
import com.tony.recipeapp.converters.IngredientToIngredientCommand;
import com.tony.recipeapp.domain.Ingredient;
import com.tony.recipeapp.domain.Recipe;
import com.tony.recipeapp.repositories.RecipeRepository;
import com.tony.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            //ToDo: implement error handling
            log.error("recipe id not found. ID: " +recipeId);
        }

        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //ToDo: implement error handling
            log.error("ingredient id not found. ID: " + ingredientId);
        }
        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
       Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
       if(!recipeOptional.isPresent()){
           //todo toss error if not found
           log.error("recipe not found for id: " +command.getRecipeId());
           return new IngredientCommand();
       } else {
           Recipe recipe = recipeOptional.get();

           Optional<Ingredient> ingredientOptional = recipe
                   .getIngredients()
                   .stream()
                   .filter(ingredient -> ingredient.getId().equals(command.getId()))
                   .findFirst();

           if(ingredientOptional.isPresent()){
               Ingredient ingredientFound = ingredientOptional.get();
               ingredientFound.setDescription(command.getDescription());
               ingredientFound.setAmount(command.getAmount());
               ingredientFound.setUom(unitOfMeasureRepository
               .findById(command.getUom().getId())
               .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
           } else{
               //add new ingredient
               Ingredient ingredient = ingredientCommandToIngredient.convert(command);
               ingredient.setRecipe(recipe);
               recipe.addIngredient(ingredient);
           }

           Recipe savedRecipe = recipeRepository.save(recipe);

           Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                   .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                   .findFirst();

           if(!savedIngredientOptional.isPresent()){
               //not totally safe, but best guess
               savedIngredientOptional = savedRecipe.getIngredients().stream()
                       .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                       .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                       .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                       .findFirst();
           }
           //todo: check for fail
           return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
       }
    }
}
