package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public Object addMember(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) int working,
      @RequestParam(required = false) int gender,
      @RequestParam(required = false) int level) {

    Member m = new Member();
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setWorking(working == 1);
    m.setGender(gender == 0 ? 'M' : 'W');
    m.setLevel((byte)level);
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

  @PutMapping("/members/{memberNo}")
  public Object updateMember(
      @PathVariable int memberNo,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) int working,
      @RequestParam(required = false) int gender,
      @RequestParam(required = false) int level) {

    Map<String, Object> contentMap = new HashMap<>();
    Member old = this.memberDao.findByNo(memberNo);
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "등록된 회원을 찾을 수 없습니다.");
      return contentMap;
    }

    Member m = new Member();
    m.setNo(memberNo);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setWorking(working == 1);
    m.setGender(gender == 0 ? 'M' : 'W');
    m.setLevel((byte)level);
    m.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(m);

    contentMap.put("status", "success");

    return contentMap;
  }
}
