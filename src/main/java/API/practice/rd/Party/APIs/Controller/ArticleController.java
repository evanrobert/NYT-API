package API.practice.rd.Party.APIs.Controller;


import API.practice.rd.Party.APIs.Domain.Article;
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
    public String homes(Model model) {
        List<Article> articleList = articleService.getMostPopular();
        System.out.println(articleList.size()); // print size of articleList
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @GetMapping("/tests")
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






