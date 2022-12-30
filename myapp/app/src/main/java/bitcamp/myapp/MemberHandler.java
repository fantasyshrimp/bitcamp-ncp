package bitcamp.myapp;

import java.sql.Date;

public class MemberHandler {

  static final int ACCOUNT_SIZE = 50;
  static int count = 0;

  static int[] nos = new int[ACCOUNT_SIZE];
  static String[] names = new String[ACCOUNT_SIZE];
  static String[] tells = new String[ACCOUNT_SIZE];
  static String[] postNos = new String[ACCOUNT_SIZE];
  static String[] basicAddresses = new String[ACCOUNT_SIZE];
  static String[] detaileAddresses = new String[ACCOUNT_SIZE];

  static boolean[] workings = new boolean[ACCOUNT_SIZE];
  static char[] genders = new char[ACCOUNT_SIZE];
  static byte[] levels = new byte[ACCOUNT_SIZE];
  static String[] createDates = new String[ACCOUNT_SIZE];


  static void inputMembers() {

    for (int i = 0; i < ACCOUNT_SIZE; i++) {
      nos[i] = Prompt.inputInt("번호 : ");
      names[i] = Prompt.inputString("이름 : ");
      tells[i] = Prompt.inputString("전화번호(010-1234-5678) : ");
      postNos[i] = Prompt.inputString("우편번호 : ");
      basicAddresses[i] = Prompt.inputString("주소 : ");
      detaileAddresses[i] = Prompt.inputString("상세주소 : ");
      workings[i] = Prompt.inputInt("재직여부 (0: 미취업 / 1. 재직중) :  : ") == 1;
      genders[i] = Prompt.inputString("성별 (M / W) : ").charAt(0);
      levels[i] = (byte)Prompt.inputInt("전공여부 (0: 비전공, 1: 준전공, 2: 전공) : ");
      createDates[i] = new Date(System.currentTimeMillis()).toString();

      count++;

      String str = Prompt.inputString("계속 입력하시겠습니까? Y / n : ");

      if (!str.equalsIgnoreCase("Y") && str.length() != 0) {
        break;
      }
      System.out.println();
    }
    Prompt.close();
  }

  static void printMembers() {
    for (int i = 0; i < count; i++) {

      System.out.printf("번호: %d\n", nos[i]);
      System.out.printf("이름: %s\n", names[i]);
      System.out.printf("전화번호: %s\n", tells[i]);
      System.out.printf("우편번호: %s\n", postNos[i]);

      System.out.printf("성별: %s\n", genders[i] == 'M' ? "남자" : "여자");
      System.out.printf("주소: %s\n", basicAddresses[i]);
      System.out.printf("상세주소: %s\n", detaileAddresses[i]);

      System.out.printf("재직여부: %s\n", workings[i] ? "미취업" : "재직중");

      switch (levels[i]) {
        case 0:
          System.out.printf("전공: %s\n", "비전공자");
          break;
        case 1:
          System.out.printf("전공: %s\n", "준전공자");
          break;
        case 2:
          System.out.printf("전공: %s\n", "전공자");
          break;
        default:
      }

      System.out.printf("가입일: %s\n", createDates[i]);
      System.out.println("--------------------------------------------");

    }
  }
}
