package bitcamp.myapp;

public class Member {
  int no;
  String name;
  String tel;
  String postNo;
  String basicAddress;
  String detailAddress;
  boolean working;
  char gender;
  byte level;
  String createdDate;

  Member(int no, String name, String tel, String postNo, String basicAddress, String detailAddress,
      boolean working, char gender, byte level, String createdDate) {
    this.no = no;
    this.name = name;
    this.tel = tel;
    this.postNo = postNo;
    this.basicAddress = basicAddress;
    this.detailAddress = detailAddress;
    this.working = working;
    this.gender = gender;
    this.level = level;
    this.createdDate = createdDate;
  }

  void printInfo() {

    String levelTitle;
    switch (level) {
      case 0: levelTitle = "비전공자"; break;
      case 1: levelTitle = "준전공자"; break;
      default: levelTitle = "전공자";
    }

    System.out.printf("%d\t%s\t%s\t%s\t%s\n",
        no, name, tel,
        working ? "예" : "아니오",
            levelTitle);

    //    System.out.printf("번호: %d\n", no);
    //    System.out.printf("이름: %s\n", name);
    //    System.out.printf("전화: %s\n", tel);
    //    System.out.printf("우편번호: %s\n", postNo);
    //    System.out.printf("주소1: %s\n", basicAddress);
    //    System.out.printf("주소2: %s\n", detailAddress);
    //    System.out.printf("재직자: %s\n", working ? "예" : "아니오");
    //    System.out.printf("성별: %s\n", gender == 'M' ? "남자" : "여자");
    //
    //    System.out.printf("전공: %s\n", levelTitle);
    //    System.out.printf("가입일: %s\n", createdDate);
  }

}
