package project.Controller;

import project.Dao.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.print.MultiDoc;
import java.util.List;
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
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam Map<String,Object> map){
        Map<String, Object> detail = this.bookService.detail(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detail);
        String bookId = map.get("bookId").toString();
        mav.addObject("bookId", bookId);
        mav.setViewName("/views/detail");

        return mav;

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam Map<String, Object> map) {
        Map<String, Object> detail = this.bookService.detail(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detail);
        mav.setViewName("/views/update");

        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();
        boolean update = this.bookService.update(map);
        if(update){
            String bookId = map.get("bookId").toString();
            mav.setViewName("redirect:/detail?bookId=" + bookId);
        }
        else{
            mav = this.update(map);
        }

        return mav;
    }
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        boolean delete = this.bookService.delete(map);
        if(delete){
            mav.setViewName("redirect:/list");
        }
        else{
            String bookId = map.get("bookId").toString();
            mav.setViewName("redirect:/detail?bookId=" +bookId);
        }
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectList(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();
        List<Map<String, Object>> maps = this.bookService.selectList(map);


        mav.addObject("data", maps);
        if (map.containsKey("keyword")){
            mav.addObject("keyword", map.get("keyword"));
        }

        mav.setViewName("/views/list");
        return mav;
    }


}
