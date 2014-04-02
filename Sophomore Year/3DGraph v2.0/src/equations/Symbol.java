package equations;


public enum Symbol
{
	//grouped by alpha then by relatedness
	all(),abs("abs"),add("+"),and("and"),acos("acos"),asin("asin"),atan("atan"),
	bita(">>"),bitb("<<"),bitc(">>>"),
	cos("cos"),
	div("/"),
	equal("="),
	factorial("!"),function(":"),
	greaterthan(">"),greaterthanequal(">="),
	Int("int"),
	lessthan("<"),lessthanequal("<="),log("log"),ln("ln"),
	minus("-"),mult("*"),mod("%"),
	notequal("!="),
	or("or"),
	plus("+"),pow("^"),
	root("root"),
	sin("sin"),sqrt("sqrt"),
	tan("tan"),todeg("toDegrees"),torads("toRadians");	
	private Symbol()
	{
		
	}
	private Symbol(String g)
	{
		name=g;
	}
	public String getName()
	{
		return name;
	}
	public static Symbol getSymbol(String g)
	{
		System.out.println("the symbol before removing: "+g);
		if(g.startsWith("equality"))
			g=g.substring("equality".length());
		if(g.startsWith("othervariable"))
			g=g.substring("othervariable".length());
		System.out.println("the symbol after removing: "+g);
		g=g.trim();
		switch(g.charAt(0))
		{
			case 'a':
			{
				switch(g.charAt(1))
				{
					case 'n':
						return and;
					case 'b':
						return abs;
					case 'c':
						return acos;
					case 's':
						return asin;
					case 't':
						return atan;
				}
			}
			case 'b':
			{
				switch(g.charAt(3))
				{
					case 'a':return bita;
					case 'b':return bitb;
					case 'c':return bitc;
				}
			}
			case 's':
			{
				if(g.equals("sin"))
				{
					return sin;
				}
				else
					return sqrt;
			}
			case 'c':
			{
				return cos;
			}
			case 't':
			{
				return tan;
			}
			case '+':
			{
				return plus;
			}
			case '-':
			{
				return minus;
			}
			case '/':
			{
				return div;
			}
			case '*':
			{
				return mult;
			}case '%':
			{
				return mod;
			}case '^':
			{
				return pow;
			}case '=':
				return equal;
			case '!':
			{
				if(g.length()==1)
					return factorial;
				else
					return notequal;
			}
			case '>':
			{
				if(g.length()==1)
					return greaterthan;
				if(g.charAt(1)=='=')
					return greaterthanequal;
			}
			case '<':
			{
				if(g.length()==1)
					return lessthan;
				if(g.charAt(1)=='=')
					return lessthanequal;
			}case ':':
				return function;
		}
		return all;
	}
	String name;
}
