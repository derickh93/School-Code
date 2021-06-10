class Floatp extends Primary {
    float val;

    Floatp(final float f) {
        val = f;
    }

    @Override
    String TypeEval() {
        return "float";
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <primary> " + val);
    }
}