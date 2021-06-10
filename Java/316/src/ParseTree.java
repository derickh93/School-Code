public abstract class ParseTree extends LexicalAnalyzer {

	static boolean errorFound = false;

	public static Program program() {

		VarDeclaration var_declaration = var_declaration();
		Statement statement = statement();
		getToken();
		return new VarDeclarationStatement(var_declaration, statement);
	}

	public static VarDeclaration var_declaration() {

		VarDecl vardecl = vardecl();
		if (state == State.Semicolon) {
			getToken();
			if (t.equals("int") || t.equals("float") || t.equals("boolean")) {
				VarDeclaration var_declaration = var_declaration();
				return new MultiVarDeclaration(var_declaration, vardecl);
			} else {
				return new SingleVarDeclaration(vardecl);
			}
		} else {
			errorMessage(1);
			return null;
		}
	}

	public static VarDecl vardecl() {
		if (t.equals("int")) {
			String key = t;
			IDList idlist = idlist();
			return new Type(key, idlist);
		} else if (t.equals("float")) {
			String key = t;
			IDList idlist = idlist();
			return new Type(key, idlist);
		} else if (t.equals("boolean")) {
			String key = t;
			IDList idlist = idlist();
			return new Type(key, idlist);
		} else {
			errorMessage(2);
			return null;
		}
	}

	public static IDList idlist() {
		getToken();
		if (state.compareTo(State.Id) >= 0
				&& state.compareTo(State.id_tru) <= 0) {
			String id = t;
			getToken();
			if (state == State.Comma) {
				IDList idlist = idlist();
				return new MultiIDList(idlist, id);
			} else {
				return new SingleIDList(id);
			}
		} else {
			errorMessage(1);
			return null;
		}
	}

	public static Statement statement() {
		/**
		 * <statement> --> <cluster> <cluster> --> <assignment> | <increment> |
		 * <decrement>
		 */
		if (state.compareTo(State.Id) >= 0
				&& state.compareTo(State.id_tru) <= 0) {
			Cluster cluster = cluster();
			return cluster;
		} else if (state == State.LBrace) {
			getToken();
			SList slist = slist();
			if (state == State.RBrace) {
				getToken();
				return new Block(slist);
			} else {
				errorMessage(6);
				return null;
			}
		} else if (state == State.Keyword_if) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Expr expr = expr();
				if (state == State.RParen) {
					getToken();
					Statement statement = statement();

					if (state == State.Keyword_else) {
						getToken();
						Statement statement1 = statement();
						return new optionalCondpartElse(statement, statement1,
								expr);
					}
					return new Cond(statement, expr);
				} else {
					errorMessage(4);
					return null;
				}
			} else {
				displayln("Error: ( expected");
				return null;
			}
		} else if (state == State.Keyword_while) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Expr expr = expr();
				if (state == State.RParen) {
					getToken();
					Statement statement = statement();
					return new While(statement, expr);
				} else {
					errorMessage(4);
					return null;
				}
			} else {
				errorMessage(7);
				return null;
			}
		} else if (state == State.Keyword_do) {
			getToken();
			Statement statement = statement();

			if (state == State.Keyword_while) {
				getToken();
				if (state == State.LParen) {
					getToken();
					Expr expr = expr();
					if (state == State.RParen) {
						getToken();
						return new doWhile(statement, expr);
					} else {
						displayln("Error: ) expected");
						return null;
					}
				} else {
					displayln("Error: ( expected");
					return null;
				}
			} else {
				displayln("Error: while expected");
				return null;
			}
		} else {
			displayln("Error: id or { or if or while or do expected");
			return null;
		}
	}

	public static SList slist() {
		Statement statement = statement();
		// getToken();
		if (state.compareTo(State.Id) >= 0
				&& state.compareTo(State.id_tru) <= 0 || state == State.LBrace
				|| state == State.Keyword_if || state == State.Keyword_while
				|| state == State.Keyword_do) {
			SList slist = slist();
			return new MultipleStatements(statement, slist);
		} else {
			return new SingleStatement(statement);
		}
	}

	public static Cluster cluster() {
		String id = t;
		getToken();
		if (state == State.Assign) {
			String assign = t;
			getToken();
			Expr expr = expr();
			if (state == State.Semicolon) {
				getToken();
				return new Assignment(assign, id, expr);
			} else {
				errorMessage(1);
				return null;
			}
		} else if (state == State.Incr) {
			Increment inc = new Increment(id, t);
			getToken();
			if (state == State.Semicolon) {
				getToken();
				return inc;
			} else {
				errorMessage(1);
				return null;
			}
		} else if (state == State.Decr) {
			Decrement dec = new Decrement(id, t);
			getToken();
			if (state == State.Semicolon) {
				getToken();
				return dec;
			} else {
				errorMessage(1);
				return null;
			}
		} else {
			errorMessage(3);
			return null;
		}
	}

	public static Expr expr() {
		BoolTerm boolterm = boolterm();
		if (state == State.Or) {
			getToken();
			Expr expr = expr();
			return new MultiBoolTerm(boolterm, expr);
		} else {
			return new SingleBoolTerm(boolterm);
		}
	}

	public static BoolTerm boolterm() {
		BoolPrimary boolprimary = boolprimary();
		if (state == State.And) {
			getToken();
			BoolTerm boolterm = boolterm();
			return new MultiBoolPrimary(boolterm, boolprimary);
		} else {
			return new SingleBoolPrimary(boolprimary);
		}
	}

	public static BoolPrimary boolprimary() {
		E e = e();
		if (state == State.Lt) {
			String sign = t;
			getToken();
			E e1 = e();
			return new LessThan(sign, e, e1);
		} else if (state == State.Le) {
			String sign = t;
			getToken();
			E e1 = e();
			return new LessThanEqual(sign, e, e1);
		} else if (state == State.Gt) {
			String sign = t;
			getToken();
			E e1 = e();
			return new GreaterThan(sign, e, e1);
		} else if (state == State.Ge) {
			String sign = t;
			getToken();
			E e1 = e();
			return new GreaterThanEqual(sign, e, e1);
		} else if (state == State.Eq) {
			String sign = t;
			getToken();
			E e1 = e();
			return new Equal(sign, e, e1);
		} else if (state == State.neq) {
			String sign = t;
			getToken();
			E e1 = e();
			return new NotEqual(sign, e, e1);
		} else {
			return new onlyE(e);
		}
	}

	public static E e() {
		Term term = term();
		if (state == State.Add) {
			getToken();
			E e = e();
			return new AddE(term, e);
		} else if (state == State.Sub) {
			getToken();
			E e = e();
			return new SubE(term, e);
		} else {
			return new SingleTerm(term);
		}
	}

	public static Term term() {
		Primary primary = primary();
		if (state == State.Mul) {
			getToken();
			Term term = term();
			return new MulE(primary, term);
		} else if (state == State.Div) {
			getToken();
			Term term = term();
			return new DivE(primary, term);
		} else {
			return new SinglePrimary(primary);
		}
	}

	public static Primary primary() {
		if (state.compareTo(State.Id) >= 0
				&& state.compareTo(State.id_tru) <= 0) {
			ID id = new ID(t);
			getToken();
			return id;
		} else if (state == State.Int) {
			Int integer = new Int(Integer.parseInt(t));
			getToken();
			return integer;
		} else if (state == State.Float || state == State.FloatE) {
			FloatPoint floater = new FloatPoint(Float.parseFloat(t));
			getToken();
			return floater;
		} else if (t.equals("true") || t.equals("false")) {
			BoolLiteral bool = new BoolLiteral(t);
			getToken();
			return bool;
		} else if (state == State.LParen) {
			getToken();
			Expr exp = expr();
			if (state == State.RParen) {
				Parenthesis par = new Parenthesis(exp);
				getToken();
				return par;
			} else {
				errorMessage(4);
				return null;
			}
		} else if (state == State.Sub) {
			getToken();
			Primary primary = primary();
			return new negation(primary);
		} else if (state == State.inv) {
			getToken();
			Primary primary = primary();
			return new inverse(primary);
		} else {
			errorMessage(5);
			return null;
		}
	}

	public static void errorMessage(int number) {
		errorFound = true;
		display(state +" "+ t + " Error: ");

		switch (number) {
		case 1:
			displayln("; expected;");
			return;
		case 2:
			displayln("int, float, boolean expected;");
			return;
		case 3:
			displayln("= or ++ or -- expected;");
			return;
		case 4:
			displayln(") expected");
			return;
		case 5:
			displayln("<id> or <int> or <float> or <floatE> or boolean "
					+ "or ( or - or ! expected ");
			return;
		case 6:
			displayln("} expected");
			return;
		case 7:
			displayln("( expected");
			return;
		}
	}

}
