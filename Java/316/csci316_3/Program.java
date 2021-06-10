class Program {
    VarDeclarations varDeclarations;
    Statement statement;

    Program(final VarDeclarations v, final Statement s) {
        varDeclarations = v;
        statement = s;
    }

    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <program>");
        varDeclarations.printParseTree(indent1);
        statement.printParseTree(indent1);
    }
}