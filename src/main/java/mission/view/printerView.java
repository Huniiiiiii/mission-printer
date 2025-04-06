package mission.view;

import java.util.Scanner;

public class printerView {
    private final Scanner scanner = new Scanner(System.in);

    public void printMenu(){
        System.out.println("프린터를 실행합니다.");
        System.out.print("사용할 기능을 입력해주세요. 1) 출력, 2) 잉크 잔량 확인, 3) 잉크 교체, 4) 프로그램 종료");
    }

    public String input(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public int paperSize(String message){
        System.out.println(message);
        int value = scanner.nextInt();
        scanner.nextLine(); // 버퍼에 남아있는 \n 제거
        return value;
    }

}
