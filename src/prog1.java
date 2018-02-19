import java.util.ArrayList;
import java.util.Scanner;

class CRC{
	public static final int kl=32;
	public static final int sl=20;

	int flag1;
	int flag2;

	//int key[]=new int[kl];
	//int data[]=new int[kl+sl];

	ArrayList<Integer> temp=new ArrayList<Integer>();
	ArrayList<Integer> key=new ArrayList<Integer>();
	ArrayList<Integer> data=new ArrayList<Integer>();
	//int rData[]=new int[kl+sl];

	int dataDigit;
	int pos1;
	int pos2;

	Scanner scan2=new Scanner(System.in);

	CRC(int flag1,int flag2) {

		this.flag1=flag1;
		this.flag2=flag2;
		/*for(int i=0;i<kl;i++) {
			key[i]=0;
		}*/

		/*for(int i=0;i<(kl+sl);i++) {
			data[i]=0;
		}*/
	}

	/*public void createKey(int flag2) {
		String str;
		char []chararray=null;
		int error=0;

		if(flag2==1) {
			key[31]=1;
			key[30]=1;
			key[27]=1;
			key[20]=1;
			key[15]=1;


			setData(1,kl);

			pos1=15;
		}else {
			System.out.println("生成多項式を作成します。(最高次数31)");
			System.out.println("入力する際は、多項式を２進数ビット列とみなしてください（例： \"x^2+1\"の場合は\"101\"）");


			setData(0,kl);

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

            }while((str.length()==0)||(str.length()>kl)||(error==1));

            for(int i=0;i<chararray.length;i++) {
            	if(chararray[i]=='1') {
            		key[kl-chararray.length+i]=1;
            	}
            }

            for(int i=0;i<kl;i++) {
            	if(key[i]==1) {
            		pos1=i;
            		break;
            	}
            }

		}

		key=(ArrayList<Integer>) temp.clone();

		temp.clear();

		System.out.println(key.size());

		for(int i=0;i<key.size();i++) {
			System.out.printf(""+key.get(i));
		}
		System.out.println();
	}*/

	public void function() {

		if(flag2==1) {

			setData(1,kl);

			//pos1=15;
		}else {
			System.out.println("生成多項式を作成します。(最高次数31)");
			System.out.println("入力する際は、多項式を２進数ビット列とみなしてください（例： \"x^2+1\"の場合は\"101\"）");

			setData(0,kl);
		}

		key=(ArrayList<Integer>) temp.clone();

		temp.clear();

		System.out.println(key.size());

		for(int i=0;i<key.size();i++) {
			System.out.printf(""+key.get(i));
		}
		System.out.println();
		String str;
		char []chararray=null;
		int error=0;

		//int []r=new int[kl+1];

		int keyDigit;
		int dataDigit;

		if(flag1==1){

			System.out.println("送信データを入力します。(最高ビット数20)");

			setData(0,sl);

			data=(ArrayList<Integer>) temp.clone();

			temp.clear();

			System.out.println(data.size());

			for(int i=0;i<data.size();i++) {
				System.out.printf(""+data.get(i));
			}
			System.out.println();

			//data.addAll(key);

			for(int i=0;i<key.size()-1;i++) {
				data.add(0);
			}

			System.out.println(data.size());

			for(int i=0;i<data.size();i++) {
				System.out.printf(""+data.get(i));
			}
			System.out.println();


			int r[]=calculate();

			for(int i=1;i<key.size();i++) {
				data.set((data.size()-key.size()+i),r[i]);
			}

			for(int i=0;i<data.size();i++) {
				System.out.printf(""+data.get(i));
			}
			System.out.println();

            /*do{
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

            }while((str.length()==0)||(str.length()>sl)||(error==1));

            for(int i=0;i<chararray.length;i++) {
            	if(chararray[i]=='1') {
            		sData[sl+pos1+1-chararray.length+i]=1;
            	}
            }

            for(int i=0;i<(kl+sl);i++) {
            	if(sData[i]==1) {
            		pos2=i;
            		break;
            	}
            }

            dataDigit=pos2;*/

    		//計算

    		/*int digit=kl-pos1;
    		int pos3=kl;

    		while(pos2+digit-(kl-pos3)<=(kl+sl)) {
    			for(int i=0;i<digit;i++) {
    				if((kl-pos3)>0) {
    					r[pos1]=key[pos1]^r[pos3];
    					pos1++;
    					pos3++;
    				}else {
    					r[pos1]=key[pos1]^sData[pos2];
    					pos1++;
    					pos2++;
    				}
    			}

    			pos1=kl-digit;
    			pos3=kl;
    			for(int i=0;i<digit;i++) {
    				if(r[pos1+i]==1) {
    					pos3=pos1+i;
    					break;
    				}
    			}
    		}

			for(int i=0;i<digit-1-(kl+sl-pos2);i++) {
				sData[kl+sl-digit+i+1]=r[kl-digit+kl+sl-pos2+i+1];
			}

    		System.out.printf("[送信データ]:");

    		for(int i=dataDigit;i<(kl+sl);i++) {
    			System.out.printf(""+sData[i]);
    		}
    		System.out.println();*/

		}else{
			/*for(int i=0;i<(kl+sl);i++) {
				rData[i]=0;
			}

			System.out.println("受信データを入力します。(最高ビット数52)");

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

            }while((str.length()==0)||(str.length()>(kl+sl))||(error==1));

            for(int i=0;i<chararray.length;i++) {
            	if(chararray[i]=='1') {
            		rData[kl+sl-chararray.length+i]=1;
            	}
            }

            for(int i=0;i<(kl+sl);i++) {
            	if(rData[i]==1) {
            		pos2=i;
            		break;
            	}
            }

            //余り計算

            int digit=kl-pos1;
    		int pos3=kl;

    		while(pos2+digit-(kl-pos3)<=(kl+sl)) {
    			for(int i=0;i<digit;i++) {
    				if((kl-pos3)>0) {
    					r[pos1]=key[pos1]^r[pos3];
    					pos1++;
    					pos3++;
    				}else {
    					r[pos1]=key[pos1]^rData[pos2];
    					pos1++;
    					pos2++;
    				}
    			}


    			pos1=kl-digit;
    			pos3=kl;
    			for(int i=0;i<digit;i++) {
    				if(r[pos1+i]==1) {
    					pos3=pos1+i;
    					break;
    				}
    			}
    		}

            int ris0=0;//1:true 0:false

            for(int i=0;i<digit-1;i++) {
            	if(r[kl-digit+1+i]==1) {
            		ris0=1;
            		break;
            	}
            }


            if(ris0==0) {
	            while(pos2!=52) {
	            	if(rData[pos2]==0) {
	            		pos2++;
	            	}else {
	            		break;
	            	}
	            }
            }

    		if((pos2==52)&&(ris0==0)) {
    			System.out.println("誤りなし");
    		}else {
    			System.out.println("誤りあり");
    		}*/
		}
	}

	private void setData(int d, int dataLength) {
		String str;
		char []chararray=null;
		int error=0;

		//int d[]=new int[kl+sl];

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

	        }while((str.length()==0)||(str.length()>dataLength)||(error==1));
		}

        for(int i=0;i<chararray.length;i++) {
        	if(chararray[i]=='1') {
        		temp.add(1);
        	}else {
        		temp.add(0);
        	}
        }

        /*for(int i=0;i<dataLength;i++) {
        	if(data.get(i)==1) {
        		digit=dataLength-i;
        		break;
        	}
        }*/
	}


	private int[] calculate() {
		//int digit=kl-pos1;
		int pos1=0;
		int pos2=key.size();

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

			//pos1=kl-digit;
			pos2=key.size();
			for(int i=0;i<key.size();i++) {
				if(r[i]==1) {
					pos2=i;
					break;
				}
			}

			System.out.println(pos1);
			System.out.println(pos2);


			for(int i=0;i<key.size();i++) {
				System.out.printf(""+r[i]);
			}
			System.out.println();
		}

		if(pos1!=data.size()) {
			for(int j=0;j<(key.size()-(data.size()-pos1));j++) {
				r[j]=r[j+data.size()-pos1];
			}

			for(int i=0;i<key.size();i++) {
				System.out.printf(""+r[i]);
			}
			System.out.println();
			for(int j=0;j<(data.size()-pos1);j++) {
				r[(key.size()-(data.size()-pos1))+j]=data.get(pos2);
				pos2++;
			}
		}

		for(int i=0;i<key.size();i++) {
			System.out.printf(""+r[i]);
		}
		System.out.println();

		/*if(pos1<data.size()) {
			for(int )

			for(int i=key.size();i>0;i--) {
				r[i-1]=data.get(data.size()-i);
				pos1++;
			}
		}*/

		/*for(int i=0;i<digit-1-(kl+sl-pos2);i++) {
			sData[kl+sl-digit+i+1]=r[kl-digit+kl+sl-pos2+i+1];
		}

		System.out.printf("[送信データ]:");

		for(int i=dataDigit;i<(kl+sl);i++) {
			System.out.printf(""+sData[i]);
		}
		System.out.println();*/

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