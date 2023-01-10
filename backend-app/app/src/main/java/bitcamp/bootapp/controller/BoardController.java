package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.BoardDao;
import bitcamp.bootapp.vo.Board;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class BoardController {

  BoardDao boardDao = new BoardDao();

  public BoardController() {

  }

  @PostMapping("/boards")
  public Object addBoard(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password) {

    Board b = new Board();
    b.setTitle(title);
    b.setContent(content);
    b.setPassword(password);
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.boardDao.insert(b);

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/boards")
  public Object getBoards() {

    Board[] boards = boardDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", boards);

    return contentMap;
  }


  @GetMapping("/boards/{boardNo}")
  public Object getBoard(@PathVariable int boardNo) {

    Map<String, Object> contentMap = new HashMap<>();
    Board b = this.boardDao.findByNo(boardNo);
    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", b);
    }
    return contentMap;
  }

}
