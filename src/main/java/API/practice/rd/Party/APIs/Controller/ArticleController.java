package API.practice.rd.Party.APIs.Controller;


import API.practice.rd.Party.APIs.Domain.Doc;
import API.practice.rd.Party.APIs.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@Controller
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/test")
    public String home(Model model) {
        model.addAttribute("articleList", articleService.getMostPopular());


        return "index";
    }

    @GetMapping("/search")
    public String searching(Model model) {
        model.addAttribute("articleList", articleService.getSearchResults("largeHorizontal375"));
        return "search";
    }


    @PostMapping("/search")
    public String postSearching(Model model, @RequestParam String searchText) {
        List<Doc> searchResults = articleService.getSearchResults(searchText);
        model.addAttribute("searchResults", searchResults);
        return "search-results";
    }
}




//    Create two "/search" endpoints in your ArticleController class.
//        A GET endpoint to display the search page.
//        A POST endpoint, that receives search text input and
//        calls the service method for results. It will then add those results to the model
//        and return a template to display them.
//Create the two templates to accompany the endpoints created above.
//        "search.html"
//        "search-results.html"

