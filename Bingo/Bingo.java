import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Bingo {
	public static ArrayList<Integer> lotteryNumList = new ArrayList<Integer>();
	public static int[][] isBingoList = new int[5][5];
	public static final int REACH = 4;
	public static final int BINGO = 5;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int[][] card = new int[5][5];
		int i, j;

		for (i = 1;i < 76;i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);//数字を生成してシャッフル

		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				card[i][j] = numbers.get(5 * i + j);//カード生成
				isBingoList[i][j] = 0;  //ビンゴリスト初期化
			}
		}

		Collections.shuffle(numbers);//抽選用にもう一度シャッフル

		for(Integer lotteryNum : numbers) {
			dispCard(card,lotteryNum);
			sc.nextLine();
		}
	}

	// カード表示
	static void dispCard(int[][] card,int num){
		String msg ="\n";
		int isBingo = 1;
		lotteryNumList.add(num);
		for (Integer i: lotteryNumList) {
			msg += "『" + i +"』";
		}
		System.out.println("---------------");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(card[i][j] == num){
					isBingoList[i][j] = isBingo;
					System.out.print("|\u001b[31m");
					System.out.print("==");
					System.out.print("\u001b[00m");
					msg +=" \n あたり！";
				 } else if(lotteryNumList.indexOf(card[i][j]) > -1){//すでに当選済み
				 	System.out.print("|\u001b[31m");
				 	System.out.print("==");
				 	System.out.print("\u001b[00m");
				 } else {
				 	if(card[i][j] > 10) {
				 		System.out.print("|" + card[i][j]);
				 	} else {
						 System.out.print("|" +String.format("%02d", card[i][j]));//0うめ
						}
					}
				}
				System.out.print("|");
				System.out.println("");
			}
			System.out.println("---------------");
			System.out.println("~~~~抽選番号~~~~");
			System.out.println(msg);
			isBingoCheck();
		}

	 	//ビンゴorリーチしてるか
		static void isBingoCheck(){
			int count = 0;

		 	//横チェック
			for (int i = 0; i < 5; i++) {
				count =0;
				for (int j = 0; j < 5; j++) {
					if(isBingoList[i][j] == 1){
						count++;
					}
				}
				System.out.println("");
				if (count == REACH) {
					System.out.println("横リーチ！");
				}
				if (count == BINGO) {
					System.out.println("横ビンゴ！");
				}
			}

		 	//縦チェック
			for (int i = 0; i < 5; i++) {
				count =0 ;
				for (int j = 0; j < 5; j++) {
					if(isBingoList[j][i] == 1){
						count++;
					}
				}
				if (count == REACH) {
					System.out.println("縦リーチ！");
				}
				if (count == BINGO) {
					System.out.println("縦ビンゴ！");
				}
			}

		 	//左ななめチェック
			count =0;
			for (int i = 0; i < 5; i++) {
				if(isBingoList[i][4 - i] == 1){
					count++;
				}
				if (count == REACH) {
					System.out.println("左ななめリーチ！");
				}
				if (count == BINGO) {
					System.out.println("左ななめビンゴ！");
				}
			}

		 	//右ななめチェック
			count =0;
			for (int i = 0; i < 5; i++) {
				if(isBingoList[i][i] == 1){
					count++;
				}
				if (count == REACH) {
					System.out.println("右ななめリーチ！");
				}
				if (count == BINGO) {
					System.out.println("右ななめビンゴ！");
				}
			}

		}

	}
