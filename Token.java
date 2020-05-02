class Token {
    String val;
    Token next;
    TokenKind kind;
    public enum TokenKind {
        TK_OPERAND,
        TK_OPERATOR,
        TK_EOF,
    };

    public void setOperand (String val) {
        this.val = val;
        this.kind = TokenKind.TK_OPERAND;
    }

    public void setOperater (String val) {
        this.val = val;
        this.kind = TokenKind.TK_OPERATOR;
    }

    public void setEof () {
        this.kind = TokenKind.TK_EOF;
    }

    public boolean isOperand () {
        if (this.kind == TokenKind.TK_OPERAND) return true;
        else return false;
    }

    public boolean isOperator() {
        if (this.kind == TokenKind.TK_OPERATOR) return true;
        else return false;
    }

    public boolean isEof() {
        if (this.kind == TokenKind.TK_EOF) return true;
        else return false;
    }
}