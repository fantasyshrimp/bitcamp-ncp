/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitcamp.myapp;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    Scanner keyScanner = new Scanner(System.in);

    System.out.print("번호 : ");
    int no = Integer.parseInt(keyScanner.nextLine());
    System.out.print("이름 : ");
    String name = keyScanner.nextLine();
    System.out.print("전화번호(010-1234-5678) : ");
    String tell = keyScanner.nextLine();
    System.out.print("우편번호 : ");
    String postNo = keyScanner.nextLine();
    System.out.print("주소 : ");
    String basicAddress = keyScanner.nextLine();
    System.out.print("상세주소 : ");
    String detaileAddress = keyScanner.nextLine();
    System.out.print("재직여부 (true / false) : ");
    boolean working = Boolean.parseBoolean(keyScanner.nextLine());
    System.out.print("성별 (M / W) : ");
    char gender = keyScanner.nextLine().charAt(0); // M(남자) W(여자)
    System.out.print("전공여부 (0: 비전공, 1: 준전공, 2: 전공) : ");
    byte level = Byte.parseByte(keyScanner.nextLine()); // 0(비전공)
    System.out.print("가입일(yyyy-mm-dd) : ");
    String createDate = keyScanner.nextLine();

    System.out.print("\n\n\n");

    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("전화번호: %s\n", tell);
    System.out.printf("우편번호: %s\n", postNo);

    System.out.printf("성별: %s\n", gender == 'M' ? "남자" : "여자");
    System.out.printf("주소: %s\n", basicAddress);
    System.out.printf("상세주소: %s\n", detaileAddress);

    System.out.printf("재직여부: %s\n", working ? "예" : "아니오");

    switch (level) {
      case 0:
        System.out.printf("전공: %s\n", "비전공자");
        break;
      case 1:
        System.out.printf("전공: %s\n", "준정공자");
        break;
      case 2:
        System.out.printf("전공: %s\n", "전공자");
        break;
      default:
    }


    System.out.printf("가입일: %s\n", createDate);



  }
}
