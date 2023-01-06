package bitcamp.myapp;

import java.sql.Date;

public class MemberHandler {

  static final int SIZE = 100;
  static int count = 0;

  // 레퍼런스 배열 준비
  static Member[] members = new Member[SIZE];

  static void inputMember() {
    Member m = new Member();
    m.no = Prompt.inputInt("번호? ");
    m.name = Prompt.inputString("이름? ");
    m.tel = Prompt.inputString("전화? ");
    m.postNo = Prompt.inputString("우편번호? ");
    m.basicAddress = Prompt.inputString("주소1? ");
    m.detailAddress = Prompt.inputString("주소2? ");
    m.working = Prompt.inputInt("0. 미취업\n1. 재직중\n재직자? ") == 1;
    m.gender = Prompt.inputInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W';
    m.level = (byte) Prompt.inputInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? ");
    m.createdDate = new Date(System.currentTimeMillis()).toString();

    // 지금 금방 만든 객체에 사용자가 입력한 값을 저장한 후
    // 그 객체의 주소를 잃어버리지 않게 레퍼런스 배열에 보관해 둔다.
    members[count++] = m;
  }


  static void printMembers() {

    System.out.println("번호\t이름\t전화\t재직\t전공");
    for (int i = 0; i < count; i++) {
      Member m = members[i];

      System.out.printf("%d\t%s\t%s\t%s\t%s\n",
          m.no, m.name, m.tel,
          m.working ? "예" : "아니오",
              getLevelTitle(m.level)
          );
    }
  }

  static void printMember() {
    int memberNo = Prompt.inputInt("회원번호? ");

    Member m = null;

    for (Member member : members) {
      if (member.no == memberNo) {
        m = member;
        break;
      }
    }

    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }
    System.out.printf("    이름: %s\n", m.name);
    System.out.printf("    전화: %s\n", m.tel);
    System.out.printf("우편번호: %s\n", m.postNo);
    System.out.printf("기본주소: %s\n", m.basicAddress);
    System.out.printf("상세주소: %s\n", m.detailAddress);
    System.out.printf("제직여부: %s\n", m.working ? "예" : "아니오");
    System.out.printf("    성별: %s\n", m.gender == 'M' ? "남자" : "여자");
    System.out.printf("    전공: %s\n", getLevelTitle(m.level));
    System.out.printf("  등록일: %s\n", m.createdDate);
  }

  static String getLevelTitle(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }

  static void modifyMember() {
    int memberNo = Prompt.inputInt("회원번호? ");

    Member old = null;
    int memberIndex = 0;
    for (int i = 0; i < members.length; i++) {
      if (members[i].no == memberNo) {
        old = members[i];
        memberIndex = i;
        break;
      }
    }

    if (old == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member m = new Member();
    m.name = Prompt.inputString(String.format("이름( %s )? >> ", old.name));
    m.tel = Prompt.inputString(String.format("전화( %s )? >> ", old.tel));
    m.postNo = Prompt.inputString(String.format("우편번호( %s )? >> ", old.postNo));
    m.basicAddress = Prompt.inputString(String.format("기본주소( %s )? >> ", old.basicAddress));
    m.detailAddress = Prompt.inputString(String.format("상세주소( %s )? >> ", old.detailAddress));
    m.working = Prompt.inputInt(String.format("0. 미취업 1. 재직중\n"
        + "재직여부( %s )? >> ", old.working ? "재직중" : "미취업")) == 1;
    m.gender = Prompt.inputInt(String.format("0. 남자 1. 여자\n"
        + "성별( %s )? >> ", old.gender == 'M' ? "남자" : "여자")) == 0 ? 'M' : 'W';
    m.level = (byte)Prompt.inputInt(String.format("0. 비전공자 1. 준전공자 2. 전공자\n"
        + "전공( %s )? >> ", getLevelTitle(old.level)));


    String doSave = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
    if (doSave.equalsIgnoreCase("Y")) {
      m.no = members[memberIndex].no;
      m.createdDate = members[memberIndex].createdDate;
      members[memberIndex] = m;
    } else {
      System.out.println("수정되지 않았습니다.");
    }
  }


  static void service() {
    int menuNo;
    while(true) {
      System.out.println("[회원 관리]");
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      menuNo = Prompt.inputInt("회원관리> ");

      if (menuNo < 0 || menuNo > 5) {
        System.out.println("잘못된 번호가 입력되었습니다.");
      } else {
        if (menuNo == 1) {
          inputMember();
        } else if (menuNo == 2) {
          printMembers();
        } else if (menuNo == 3) {
          printMember();
        } else if (menuNo == 4) {
          modifyMember();
        }
        else if (menuNo == 0) {
          break;
        } else {
          System.out.println("뭔가 다른 작업 실행");
        }
      }
    }
  }
}
