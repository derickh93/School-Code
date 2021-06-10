class MultipleStatement extends SList {
    Statement statement;
    SList slist;

    MultipleStatement(final Statement st, final SList sl) {
        statement = st;
        slist = sl;
    }

    @Override
    String TypeEval() throws TypeError {
        return "correct";
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <s list>");
        statement.printParseTree(indent1);
        slist.printParseTree(indent1);
    }
}
