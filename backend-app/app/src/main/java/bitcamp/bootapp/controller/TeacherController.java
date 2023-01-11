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
import bitcamp.bootapp.dao.TeacherDao;
import bitcamp.bootapp.vo.Teacher;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class TeacherController {

  TeacherDao teacherDao = new TeacherDao();

  public TeacherController() {

  }

  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", teachers);

    return contentMap;
  }

  @PostMapping("/teachers")
  public Object addTeacher(Teacher t) {

    t.setJoinDate(new Date(System.currentTimeMillis()).toString());
    this.teacherDao.insert(t);

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/teachers/{teacherNo}")
  public Object getTeacher(@PathVariable int teacherNo) {

    Map<String, Object> contentMap = new HashMap<>();
    Teacher t = this.teacherDao.findByNo(teacherNo);
    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 강사가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", t);
    }
    return contentMap;
  }

  @PutMapping("/teachers/{no}")
  public Object updateTeacher(Teacher t) {

    Map<String, Object> contentMap = new HashMap<>();
    Teacher old = this.teacherDao.findByNo(t.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "등록된 강사를 찾을 수 없습니다.");
      return contentMap;
    }

    t.setJoinDate(old.getJoinDate());
    this.teacherDao.update(t);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/teachers/{teacherNo}")
  public Object deleteBoard(
      @PathVariable int teacherNo) {

    Map<String, Object> contentMap = new HashMap<>();
    Teacher t = this.teacherDao.findByNo(teacherNo);
    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "등록된 강사를 찾을 수 없습니다.");
    } else {

      this.teacherDao.delete(t);
      contentMap.put("status", "success");
    }
    return contentMap;
  }
}
