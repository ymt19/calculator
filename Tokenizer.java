import java.util.ArrayList;

class Tokenizer {
    String str;
    public Tokenizer (String str) {
        this.str = str;
    }

    public ArrayList<Token> tokenize () {
        ArrayList<Token> tokens = new ArrayList<Token>();
        int pos = 0;
        while (str.length() > pos) {
            char c = str.charAt(pos);

            // ignoer space
            if (c == ' ') {
                pos++;
                continue;
            }

            // add symbol to token list
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || c == '(') {
                Token token = new Token();
                String val = "";
                val += c;
                token.setOperater(val);
                tokens.add(token);
                pos++;
                continue;
            }

            // add integer to token list
            if (c >= '0' && c <= '9') {
                String num = "";
                while (str.length() > pos && str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
                    num += str.charAt(pos);
                    pos++;
                }
                Token token = new Token();
                token.setOperand(num);
                tokens.add(token);
                continue;
            }

            // error
            System.err.println("tokenize error.");
            System.exit(1);
        }

        // add eof to token list
        Token token = new Token();
        token.setEof();
        tokens.add(token);

        return tokens;
    }

    
}