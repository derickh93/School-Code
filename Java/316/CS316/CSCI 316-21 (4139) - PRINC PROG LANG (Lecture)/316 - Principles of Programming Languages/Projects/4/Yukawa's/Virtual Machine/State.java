/**

The states of the DFA for the lexical analyzer for VM instruction streams.

**/

public enum State 
{ 
  // final states     ordinal number  token accepted 

	Id,              // 0         identifiers
	Int,             // 1         integers
	Float,           // 2         floats without exponentiation part
	FloatE,          // 3         floats with exponentiation part
	Colon,           // 4         :
	Sharp,		 // 5	      #
	Comma,		 // 6         ,

  // non-final states              string recognized    

	Start,           // 7      the empty string
	Plus,		 // 8      +
	Minus,		 // 9	   -
	Period,          // 10      ".", "+.", "-."
	E,               // 11     float parts ending with E or e
	EPlusMinus,      // 12     float parts ending with + or - in exponentiation part
	Underscore,      // 13     id's followed by "_"

	UNDEF,

  // keyword states

	Add, Sub, Mul, Div,
	Or, And, Inv, Neg,
	Lt, Le, Gt, Ge, Eq, Neq,
	Push, Iff, Goto,
	Call, Return
}

// By enumerating the final states first and then the non-final states,
// test for a final state can be done by testing if the state's ordinal number
// is less than or equal to that of RParen.
