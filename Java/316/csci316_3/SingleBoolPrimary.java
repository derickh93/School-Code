class SingleBoolPrimary extends BoolTerm {
    BoolPrimary boolPrimary;

    SingleBoolPrimary(final BoolPrimary b) {
        boolPrimary = b;
    }

    @Override
    String TypeEval() throws TypeError {
        return boolPrimary.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <boolTerm>");
        boolPrimary.printParseTree(indent + " ");
    }
}