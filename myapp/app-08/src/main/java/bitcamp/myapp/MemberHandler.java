package bitcamp.myapp;

import java.sql.Date;

public class MemberHandler {

  static final int SIZE = 100;
  static int count = 0;
  static Member[] members = new Member[SIZE];

  static void inputMember() {
    members[count++] = new Member(
        Prompt.inputInt("번호? "),
        Prompt.inputString("이름? "),
        Prompt.inputString("전화? "),
        Prompt.inputString("우편번호? "),
        Prompt.inputString("주소1? "),
        Prompt.inputString("주소2? "),
        Prompt.inputInt("0. 미취업\n1. 재직중\n재직자? ") == 1,
        Prompt.inputInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W',
            (byte) Prompt.inputInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? "),
            new Date(System.currentTimeMillis()).toString());
  }

  static void printMembers() {
    System.out.println("번호\t이름\t전화\t재직\t전공");

    for (int i = 0; i < count; i++) {
      members[i].printInfo();
    }
  }

  static void service() {
    while (true) {
      System.out.println("[회원 관리]");
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt("회원관리> ");
  
      if (menuNo == 0) {
        break;
      } else if (menuNo < 0 || menuNo > 5) {
        System.out.println("잘못된 번호입니다.");
      } else {
  
        if (menuNo == 1) {
          inputMember();
        } else if (menuNo == 2) {
          printMembers();
        } else {
          System.out.println("작업 실행");
        }
  
      }
    }
  }
}
