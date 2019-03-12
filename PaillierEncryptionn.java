import java.util.*;
import java.math.BigInteger;
import java.util.Random;
class PaillierEncryptionn{
	private static BigInteger p,q;  //two prime numbers
	private static BigInteger n,g; //publickeys
	private static BigInteger temp;
	private static BigInteger m;
	private static BigInteger value1,value2;
	private static BigInteger c,r;
	private static BigInteger lamda,mu; //privatekeys
public static void publickey(){
	Random rand = new Random(); 

    p= new BigInteger(5,rand);

    q= new BigInteger(5,rand);
 
   p=prime(p);  
   q=prime(q);
   n=p.multiply(q);
   temp=(p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));
     if(gcd(n,temp).compareTo(BigInteger.valueOf(1))==0){
        //System.out.println(p+" "+q+" "+n+" "+temp);
       }
       else{
        	 System.out.println("You need to get different prime numbers");
	         System.exit(1);
          }
         g=new BigInteger(5,rand);
		 System.out.println("publickeys:");
		 System.out.println("n:"+n+" g:"+g);
 }
 
public static void encryption(){
	Scanner scn=new Scanner(System.in);
	System.out.println("Enter the vote value: ");
    m=scn.nextBigInteger();
	Random rand = new Random(); 
	value1=compute(g,m);
	r=new BigInteger(5,rand);
	value2=compute(r,n);
	c=(value1.multiply(value2)).mod(n.multiply(n));
	System.out.println(c);
}
public static void privatekey(){
	lamda=lcm(p.subtract(BigInteger.valueOf(1)),q.subtract(BigInteger.valueOf(1)));
	mu=compute(g,lamda);
	mu=mu.mod(n.multiply(n));
	mu=(mu.subtract(BigInteger.valueOf(1))).divide(n);
	mu=mu.modPow(BigInteger.valueOf(-1),n);
	System.out.println("Privatekeys: ");
	System.out.println("lamda:"+lamda+" mu:"+mu);
	
}

public static BigInteger prime(BigInteger num){
	Random rand = new Random(); 
	if(num.isProbablePrime(1))
		return num;
	return prime(num= new BigInteger(5,rand));
}	

public static BigInteger gcd(BigInteger num1,BigInteger num2){
	if(num2.compareTo(BigInteger.valueOf(0))==0)
		return num1;
	return gcd(num2,num1.remainder(num2));
	
}

public static BigInteger lcm(BigInteger num1,BigInteger num2){
	BigInteger temp1=num1.multiply(num2);
	BigInteger  temp2=gcd(num1,num2);
	BigInteger  ans=temp1.divide(temp2);
	return ans;
}

public static BigInteger compute(BigInteger num1,BigInteger num2){
	Integer number=num2.intValue();
	return (num1.pow(number));
}

public static void main(String args[]){
  publickey();
  privatekey();
  encryption();
}
}
