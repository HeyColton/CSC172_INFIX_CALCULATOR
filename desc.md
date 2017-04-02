### Problem 1 (Infix to Postfix):
  
Converting infix expression to postfix.
There are multiple expressions. One expression per line.

Output each converted expression in one line. There is a space between two tokens. Do not output any other character.

Sample Input
```
1 + 1
5.2 + 1
5.2 + 1.0
1 - 1
5.2 - 1
1.0 - 5.2 
3.0 * 4
4 / 0.25
1 - (4 + 5)
1 - (1 - (4 + 5))
0 < 55
3.2 < 2.3
0 > 55
3.2 > 2.3
5 = 5.0
1 = 1
1 = 0
1 & 1
1 & 0
1 | 0
0 | 0
!0
!1
!(3 * (1 + 6) = 63 / 3)
(1 < 3) & (2 > 4) | 1
```
Sample Output
```
1 3 < 2 4 > & 1 |
1 1 +
5.2 1 +
5.2 1.0 +
1 1 -
5.2 1 -
1.0 5.2 -
3.0 4 *
4 0.25 /
1 4 5 + -
1 1 4 5 + - -
0 55 <
3.2 2.3 <
0 55 >
3.2 2.3 >
5 5.0 =
1 1 =
1 0 =
1 1 &
1 0 &
1 0 |
0 0 |
0 !
1 !
3 1 6 + * 63 3 / = !
```

By default, you should read from System.in and write to System.out. If your program handles input file and output file. Please use the following main method.
```java
    public static void main(String[] args) {
        int c;
        String in = "in.txt";
        String out = "out.txt";
        try {
            FileOutputStream os = new FileOutputStream(in, false);
            while((c=System.in.read()) != -1) {
                os.write(c);
            }
            os.close();
            /*------------------------
            *
            * Your code which converts in.txt to out.txt
            *
            *-----------------------*/
            
            FileInputStream is = new FileInputStream(out);
            while((c=is.read()) != -1) {
                System.out.write(c);
            }
            is.close();
            System.out.flush();
        }
        catch(Exception e) {

        }
    }
```