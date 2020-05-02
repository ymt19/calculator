import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        System.out.print("calculation formula=>");

        // standerd input
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        // tokenize
        Tokenizer token = new Tokenizer(input);
        ArrayList<Token> tokens = token.tokenize();

        // parse
        Parser p = new Parser(tokens);
        Node root = p.parse();

        // create rpn
        ArrayList<String> rpn;
        root.runTree();
        rpn = root.rpn;        

        // create ortder to stcak machine
        ArrayList<String> orderToSM = new ArrayList<String>();
        Iterator itr = rpn.iterator();
        while (itr.hasNext()) {
            String str = (String)itr.next();
            if (str.equals("+")) {
                orderToSM.add("add");
                continue;
            }
            if (str.equals("-")) {
                orderToSM.add("sub");
                continue;
            }
            if (str.equals("*")) {
                orderToSM.add("mul");
                continue;
            }
            if (str.equals("/")) {
                orderToSM.add("div");
                continue;
            }
            orderToSM.add("push");
            orderToSM.add(str);
        }
        orderToSM.add("wrt");
        orderToSM.add("halt");

        // operate stack machine
        StackMachine sm = new StackMachine();
        itr = orderToSM.iterator();
        while (itr.hasNext()) {
            String str = (String)itr.next();
            if (str.equals("add")) {
                sm.add();
                continue;
            }
            if (str.equals("sub")) {
                sm.sub();
                continue;
            }
            if (str.equals("mul")) {
                sm.mul();
                continue;
            }
            if (str.equals("div")) {
                sm.div();
                continue;
            }
            if (str.equals("push")) {
                str = (String)itr.next();
                int num = Integer.parseInt(str);
                sm.push(num);
                continue;
            }
            if (str.equals("wrt")) {
                sm.wrt();
                continue;
            }
            if (str.equals("halt")) {
                break;
            }
        }
    }
}