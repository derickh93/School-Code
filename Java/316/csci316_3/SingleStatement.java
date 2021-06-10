class SingleStatement extends SList {
    Statement statement;

    SingleStatement(final Statement s) throws TypeError {
        statement = s;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        return statement.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <s list>");
        statement.printParseTree(indent1);
    }
}
