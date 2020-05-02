import java.util.ArrayList;

class Node {
    Node left;
    Node right;
    String val;
    ArrayList<String> rpn;

    public Node () {
        this.left = null;
        this.right = null;
        rpn = new ArrayList<String>();
    }

    public void setNodeOperand (String val) {
        this.val = val;
    }

    public void setNodeOperator (String val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void runTree () {
        dfs(this);
    }

    private void dfs (Node node) {
        if (node.left == null && node.right == null) {
            rpn.add(node.val);
            return;
        }

        dfs(node.left);
        dfs(node.right);
        rpn.add(node.val);
    }
}