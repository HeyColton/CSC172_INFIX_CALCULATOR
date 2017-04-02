
/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Project number:2
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class INFIX_CALCULATOR {
	public static void ShuntingYardAlgorithm(String in, String out) {
		try {
			Scanner file = new Scanner(new File(in));
			PrintWriter writer = new PrintWriter(new File(out));
			// get every line
			while (file.hasNext()) {
				String string = file.nextLine();

				writer.println(InToPost(string));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String InToPost(String string) {

		MyOwnQueue<String> queue = new MyOwnQueue<String>();
		MyOwnStack<String> stack = new MyOwnStack<String>();
		int i = 0;
		while (i < string.length()) {
			String op = Character.toString(string.charAt(i));

			// separate several conditions to check if it's tan, sin,
			// numbers, operators or space
			if (i + 1 < string.length()) {
				if (op.equals("s")) {
					op = op + Character.toString(string.charAt(i + 1)) + Character.toString(string.charAt(i + 2));
					i += 2;
				} else if (op.equals("t")) {
					op = op + Character.toString(string.charAt(i + 1)) + Character.toString(string.charAt(i + 2));
					i += 2;
				} else if (op.equals("c")) {
					op = op + Character.toString(string.charAt(i + 1)) + Character.toString(string.charAt(i + 2));
					i += 2;
				} else if (Character.toString(string.charAt(i + 1)).equals(".")) {
					do {
						op = op + Character.toString(string.charAt(i + 1));
						i++;
						if (i + 1 >= string.length()) {
							break;
						}
					} while (isNumber(Character.toString(string.charAt(i + 1))));
				} else if (isNumber(Character.toString(string.charAt(i)))) {
					while (isNumber(Character.toString(string.charAt(i + 1)))) {
						op = op + Character.toString(string.charAt(i + 1));
						i++;
						if (i + 1 >= string.length()) {
							break;
						}
					}
				}
			}
			// If the token is an operand, enqueue it
			if (isNumber(op)) {
				queue.enqueue(op);

				// If the token is a close-parenthesis [‘)’], pop all
				// the stack elements and enqueue them one by one until
				// an open-parenthesis [‘(‘] is found.
			} else if (op.equals(")")) {
				String pop = stack.peek();

				while (!pop.equals("(")) {

					queue.enqueue(stack.pop());
					pop = stack.peek();

				}
				stack.pop();
			} else if (op.equals(" ")) {

			} else if (op.equals("(")) {
				stack.push("(");
			} else if (precedence(op) != -1) {
				// if it's an operator
				if (stack.peek() != null) {
					while (precedence(stack.peek()) >= precedence(op)) {
						if (precedence(stack.peek()) == precedence(op) & RightCheck(stack.peek())) {
							queue.enqueue(stack.pop());
							break;
						}
						queue.enqueue(stack.pop());
						if (stack.peek() == null) {
							break;
						}
					}
				}
				stack.push(op);
			} else {
				try {
					throw new Exception();
				} catch (Exception e) {
					System.out.println("Invalid Excepression!");
				}
			}

			i++;
		}
		while (stack.peek() != null) {
			queue.enqueue(stack.pop());
		}
		// convert every data in queue to a string
		String result = data(queue.First);
		return result.substring(0, result.length() - 1);

	}

	public static String data(MyDoubleNode node) {
		if (node == null) {
			return "";
		} else {
			return node.data + " " + data(node.next);
		}

	}

	// check if it is right-associative
	public static Boolean RightCheck(String c) {
		Boolean check = false;
		if (c.equals("=") | c.equals("^") | c.equals("!")) {
			check = true;
		}
		return check;
	}

	// precedence of operators
	public static int precedence(String c) {
		int i = -1;
		if (c.equals("=")) {
			i = 1;
		} else if (c.equals(">") | c.equals("<") | c.equals("&") | c.equals("|")) {
			i = 2;
		} else if (c.equals("+") | c.equals("-") | c.equals("!")) {
			i = 3;
		} else if (c.equals("*") | c.equals("/") | c.equals("%")) {
			i = 4;
		} else if (c.equals("^")) {
			i = 5;
		} else if (c.equals("sin") || c.equals("cos") | c.equals("tan")) {
			i = 6;
		} else if (c.equals("(")) {
			i = 0;
		}

		return i;

	}

	// check if it is a number
	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public static void PostfixEvaluation(String in, String out) {
		try {
			Scanner file = new Scanner(new File(in));
			PrintWriter writer = new PrintWriter(new File(out));
			while (file.hasNext()) {
				String string = file.nextLine();

				writer.println(postfix(string));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String postfix(String string) {
		MyOwnStack<String> stack = new MyOwnStack<String>();
		MyOwnQueue<String> queue = new MyOwnQueue<String>();
		int i = 0;
		while (i < string.length()) {
			String op = Character.toString(string.charAt(i));
			if (i + 1 < string.length()) {
				if (op.equals("s")) {
					op = op + Character.toString(string.charAt(i + 1)) + Character.toString(string.charAt(i + 2));
					i += 2;
				} else if (op.equals("t")) {
					op = op + Character.toString(string.charAt(i + 1)) + Character.toString(string.charAt(i + 2));
					i += 2;
				} else if (op.equals("c")) {
					op = op + Character.toString(string.charAt(i + 1)) + Character.toString(string.charAt(i + 2));
					i += 2;
				} else if (Character.toString(string.charAt(i + 1)).equals(".")) {
					do {
						op = op + Character.toString(string.charAt(i + 1));
						i++;
						if (i + 1 >= string.length()) {
							break;
						}
					} while (isNumber(Character.toString(string.charAt(i + 1))));
				} else if (isNumber(Character.toString(string.charAt(i)))) {
					while (isNumber(Character.toString(string.charAt(i + 1)))) {
						op = op + Character.toString(string.charAt(i + 1));
						i++;
						if (i + 1 >= string.length()) {
							break;
						}
					}
				}
			}

			if (isNumber(op)) {
				stack.push(op);
			} else if (op.equals(" ")) {

			} else {

				operation(stack, op);
			}

			i++;
		}
		NumberFormat formatter = new DecimalFormat("#0.00");

		return formatter.format((Double.valueOf(stack.First.data)));

	}

	public static void operation(MyOwnStack<String> s, String op) {
		switch (op) {
		case "+":
			try {
				s.push(Double.toString(Double.valueOf(s.pop()) + Double.valueOf(s.pop())));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}

			break;
		case "-":
			try {
				s.push(Double.toString(-Double.valueOf(s.pop()) + Double.valueOf(s.pop())));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "*":
			try {
				s.push(Double.toString(Double.valueOf(s.pop()) * Double.valueOf(s.pop())));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "/":
			try {
				Double s1 = Double.valueOf(s.pop());
				Double s2 = Double.valueOf(s.pop());
				s.push(Double.toString(s2 / s1));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "^":
			try {
				Double t1 = Double.valueOf(s.pop());
				Double t2 = Double.valueOf(s.pop());
				s.push(Double.toString(Math.pow(t2, t1)));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "<":
			try {
				if (Double.valueOf(s.pop()) > Double.valueOf(s.pop())) {
					s.push("1");
				} else {
					s.push("0");
				}
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case ">":
			try {
				if (Double.valueOf(s.pop()) > Double.valueOf(s.pop())) {
					s.push("0");
				} else {
					s.push("1");
				}
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "=":
			try {
				if (Double.valueOf(s.pop()).equals(Double.valueOf(s.pop()))) {
					s.push("1");
				} else {
					s.push("0");
				}
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "&":
			try {
				if (Double.valueOf(s.pop()) == 0 | Double.valueOf(s.pop()) == 0) {
					s.push("0");
				} else {
					s.push("1");
				}
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "|":
			try {
				if (Double.valueOf(s.pop()) == 1 | Double.valueOf(s.pop()) == 1) {
					s.push("1");
				} else {
					s.push("0");
				}
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "!":
			try {
				if (Double.valueOf(s.pop()) == 1) {
					s.push("0");
				} else {
					s.push("1");
				}
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "%":
			try {
				Double m1 = Double.valueOf(s.pop());
				Double m2 = Double.valueOf(s.pop());
				s.push(Double.toString(m2 % m1));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "sin":
			try {
				s.push(Double.toString(Math.sin(Double.valueOf(s.pop()))));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "cos":
			try {
				s.push(Double.toString(Math.cos(Double.valueOf(s.pop()))));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		case "tan":
			try {
				s.push(Double.toString(Math.tan(Double.valueOf(s.pop()))));
			} catch (Exception e) {
				System.out.println("Logical Error!");
			}
			break;
		default:
			try {
				throw new Exception();

			} catch (Exception e) {
				System.out.println("Invalid Expression!");
			}
		}
	}

	public static void Deliverable(String in, String out) {
		try {
			Scanner file = new Scanner(new File(in));
			PrintWriter writer = new PrintWriter(new File(out));
			// count line to use print instead of println that will start a new
			// empty line at the end
			int line = 1;
			// get every line
			while (file.hasNext()) {
				String string = file.nextLine();
				string = InToPost(string);
				string = postfix(string);
				if (line != 1) {
					writer.println();
				}
				line++;
				writer.print(string);

			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Required Test document
		Deliverable("infix_expr_short.txt", "my_eval.txt");
		// Test documents given by TA
		// ShuntingYardAlgorithm("p1//in//input1.txt","my_eval.txt");
		// PostfixEvaluation("p2//in//input1.txt","out.txt");
		// ShuntingYardAlgorithm("p1//in//input2.txt","my_eval.txt");
		// PostfixEvaluation("p2//in//input2.txt","my_eval.txt");
		// ShuntingYardAlgorithm("p1//in//input3.txt","my_eval.txt");
		// PostfixEvaluation("p2//in//input3.txt","my_eval.txt");
		// ShuntingYardAlgorithm("p1//in//input4.txt","my_eval.txt");
		// PostfixEvaluation("p2//in//input4.txt","my_eval.txt");
		// ShuntingYardAlgorithm("p1//in//input5.txt","my_eval.txt");
		// PostfixEvaluation("p2//in//input5.txt","my_eval.txt");
	}
}
