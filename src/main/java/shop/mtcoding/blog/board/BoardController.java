package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;
    private final HttpSession session;

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String title, String content, String username) {
//        System.out.println("title: " + title);
//        System.out.println("content : " + title);
//        System.out.println("username: " + title);
        boardNativeRepository.update(title, content, username, id);
        return "redirect:/board/" + id;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(String title, String content, String username) {
//        System.out.println("title: " + title);
//        System.out.println("content : " + title);
//        System.out.println("username: " + title);
        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }
}
