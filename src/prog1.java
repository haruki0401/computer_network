import java.util.*;

class CRC{

	int flag1;
	int flag2;

	ArrayList<Integer> temp=new ArrayList<Integer>();//入力したデータ系列の一時保管用
	ArrayList<Integer> key=new ArrayList<Integer>();
	ArrayList<Integer> data=new ArrayList<Integer>();

	Scanner scan2=new Scanner(System.in);

	CRC(int flag1,int flag2) {
		this.flag1=flag1;
		this.flag2=flag2;
	}

	@SuppressWarnings("unchecked")
	public void function() {

		if(flag2==1) {

			setData(1);

		}else {
			System.out.println("生成多項式を作成します。");
			System.out.println("入力する際は、多項式を２進数ビット列とみなしてください（例： \"x^2+1\"の場合は\"101\"）");

			setData(0);
		}

		key=(ArrayList<Integer>) temp.clone();
		temp.clear();

		if(flag1==1){

			System.out.println("送信データ系列を入力します。");

			setData(0);

			data=(ArrayList<Integer>) temp.clone();
			temp.clear();

			for(int i=0;i<key.size()-1;i++) {
				data.add(0);
			}

			int r[]=calculate();

			System.out.printf("[CRC] : ");
			for(int i=1;i<key.size();i++) {
				data.set((data.size()-key.size()+i),r[i]);
				System.out.printf(""+data.get((data.size()-key.size()+i)));
			}
			System.out.println();

			System.out.printf("[符号化送信データ系列] : ");
			for(int i=0;i<data.size();i++) {
				System.out.printf(""+data.get(i));
			}
			System.out.println();
		}else{
			System.out.println("受信データ系列を入力します。");

			setData(0);

			while(temp.size()<key.size()){
				System.out.println("入力データ系列が生成多項式より短いため判定できません。");
				temp.clear();
				setData(0);
			}

			data=(ArrayList<Integer>) temp.clone();
			temp.clear();

			int r[]=calculate();

			int ris0=1;//1:true,2:false

			for(int i=0;i<key.size();i++) {
				if(r[i]==1) {
					ris0=0;
					break;
				}
			}

			if(ris0==1) {
    			System.out.println("誤りなし");
    		}else {
    			System.out.println("誤りあり");
    		}
		}
	}

	private void setData(int d) {
		String str;
		char []chararray=null;
		int error=0;

		if(d==1){
			str="10000100000010011";
            chararray=str.toCharArray();
		}else {
			do{
	        	System.out.println("値を入力してください。(値が正しくない場合は再度表示されます。)");
	            str= scan2.next();
	            chararray=str.toCharArray();
	            error=0;

	            for(int i=0;i<chararray.length;i++){
	            	if((chararray[i]!='0')&&(chararray[i]!='1')) {
	            		error=1;
	            		break;
	            	}
	            }
	        }while((str.length()==0)||(error==1));
		}

        for(int i=0;i<chararray.length;i++) {
        	if(chararray[i]=='1') {
        		temp.add(1);
        	}else {
        		temp.add(0);
        	}
        }

	}


	private int[] calculate() {
		int pos1=0;//次に演算で使用するのデータ系列の桁の位置
		int pos2=key.size();//次の演算でデータ系列から何桁使用するかの指標

		int[] r=new int[key.size()];

		while((pos1+pos2)<data.size()+1) {
			for(int i=0;i<key.size();i++) {
				if(pos2<key.size()) {
					r[i]=r[pos2]^key.get(i);
					pos2++;
				}else {
					r[i]=data.get(pos1)^key.get(i);
					pos1++;
				}
			}
			pos2=key.size();
			for(int i=0;i<key.size();i++) {
				if(r[i]==1) {
					pos2=i;
					break;
				}
			}
		}

		if(pos1!=data.size()) {//商の最後が0になってしまったとき
			for(int j=0;j<(key.size()-(data.size()-pos1));j++) {
				r[j]=r[j+data.size()-pos1];
			}
			for(int j=0;j<(data.size()-pos1);j++) {
				r[(key.size()-(data.size()-pos1))+j]=data.get(pos1);
				pos2++;
			}
		}

		for(int i=0;i<key.size();i++) {
			System.out.printf(""+r[i]);
		}
		System.out.println();

		return r;
	}
}

public class prog1 {
	public static void main(String[] args) {
		int flag1;
		int flag2;
		String loop="0";

		Scanner scan1=new Scanner(System.in);

		while(loop.equals("0")) {
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

			CRC obj=new CRC(flag1,flag2);

			obj.function();

			System.out.println("続けますか？続ける場合は \"0\" を、続けない場合はそれ以外の文字を入力してください。");
			loop=scan1.next();
		}

		System.out.println("終了しました。");

		scan1.close();
	}
}