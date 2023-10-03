import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;
//import java.time.LocalDateTime;

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
    boolean burst = false;
    boolean isBlackjack = false;
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
            // this.sum = 0;
            this.checkSum = false;
            this.burst = true;
        } else if (this.sum == burstNum) {
            System.out.println(" 21) 블랙잭!");
            // this.sum = 0;
            this.checkSum = false;
        } else {
            System.out.println(sum + ")");
            // this.sum = 0;
        }
    }

    /**
     * 
     * @param a 배율입력
     */
    void getMoney(int a) {
        this.money += this.bet * a;
        if (a == 2) {
            System.out.println(name + "님이 " + bet + "를 벌었습니다");
        } else {
            System.out.println(name + "살았습니다.");
        }
    }

    void restart() {
        this.cardMark.clear();
        this.cardNumber.clear();
        this.checkSum = true;
        this.isBlackjack = false;
        this.burst = false;
    }

}

class Npc extends Player {
    String[] defaultNames1 = { " Amelia", " Chloe", " Koyori", " Mio", " Kiara", " Watame", " Poruka", " Gozaru",
            " Iroha", " Mumei" };
    String[] defaultNames2 = { "Watson", "Sakamata", "Hakui", "Oogami", "Takanashi", "Tsunomaki", "Omaru", "Kazama",
            "Nanashi" };
    Random rand = new Random();

    Npc() {
        super("");
        this.name = defaultNames2[rand.nextInt(defaultNames2.length)]
                + defaultNames1[rand.nextInt(defaultNames1.length)];
    }

    void betting() {
        this.bet = rand.nextInt(money / 2);
        System.out.println(this.name + "의 베팅 금액은 " + this.bet);
        this.money -= this.bet;
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
            // this.sum = 0;
            this.getCard();
        } else if (this.sum < 16) {
            // this.sum = 0;
            if (rand.nextInt(100) < 50) {
                this.getCard();
            } else {
                this.checkSum = false;
                this.getSum();
                System.out.println(this.name + " 스테이(합: " + this.sum + ")");
            }
        } else if (this.sum < 19) {
            // this.sum = 0;
            if (rand.nextInt(100) < 10) {
                this.getCard();
            } else {
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

class Dealer extends Player {
    Dealer() {
        super("");
        this.name = "딜러";
    }

    void getCardFirst() {
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardNumber.add(rand.nextInt(10) + 1);
        //this.cardNumber.add(1);
        //this.cardNumber.add(20);
        // 블랙잭 나오게 하기
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
            System.out.println(sum + ") 딜러 버스트!");
            this.checkSum = false;
            this.burst = true;
        } else if (this.sum > 16) {
            System.out.print(sum + ")\n딜러의 첫 카드는 " + this.cardMark.get(0) + this.cardNumber.get(0) + " 입니다. (합: ");
            this.checkSave();
            // this.sum = 0;
            this.checkSum = false;
        } else {
            System.out.println(sum + ")");
            // this.sum = 0;
        }
    }

}
class Card {
    String mark;
    String number;
    int mean;
    Card(String a, String b, int c){
        mark = a;
        number = b;
        mean = c;
    }
}

public class test {
    /*
     * 
     * @param a - sec input
     */
    public static void sleep(int a) {
        try {
            Thread.sleep(a * 1000);
        } catch (InterruptedException e) {

        }
    }

    public static void check(int a, Player p) {
        if (a > 21 - p.sum && !p.burst) {
            p.getMoney(2);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("이름을 입력하세요: ");
        Player you = new Player(scan.nextLine());
        Npc npc1 = new Npc();
        Npc npc2 = new Npc();
        Dealer dealer = new Dealer();
        //ArrayList<Card> cards = new ArrayList<>();
        String[] markList = { "하트 ", "크로바 ", "스페이드 ", "다이아 " };
        String[] numberList = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // 13
        Card[] cards = new Card[52];
        for (int i = 0,index = 0; i < 4; i++) {
            for (int j = 0; j < numberList.length; j++) {
                if (j < 10) {
                    cards[index] = new Card(markList[i], numberList[j], j + 1);
                    index++;
                } else {
                    cards[index] = new Card(markList[i], numberList[j], 10);
                    index++;
                }
            }
        }
        for(int a = 0; a<52;a++){
            System.out.println(a + cards[a].mark + cards[a].number + cards[a].mean);
        }
        
        while (true) {
            you.betting();
            sleep(1);
            npc1.betting();
            sleep(1);
            npc2.betting();
            you.getCardFirst();
            sleep(1);
            npc1.getCardFirst();
            sleep(1);
            npc2.getCardFirst();
            sleep(1);
            dealer.getCardFirst();

            while (you.checkSum || npc1.checkSum || npc2.checkSum) {
                while (you.checkSum) {
                    System.out.print("hit or stay? (h/s): ");
                    String temp = scan.nextLine();
                    if (temp.equals("s")) {
                        you.checkSum = false;
                    } else if (temp.equals("h")) {
                        you.getCard();
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다");
                        // scan.nextLine();
                    }
                }

                if (npc1.checkSum) {
                    sleep(3);
                    npc1.stayOrHit();
                }
                if (npc2.checkSum) {
                    sleep(2);
                    npc2.stayOrHit();
                }
            }
            while (dealer.checkSum) {
                sleep(2);
                dealer.getCard();
            }
            // 대충 계산하는 코드 넣죠?

            if (dealer.burst) { // 딜러가 버스트
                if (!you.burst) {
                    you.getMoney(2);
                }
                if (!npc1.burst) {
                    npc1.getMoney(2);
                }
                if (!npc2.burst) {
                    npc2.getMoney(2);
                }
            } else if (dealer.sum == 21) { // 딜러가 블랙잭
                if (you.sum == 21) {
                    you.getMoney(1);
                }
                if (npc1.sum == 21) {
                    npc1.getMoney(1);
                }
                if (npc2.sum == 21) {
                    npc2.getMoney(1);
                }
            } else { // 21에 젤 가까운 사람
                check(21 - dealer.sum, you);
                check(21 - dealer.sum, npc1);
                check(21 - dealer.sum, npc2);
            }
            /*
             * String temp2;
             * try {
             * temp2 = scan.nextLine();
             * } catch (InputMismatchException e) {
             * System.out.println("옳바른 정수값을 입력해 주세요");
             * scan.nextLine();
             * }
             */
            System.out.print("계속하시겠습니까?(y/n): ");
            String temp2 = scan.nextLine();
            if (temp2.equals("n")) {
                break;
            } else if (you.money <= 0) {
                System.out.println("돈이 부족합니다.");
                break;
            } else {
                you.restart();;
                npc1.restart();
                npc2.restart();
                dealer.restart();
            }

        }
        scan.close();
    }
}