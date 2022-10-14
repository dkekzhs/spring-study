package Controller.Book;

import Controller.Dao.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView create(){
        return new ModelAndView("/views/create");
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createPost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        String bookId = this.bookService.create(map);
        if (bookId == null) {
            mav.setViewName("redirect:/create");
        }else {
            mav.setViewName("redirect:/detail?bookId=" + bookId);
        }

        return mav;
    }

}
