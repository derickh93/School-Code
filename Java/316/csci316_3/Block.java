class Block extends Statement {
    SList slist;

    Block(final SList s) throws TypeError {
        slist = s;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        return slist.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <statement>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " <block>");
        slist.printParseTree(indent1 + " ");
    }
}
