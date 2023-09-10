import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Player {
    Player(String a) {
        this.name = a;
    }

    Scanner scan = new Scanner(System.in);
    Random rand = new Random(); // d
    String name;
    int bet;
    int money = 10000;
    ArrayList<Integer> cardNumber = new ArrayList<>();
    ArrayList<String> cardMark = new ArrayList<>();
    String markList[] = { "하트 ", "크로바 ", "스페이드 ", "다이아 " };

    void getCardFirst() {
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardNumber.add(rand.nextInt(10) + 1);
        this.cardMark.add(markList[rand.nextInt(4)]);
        this.cardMark.add(markList[rand.nextInt(4)]);
        System.out.println(this.name + "의 카드는 " + this.cardMark.get(0) + this.cardNumber.get(0) + ", " + this.cardMark.get(1) + this.cardNumber.get(1)
                + " 입니다.");
    }

    void betting() {
        while (true) {
            System.out.print("배팅 금액을 입력하세요(소지금: " + this.money + "): ");
            bet = scan.nextInt();
            if (bet > this.money) {
                System.out.println("배팅 금액이 소지금 보다 많습니다");
            } else if (bet <= 0) {
                System.out.println("베팅 금액이 있어야 합니다");
            } else {
                this.money -= bet;
                break;
            }
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

}

public class Cope {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("이름을 입력하세요: ");
        Player you = new Player(scan.nextLine());
        Npc npc1 = new Npc();
        Npc npc2 = new Npc();

        you.betting();
        npc1.betting();
        npc2.betting();
        you.getCardFirst();
        npc1.getCardFirst();
        npc2.getCardFirst();

        scan.close();//
    }
}