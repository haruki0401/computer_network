import java.util.Scanner;

class CRC{
	int key[]=new int[32];
	long data;

	int flag3;
	int digit;

	Scanner scan2=new Scanner(System.in);

	CRC() {
		for(int i=0;i<32;i++) {
			key[i]=0;
		}
	}


	public void createKey(int flag2) {
		if(flag2==1) {
			key[0]=1;
			key[1]=1;
			key[4]=1;
			key[11]=1;
			key[16]=1;
		}else {
			System.out.println("生成多項式を作成します。(最大次数31");

			do {
				System.out.println("係数が1となるを次数を一つずつ順に入力してください。（例: \"x^2+1\"の場合は 0と2");

				digit=scan2.nextInt();
				while(digit<0||digit>31){
					System.out.println("正しい数字を入力してください。");
					digit=scan2.nextInt();
				};
				key[digit]=1;

				System.out.println("まだ入力がが必要な場合は \"1\"を、入力を終えた場合は\"2\"を入力して下さい。");
				flag3=scan2.nextInt();
				while((flag3!=1)&&(flag3!=2)){
					System.out.println("正しい数字を入力してください。");
					flag3=scan2.nextInt();
				};

			}while(flag3==1);

		}

		for(int j=31;j>=0;j--) {
			System.out.printf(""+key[j]);
		}
		System.out.println();

	}

	public void calculate(int flag1) {
		if(flag1==1){
			System.out.println("送信するデータを入力してください(最大10ビット)");
			data=scan2.nextLong();
			while((String.valueOf(data).length()>10));
		}else {

		}

	}


}


public class prog1 {

	public static void main(String[] args) {

		int flag1;
		int flag2;

		//int key[]= new int[32];

		Scanner scan1=new Scanner(System.in);

		System.out.println("送信データを生成する場合は　\"1\"　を、");
		System.out.println("受信データから誤り検出する場合は \"2\" を入力してください。");

		flag1=scan1.nextInt();
		while((flag1!=1)&&(flag1!=2)){
			System.out.println("正しい数字を入力してください。");
			flag1=scan1.nextInt();
		};


		System.out.println("使用する生成多項式を決定します。");
		System.out.println("デフォルトを使用する場合は \"1\" を、");
		System.out.println("カスタマイズする場合は \"2\" を入力してください。");

		flag2=scan1.nextInt();
		while((flag2!=1)&&(flag2!=2)){
			System.out.println("正しい数字を入力してください。");
			flag2=scan1.nextInt();
		};


		CRC obj=new CRC();

		obj.createKey(flag2);
		obj.calculate(flag1);

		/*for(int j=31;j>=0;j--) {
		System.out.printf(""+key[j]);
		}*/


		if(flag1==1) {

		}




		scan1.close();
	}

}
