package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class MemberController {

  MemberDao memberDao = new MemberDao();

  public MemberController() {

  }

  @GetMapping("/members")
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", members);

    return contentMap;
  }

  @PostMapping("/members")
  public Object addMember(Member m) {

    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.memberDao.insert(m);

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/members/{memberNo}")
  public Object getMember(@PathVariable int memberNo) {

    Map<String, Object> contentMap = new HashMap<>();
    Member m = this.memberDao.findByNo(memberNo);
    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 회원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", m);
    }
    return contentMap;
  }

  @PutMapping("/members/{no}")
  public Object updateMember(Member m) {

    Map<String, Object> contentMap = new HashMap<>();
    Member old = this.memberDao.findByNo(m.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "등록된 회원을 찾을 수 없습니다.");
      return contentMap;
    }

    m.setCreatedDate(old.getCreatedDate());
    this.memberDao.update(m);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/members/{memberNo}")
  public Object deleteBoard(
      @PathVariable int memberNo) {

    Map<String, Object> contentMap = new HashMap<>();
    Member b = this.memberDao.findByNo(memberNo);
    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "등록된 회원을 찾을 수 없습니다.");
    } else {

      this.memberDao.delete(b);
      contentMap.put("status", "success");
    }
    return contentMap;
  }
}
