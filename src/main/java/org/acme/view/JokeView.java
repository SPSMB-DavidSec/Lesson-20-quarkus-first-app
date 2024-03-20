package org.acme.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.acme.dao.CategoryRepository;
import org.acme.dao.JokeRepository;
import org.acme.dao.RatingRepository;
import org.acme.dto.JokeDTO;
import org.acme.model.Category;
import org.acme.model.Joke;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
@RequestScoped
public class JokeView {
    @Inject
    JokeRepository jokeRepository;
    @Inject
    RatingRepository ratingRepository;
    @Inject
    CategoryRepository categoryRepository;

    List<Joke> jokes;
    List<Category> categories;
    JokeDTO newJoke;

    @PostConstruct
    public void init(){
/*        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        String categoryName = params.get("category");
        System.out.println(categoryName);*/

        categories = categoryRepository.listAll();
        System.out.println(categories);

        Optional<Category> category = categoryRepository.listByName("Krátké vtipy");
        if (category.isPresent()){
            jokes = jokeRepository.findByCategoryName("Krátké vtipy");
        }
        else {
            jokes = jokeRepository.listAll();
        }
        newJoke = new JokeDTO();
    }
    public double getAvgRating(Joke joke){
        return ratingRepository.getAvgRatingForJoke(joke);
    }

    @Transactional
    public void saveJoke(){
        Joke joke = new Joke();
        joke.setText(newJoke.getText());
        joke.setAuthor(newJoke.getAuthor());
        Optional<Category> categoryOptional = categoryRepository.listByName(newJoke.getCategory());
        if (categoryOptional.isPresent()){
            joke.setCategory(categoryOptional.get());
        }
        else {
            Category category = new Category();
            category.setName(newJoke.getCategory());
            category.getJokeList().add(joke);
            categoryRepository.persist(category);
            joke.setCategory(category);
        }
        jokeRepository.persist(joke);

        init();
    }


    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public JokeDTO getNewJoke() {
        return newJoke;
    }

    public void setNewJoke(JokeDTO newJoke) {
        this.newJoke = newJoke;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
