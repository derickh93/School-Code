class Ident {
    String ident;

    Ident(final String i) {
        ident = i;
    }

    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " " + ident);
    }
}