public class getnum{
    public char[27] esn;
    public getnum{
       esn = {null,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z};
    }
    public int getstring(string s){
        int sum = 0;
        int size = s.length();
        for(int i = 0; i<size; ++i){
            int m = charAt(i);
            int n = esn[m-a];
            sum += pow(27,size-i-1)*n;
        }
        return sum;
    }

    public static void main(String args){
        string a = bee;
        System.out.printf("%d\n", getstring(s));
        return 0;
    }
} 