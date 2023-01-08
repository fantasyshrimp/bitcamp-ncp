package bitcamp.myapp;

import java.sql.Date;

public class Board {
  int no;
  String title;
  String contents;
  String pwd;
  String createDate;
  String changeDate;
  int viewCount;

  Board(int no, String title, String contents, String pwd) {
    this.no = no;
    this.title = title;
    this.contents = contents;
    this.pwd = pwd;
    this.createDate = new Date(System.currentTimeMillis()).toString();
    this.changeDate = this.createDate;
    this.viewCount = 0;
  }

}
