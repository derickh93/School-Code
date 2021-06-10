abstract class Term {
    abstract void printParseTree(String indent);

    abstract String TypeEval() throws TypeError;
}