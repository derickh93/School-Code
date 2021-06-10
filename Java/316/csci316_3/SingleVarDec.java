class SingleVarDec extends VarDeclarations {
    VarDec varDec;

    SingleVarDec(final VarDec v) {
        varDec = v;
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <var declarations>");
        varDec.printParseTree(indent1);
    }
}
