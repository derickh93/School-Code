abstract class Expr {
    abstract void printParseTree(String indent);

    abstract String TypeEval() throws TypeError;
}
