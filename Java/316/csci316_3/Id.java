class Id extends Primary {
    String id;

    Id(final String ident) throws TypeError {
        id = ident;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String type = Parser.types.get(id);

        // An undeclared variable was found.
        if (type == null) {
            throw new TypeError("undeclared variable " + id
                    + " found in expression");
        }

        return type;
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <primary> " + id);
    }
}