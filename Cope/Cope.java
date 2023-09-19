package Cope;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;

class Player {
    Player(String a) {
        this.name = a;
    }

    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    String name;
    int bet;
    int money = 10000;
    int sum = 0;
    int burstNum = 21;
    boolean checkSum = true;
    ArrayList<Integer> cardNumber = new ArrayList<>();
    ArrayList<String> cardMark = new ArrayList<>();
    String markList[] = { "하트 ", "크로바 ", "스페이드 ", "다이아 " };

    void getCardFirst() {
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardNumber.add(rand.nextInt(10) + 1);
        /*
         * this.cardNumber.add(10);
         * this.cardNumber.add(11);
         */ // 블랙잭 나오게 하기
        this.cardMark.add(markList[rand.nextInt(4)]);
        this.cardMark.add(markList[rand.nextInt(4)]);
        System.out.print(this.name + "의 카드는 " + this.cardMark.get(0) + this.cardNumber.get(0) + ", "
                + this.cardMark.get(1) + this.cardNumber.get(1)
                + " 입니다. (합 :");
        this.checkSave();
    }

    void getCard() {
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardMark.add(markList[rand.nextInt(4)]);
        System.out.print(this.name + "의 카드는 " + this.cardMark.get(cardMark.size() - 1)
                + this.cardNumber.get(cardNumber.size() - 1) +
                "입니다. (합: ");
        this.checkSave();
    }

    void betting() {
        while (true) {
            System.out.print("배팅 금액을 입력하세요(소지금: " + this.money + "): ");
            try {
                this.bet = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("옳바른 정수값을 입력해 주세요");
                scan.nextLine();
            }

            if (this.bet > this.money) {
                System.out.println("배팅 금액이 소지금 보다 많습니다");
            } else if (this.bet <= 0) {
                System.out.println("베팅 금액이 있어야 합니다");
            } else {
                this.money -= bet;
                break;
            }
        }

    }

    void checkSave() {
        this.sum = 0;
        for (int i = 0; i < this.cardNumber.size(); i++) {
            this.sum += this.cardNumber.get(i);
        }
        if (this.sum > burstNum) {
            System.out.println(sum + ") 버스트!");
            this.sum = 0;
            this.checkSum = false;
        } else if (this.sum == burstNum) {
            System.out.println(" 21) 블랙잭!");
            this.sum = 0;
            this.checkSum = false;
        } else {
            System.out.println(sum + ")");
            this.sum = 0;
        }
    }

}

class Npc extends Player {
    String[] defaultNames1 = { " Amelia", " Chloe", " Koyori", " Kiara" };
    String[] defaultNames2 = { "Watson", "Sakamata", "Hakui", "Oogami" };
    Random rand = new Random();

    Npc() {
        super("");
        this.name = defaultNames2[rand.nextInt(defaultNames2.length)]
                + defaultNames1[rand.nextInt(defaultNames1.length)];
    }

    void betting() {
        this.bet = rand.nextInt(money / 2);
        System.out.println(this.name + "의 베팅 금액은 " + this.bet);
    }

    void getSum() {
        this.sum = 0;
        for (int i = 0; i < this.cardNumber.size(); i++) {
            this.sum += this.cardNumber.get(i);
        }
    }

    void stayOrHit() {
        this.getSum();
        if (this.sum < 13) {
            this.sum = 0;
            this.getCard();
        } else if (this.sum < 16) {
            this.sum = 0;
            if (rand.nextInt(100) < 50) {
                this.getCard();
            }
            else {
                this.checkSum = false;
                this.getSum();
                System.out.println(this.name + " 스테이(합: " + this.sum + ")");
            }
        }
        else if (this.sum < 19) {
            this.sum = 0;
            if (rand.nextInt(100) < 10) {
                this.getCard();
            }
            else {
                this.checkSum = false;
                 this.getSum();
                System.out.println(this.name + " 스테이(합: " + this.sum + ")");
            }
        } else {
            this.checkSum = false;
            this.getSum();
            System.out.println(this.name + " 스테이(합: " + this.sum + ")");
        }
    }

}

class Dealer extends Player{
    Dealer() {
        super("");
        this.name = "딜러";
    }
    void getCardFirst() {
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardNumber.add(rand.nextInt(10) + 1);
        /*
         * this.cardNumber.add(10);
         * this.cardNumber.add(11);
         */ // 블랙잭 나오게 하기
        this.cardMark.add(markList[rand.nextInt(4)]);
        this.cardMark.add(markList[rand.nextInt(4)]);
        System.out.println(this.name + "의 카드는 " + this.cardMark.get(1) + this.cardNumber.get(1) + " 입니다.");
        }

    void getSum() {
        this.sum = 0;
        for (int i = 1; i < this.cardNumber.size(); i++) {
            this.sum += this.cardNumber.get(i);
        } 
    }
    void getCard() {
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardMark.add(markList[rand.nextInt(4)]);
        System.out.print(this.name + "의 카드는 " + this.cardMark.get(cardMark.size() - 1)
                + this.cardNumber.get(cardNumber.size() - 1) +
                "입니다. (합: ");
        this.checkSaveD();
    }

    void checkSaveD() {
        this.sum = 0;
        for (int i = 1; i < this.cardNumber.size(); i++) {
            this.sum += this.cardNumber.get(i);
        }
        if (this.sum > 21) { // 뒤집은 카드 공개
            System.out.println(") 딜러 버스트!");
            this.checkSum = false;
        } else if (this.sum > 16){
            System.out.print(sum + ")\n딜러의 첫 카드는 " + this.cardMark.get(1) + this.cardNumber.get(1) + " 입니다. (합: ");
            this.checkSave();
            this.sum = 0;
            this.checkSum = false;
        } else {
            System.out.println(sum + ")");
            this.sum = 0;
        }
    }

}

public class Cope {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("이름을 입력하세요: ");
        Player you = new Player(scan.nextLine());
        Npc npc1 = new Npc();
        Npc npc2 = new Npc();
        Dealer dealer = new Dealer();

        you.betting();
        npc1.betting();
        npc2.betting();
        you.getCardFirst();
        npc1.getCardFirst();
        npc2.getCardFirst();
        dealer.getCardFirst();

        while (you.checkSum || npc1.checkSum || npc2.checkSum) {
            if (you.checkSum) {
                System.out.print("hit or stay? (h/s): ");
                String temp = scan.nextLine();
                if (temp.equals("s")) {
                    you.checkSum = false;
                }
                else if (temp.equals("h")) {
                    you.getCard();
                }
                else {
                    System.out.println("뭐라고요?");
                    scan.nextLine();
                }
            }

            if (npc1.checkSum) {
                npc1.stayOrHit();
            }
            if (npc2.checkSum) {
                npc2.stayOrHit();
            }
        }
        while (dealer.checkSum) {
            dealer.getCard();
        }
        /*
        내가 짠 코드지만 시발 존나 못 짰네
        시발 코딩 접을까?
        (자칭) 9세 로리우이 ㅈㄴ 귀엽네요
        근데 시발 디코봇으로 만들 생각하니까 벌써 두려운데
        뽀에뽀에뽀에
        */

        scan.close();
    }
}