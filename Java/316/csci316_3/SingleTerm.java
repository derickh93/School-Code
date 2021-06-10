class SingleTerm extends E {
    Term term;

    SingleTerm(final Term t) {
        term = t;
    }

    @Override
    String TypeEval() throws TypeError {
        return term.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <E>");
        term.printParseTree(indent + " ");
    }
}