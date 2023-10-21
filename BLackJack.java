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
    // Random rand = new Random();
    String name;
    int bet;
    int money = 10000;
    int sum = 0;
    int burstNum = 21;
    boolean checkSum = true;
    boolean burst = false;
    boolean isBlackjack = false;
    ArrayList<Card> cards = new ArrayList<>();
    String markList[] = { "하트 ", "크로바 ", "스페이드 ", "다이아 " };

    void getCardFirst(Card a, Card b) {
        Card aC = new Card(a);
        Card bC = new Card(b);
        this.cards.add(aC);
        this.cards.add(bC);
        System.out.print(this.name + "의 카드는 " + cards.get(0).mark + this.cards.get(0).number + ", "
                + this.cards.get(1).mark + this.cards.get(1).number
                + " 입니다. (합 :");
        this.checkSave();
    }

    void getCard(Card a) {
        Card aC = new Card(a);
        this.cards.add(aC);
        System.out.print(this.name + "의 카드는 " + this.cards.get(cards.size() - 1).mark
                + this.cards.get(cards.size() - 1).number +
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

            if (this.bet > this.money)
                System.out.println("배팅 금액이 소지금 보다 많습니다");
            else if (this.bet <= 0)
                System.out.println("베팅 금액이 있어야 합니다");
            else {
                this.money -= bet;
                break;
            }
        }

    }

    void getSum() {
        this.sum = 0;
        for (Card card : this.cards) {
            this.sum += card.mean;
        }
    }

    void checkSave() {
        getSum();
        aTo11();
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
        this.cards.clear();
        this.checkSum = true;
        this.isBlackjack = false;
        this.burst = false;
        this.bet = 0;
    }

    void aTo11() {
        if (this.sum > 21) { // 21넘음?
            for (int i = 0; i < this.cards.size(); i++) { // ㅇㅇ
                if (this.cards.get(i).mean == 11 && sum > 21) { // A있음?
                    this.cards.get(i).mean = 1; // ㅇㅇ
                    getSum();
                    aTo11();
                } else {
                    burst = true;
                }
            }
            // ㄴㄴ
        }
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

    void stayOrHit(Card a) {
        this.getSum();
        if (this.sum < 13) {
            // this.sum = 0;
            this.getCard(a);
        } else if (this.sum < 16) {
            // this.sum = 0;
            if (rand.nextInt(100) < 50) {
                this.getCard(a);
            } else {
                this.checkSum = false;
                this.getSum();
                System.out.println(this.name + " 스테이(합: " + this.sum + ")");
            }
        } else if (this.sum < 19) {
            // this.sum = 0;
            if (rand.nextInt(100) < 10) {
                this.getCard(a);
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

    void getCardFirst(Card a, Card b) {
        Card aC = new Card(a);
        Card bC = new Card(b);
        this.cards.add(aC);
        this.cards.add(bC);
        // this.cards.add(1);
        // this.cards.add(20);
        // 블랙잭 나오게 하기
        System.out.println(this.name + "의 카드는 " + this.cards.get(1).mark + this.cards.get(1).number + " 입니다.");
    }

    void getSum() {
        this.sum = 0;
        for (int i = 1; i < this.cards.size(); i++) {
            this.sum += this.cards.get(i).mean;
        }
    }

    void getCard(Card a) {
        Card aC = new Card(a);
        this.cards.add(aC);
        System.out.print(this.name + "의 카드는 " + this.cards.get(cards.size() - 1).mark
                + this.cards.get(cards.size() - 1).number +
                "입니다. (합: ");
        this.checkSaveD();
    }

    void checkSaveD() {
        this.sum = 0;
        for (int i = 1; i < this.cards.size(); i++) {
            this.sum += this.cards.get(i).mean;
        }
        if (this.sum > 21) { // 뒤집은 카드 공개
            System.out.println(sum + ") 딜러 버스트!");
            this.checkSum = false;
            this.burst = true;
        } else if (this.sum > 16) {
            System.out.print(
                    sum + ")\n딜러의 첫 카드는 " + this.cards.get(0).mark + this.cards.get(0).number + " 입니다. (합: ");
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
    Card temp;

    Card(String a, String b, int c) {
        mark = a;
        number = b;
        mean = c;
    }

    Card(Card a) {
        this.mark = a.mark;
        this.number = a.number;
        this.mean = a.mean;

    }
}

public class BLackJack {
    /*
     * 
     * @param a - sec input
     */
    public static void sleep(int a) {
        try {
            Thread.sleep(a * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void check(int a, Player p) {
        if (a > 21 - p.sum && !p.burst) {
            p.getMoney(2);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        String[] markList = { "하트 ", "크로바 ", "스페이드 ", "다이아 " };
        String[] numberList = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" }; // 13
        Card[] cards = new Card[52];
        for (int i = 0, index = 0; i < 4; i++) {
            for (int j = 0; j < numberList.length; j++) {
                if (j < 9) {
                    cards[index] = new Card(markList[i], numberList[j], j + 2);
                    index++;
                } else if (j == 12) {
                    cards[index] = new Card(markList[i], numberList[j], 11);
                    index++;
                } else {
                    cards[index] = new Card(markList[i], numberList[j], 10);
                    index++;
                }
            }
        }
        /*
         * for (int a = 0; a < 52; a++) {
         * System.out.println(a + cards[a].mark + cards[a].number + cards[a].mean);
         * }
         */
        System.out.print("이름을 입력하세요: ");
        Player you = new Player(scan.nextLine());
        Npc npc1 = new Npc();
        Npc npc2 = new Npc();
        Dealer dealer = new Dealer();
        Boolean keepGoing = true;
        while (keepGoing) {
            you.betting();
            sleep(1);
            npc1.betting();
            sleep(1);
            npc2.betting();
            // you.getCardFirst(cards[12], cards[0]);
            you.getCardFirst(cards[rand.nextInt(cards.length)], cards[rand.nextInt(cards.length)]);
            sleep(1);
            npc1.getCardFirst(cards[rand.nextInt(cards.length)], cards[rand.nextInt(cards.length)]);
            sleep(1);
            npc2.getCardFirst(cards[rand.nextInt(cards.length)], cards[rand.nextInt(cards.length)]);
            sleep(1);
            dealer.getCardFirst(cards[rand.nextInt(cards.length)], cards[rand.nextInt(cards.length)]);

            while (you.checkSum || npc1.checkSum || npc2.checkSum) {
                while (you.checkSum) {
                    System.out.print("hit or stay? (h/s): ");
                    String temp = scan.nextLine();
                    if (temp.equals("s")) {
                        you.checkSum = false;
                    } else if (temp.equals("h")) {
                        you.getCard(cards[rand.nextInt(cards.length)]);// cards[rand.nextInt(cards.length)
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다");
                        // scan.nextLine();
                    }
                }

                if (npc1.checkSum) {
                    sleep(3);
                    npc1.stayOrHit(cards[rand.nextInt(cards.length)]);
                }
                if (npc2.checkSum) {
                    sleep(2);
                    npc2.stayOrHit(cards[rand.nextInt(cards.length)]);
                }
            }
            while (dealer.checkSum) {
                sleep(2);
                dealer.getCard(cards[rand.nextInt(cards.length)]);
            }

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
            System.out.print("계속하시겠습니까?(y/n): ");
            String temp2 = scan.nextLine();
            while (true) {
                if (temp2.equals("n")) {
                    keepGoing = false;
                    break;
                } else if (you.money <= 0) {
                    System.out.println("돈이 부족합니다.");
                    keepGoing = false;
                    break;
                } else {
                    you.restart();
                    npc1.restart();
                    npc2.restart();
                    dealer.restart();
                    break;
                }

            }

        }
        scan.close();
    }
}