import java.util.Scanner;

class CRC{
	public static final int kl=32;
	public static final int sl=20;

	int key[]=new int[kl];
	int sData[]=new int[kl+sl];
	int rData[];
	//int flag3;
	int pos1;
	int pos2;

	Scanner scan2=new Scanner(System.in);

	CRC() {
		for(int i=0;i<kl;i++) {
			key[i]=0;
		}
	}


	public void createKey(int flag2) {
		String str;
		char []chararray=null;
		int error=0;

		if(flag2==1) {
			key[31]=1;
			key[30]=1;
			key[27]=1;
			key[20]=1;
			key[15]=1;

			pos1=15;
		}else {
			System.out.println("生成多項式を作成します。(最高次数31)");
			System.out.println("多項式を入力する際は、２進数ビット列とみなしてください（例： \"x^2+1\"の場合は\"101\"）");


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

            //int length=chararray.length;

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

		for(int j=0;j<kl;j++) {
			System.out.printf(""+key[j]);
		}

		System.out.println();

	}

	public void calculate(int flag1) {
		String str;
		char []chararray=null;
		int error=0;

		int []r=new int[kl+1];


		if(flag1==1){

			for(int i=0;i<(kl+sl);i++) {
				sData[i]=0;
			}

			System.out.println("送信データを入力します。(最高ビット数20)");

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

            }while((str.length()==0)||(str.length()>sl)||(error==1));

            for(int i=0;i<chararray.length;i++) {
            	if(chararray[i]=='1') {
            		sData[sl+pos1+1-chararray.length+i]=1;
            	}
            }


    		for(int j=0;j<(kl+sl);j++) {
    			System.out.printf(""+sData[j]);
    		}
    		System.out.println();

            for(int i=0;i<(kl+sl);i++) {
            	if(sData[i]==1) {
            		pos2=i;
            		break;
            	}
            }



    		//計算
    		//r=key;

    		int digit=kl-pos1;
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


    			/*for(int j=0;j<kl;j++) {
        			System.out.printf(""+r[j]);
        		}
        		System.out.println();*/

    		}


			for(int i=0;i<digit-1-(kl+sl-pos2);i++) {
				sData[kl+sl-digit+i+1]=r[kl-digit+kl+sl-pos2+i+1];
	    		//System.out.println("!");
			}


    		for(int j=0;j<(kl+sl);j++) {
    			System.out.printf(""+sData[j]);
    		}
    		System.out.println();


		}else{

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

		if(flag1==1) {

		}

		scan1.close();
	}

}
