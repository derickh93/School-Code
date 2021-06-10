class SingleE extends BoolPrimary {
    E e;

    SingleE(final E e_) {
        e = e_;
    }

    @Override
    String TypeEval() throws TypeError {
        return e.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <boolPrimary>");
        e.printParseTree(indent + " ");
    }
}