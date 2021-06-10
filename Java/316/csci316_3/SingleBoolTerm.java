class SingleBoolTerm extends Expr {
    BoolTerm boolTerm;

    SingleBoolTerm(final BoolTerm b) {
        boolTerm = b;
    }

    @Override
    String TypeEval() throws TypeError {
        return boolTerm.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <expr>");
        boolTerm.printParseTree(indent + " ");
    }
}
