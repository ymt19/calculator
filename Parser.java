import java.util.ArrayList;
import java.util.Iterator;

public class Parser {
    private int now;                    // パーサが着目している位置
    private ArrayList<Token> tokens;    // トークン文字列

    public Parser (ArrayList<Token> tokens) {
        this.now = 0;
        this.tokens = tokens;
    }

    // パースを実行する
    // 再帰下降構文解析
    public Node parse () {
        Node root = new Node();
        root = expr();
        return root;
    }

    // expr = mul ("+" mul | "-" mul)*
    private Node expr () {
        Node node = mul();
        for (;;) {
            Token token = tokens.get(this.now);
            if (token.isOperator() && (token.val.equals("+") || token.val.equals("-"))) {
                Node tmp = new Node();
                if (token.val.equals("+")) {
                    this.now++;
                    tmp.setNodeOperator("+", node, mul());
                    node = tmp;
                } else if (token.val.equals("-")) {
                    this.now++;
                    tmp.setNodeOperator("-", node, mul());
                    node = tmp;
                }
            }
            else return node;
        }
    }

    // primary ("*" primary | "/" primary)*
    private Node mul () {
        Node node = primary();
        for (;;) {
            Token token = tokens.get(this.now);
            if (token.isOperator() && (token.val.equals("*") || token.val.equals("/"))) {
                Node tmp = new Node();
                if (token.val.equals("*")) {
                    this.now++;
                    tmp.setNodeOperator("*", node, primary());
                    node = tmp;
                } else if (token.val.equals("/")) {
                    this.now++;
                    tmp.setNodeOperator("/", node, primary());
                    node = tmp;
                }
            }
            return node;
        }
    }

    // primary = num | "(" expr ")"
    private Node primary () {
        Node node = new Node();

        // start (
        if (this.tokens.get(this.now).isOperator()) {
            if (this.tokens.get(this.now).val.equals("(")) {
                this.now++;
                node = expr();
                if (!this.tokens.get(this.now).val.equals(")")) {
                    System.err.println("expect ) error.");
                    System.exit(1);
                }
                this.now++;
                return node;
            }
        }

        // begin integer
        if (!this.tokens.get(this.now).isOperand()) {
            System.err.println("expect number error.");
            System.exit(1);
        }
        node.setNodeOperand(this.tokens.get(this.now++).val);
        return node;
    }
}