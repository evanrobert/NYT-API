package API.practice.rd.Party.APIs.Controller;


import API.practice.rd.Party.APIs.Domain.Article;
import API.practice.rd.Party.APIs.Domain.Doc;
import API.practice.rd.Party.APIs.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("articleList", articleService.getMostPopular());
//
//
//        return "index";
//    }
//    @GetMapping("/search")
//    public String showSearchPage(Model model) {
//        model.addAttribute("articleList", articleService.getMostPopular());
//        return "index";
//    }
//
//    @PostMapping("/search")
//    public String searchArticles(Model model, @RequestParam String searchText) {
//        List<Doc> searchResults = articleService.getSearchResults(searchText);
//        model.addAttribute("searchResults", searchResults);
//        return "search-results";
//    }
@Controller
public class NewsController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articleList", articleService.getMostPopular());
        return "index";
    }

    @GetMapping("/search")
    public String showSearchPage(Model model) {
        List<Article> articleList = articleService.getMostPopular();
        model.addAttribute("articleList", articleList);
        return "search";
    }

    @PostMapping("/search")
    public String searchArticles(Model model, @RequestParam String searchText) {
        List<Doc> searchResults = articleService.getSearchResults(searchText);
        model.addAttribute("searchResults", searchResults);
        return "search-results";
    }

}


}




