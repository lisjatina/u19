package jtm.activity08;

public class SimpleCalc {

	public static int add(int a, int b) throws SimpleCalcException
			{validateInput(a,b);
		return validateOutput(a,b, "+");
	}
	public static int subtract(int a, int b) throws SimpleCalcException
			{ validateInput(a,b);
		return validateOutput(a,b, "-");
	}

	public static int multiply(int a, int b) throws SimpleCalcException
			{ validateInput(a,b);
			return validateOutput(a,b, "*");	}

	public static int divide(int a, int b) throws SimpleCalcException
			{validateInput(a,b);
			return validateOutput(a,b, "/");
	}

	// 1 specify that method can throw SimpleCalcException
	// 2 Validate that inputs are in range of -10..+10 using assertions
	// Use following messages for assertion description if values are not in
	// range:
	// "input value a: A is below -10"
	// "input value a: A is above 10"
	// "input value b: B is below -10"
	// "input value b: B is above 10"
	// "input value a: A is below -10 and b: B is below -10"
	// "input value a: A is above 10 and b: B is below -10"
	// "input value a: a is below -10 and b: B is above 10"
	// "input value a: a is above 10 and b: B is above 10"
	//
	// where: A and B are actual values of a and b.
	//
	// hint:
	// note that assert allows only simple boolean expression
	// (i.e. without &, |, () and similar constructs).
	// therefore for more complicated checks use following approach:
	// if (long && complicated || statement)
	// assert false: "message if statement not fulfilled";

	// 3 Catch assertion errors and wrap them in SimpleCalcExceptions with
	// message "Assertion error" and caught assertion error as a cause.
	private static void validateInput(int a, int b) throws SimpleCalcException
			{
				try {
					if (a < -10 & b<-10 ) assert false: "input value a: " + a +  " is below -10 and b: " + b + " is below -10";
					if (a >10 & b<-10 ) assert false: "input value a: " + a +  " is above 10 and b: " + b + " is below -10";
					if (a < -10 & b>10 ) assert false: "input value a: " + a +  " is below -10 and b: " + b + " is above 10";
					if (a >10 & b>10 ) assert false: "input value a: " + a +  " is above 10 and b: " + b + " is above 10";

					if (a < -10 ) assert false: "input value a: " + a +  " is below -10";
					if (a >10 ) assert false: "input value a: " + a +  " is above 10";

					if (b < -10 ) assert false: "input value b: " + b +  " is below -10";
					if (b >10 ) assert false: "input value b: " + b +  " is above 10";

				} catch (AssertionError e){
					throw  new SimpleCalcException("Assertion error", e);
				}

	}

	// use this method to check that result of operation is also in
	// range of -10..+10.
	// If result is not in range:
	// throw SimpleCalcException with message:
	// "output value a oper b = result is above 10"
	// "output value a oper b = result is below -10"
	// where oper is +, -, *, /
	// Else:
	// return result
	// Hint:
	// If division by zero is performed, catch original exception and create
	// new SimpleCalcException with message "division by zero" and add
	// original division exception as a cause for it.
	private static int validateOutput(int a, int b, String operation) throws SimpleCalcException
			{
				int result = 0;
				switch (operation){
					case "+":
						result = a+b;
						break;
					case "-":
						result = a-b;
						break;
					case "*":
						result= a*b;
						break;
					case "/":
						if (b == 0){
							throw new SimpleCalcException("division by zero", new ArithmeticException("/ by zero"));
						}
						result = a/b;
						break;				}
				if (result>10)
					throw new SimpleCalcException("output value " + a + " " + operation + " " + b
					+ " = " + result + " is above 10");
				if (result< - 10)
					throw new SimpleCalcException("output value " + a + " " + operation + " " + b
							+ " = " + result + " is below -10");
		return result;
	}
}
