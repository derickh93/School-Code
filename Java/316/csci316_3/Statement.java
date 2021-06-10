abstract class Statement {
    abstract void printParseTree(String indent);

    abstract String TypeEval() throws TypeError;
}
