class MultipleVarDec extends VarDeclarations {
    VarDec varDec;
    VarDeclarations varDeclarations;

    MultipleVarDec(final VarDec v, final VarDeclarations vd) {
        varDec = v;
        varDeclarations = vd;

    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <var declarations>");
        varDec.printParseTree(indent1);
        varDeclarations.printParseTree(indent1);
    }
}