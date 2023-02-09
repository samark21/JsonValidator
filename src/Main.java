import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String mystr = "{\"name\":\"John\", \"age\":30, \"car\":null}";
        String mystr2 = "{\"glossary\":{\"title\":\"example glossary\",\"GlossDiv\":{\"title\":\"S\",\"GlossList\":{\"GlossEntry\":{\"ID\": \"SGML\",\"SortAs\": \"SGML\",\"GlossTerm\":\"Standard Generalized Markup Language\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO 8879:1986\",\"GlossDef\":{\"para\":\"A meta-markup language, used to create markup languages such as DocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"GlossSee\":\"markup\"}}}}}";
        String mystr3 = "{\"menu\": {\n" +
                "  \"id\": \"file\",\n" +
                "  \"value\": \"File\",\n" +
                "  \"popup\": {\n" +
                "    \"menuitem\": [\n" +
                "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
                "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
                "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
                "    ]\n" +
                "  }\n" +
                "}}";
        String mystr4 = "{\"widget\": {\n" +
                "    \"debug\": \"on\",\n" +
                "    \"window\": {\n" +
                "        \"title\": \"Sample Konfabulator Widget\",\n" +
                "        \"name\": \"main_window\",\n" +
                "        \"width\": 500,\n" +
                "        \"height\": 500\n" +
                "    },\n" +
                "    \"image\": { \n" +
                "        \"src\": \"Images/Sun.png\",\n" +
                "        \"name\": \"sun1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 250,\n" +
                "        \"alignment\": \"center\"\n" +
                "    },\n" +
                "    \"text\": {\n" +
                "        \"data\": \"Click Here\",\n" +
                "        \"size\": 36,\n" +
                "        \"style\": \"bold\",\n" +
                "        \"name\": \"text1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 100,\n" +
                "        \"alignment\": \"center\",\n" +
                "        \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
                "    }\n" +
                "}} ";
        System.out.println(isValid(mystr));
        System.out.println(isValid(mystr2));
        System.out.println(isValid(mystr3));
        System.out.println(isValid(mystr4));


    }


    public static Boolean isValid(String myStr) {
        Stack<Character> validityStack = new Stack<>();
        for (int i = 0; i < myStr.length(); i++) {
            if (myStr.charAt(i) == '{' || myStr.charAt(i) == '}' || myStr.charAt(i) == ':' || myStr.charAt(i) == '"' ||
                    myStr.charAt(i) == ',' || myStr.charAt(i) == '[' || myStr.charAt(i) == ']') {
                if (myStr.charAt(i) == '{') {
                    validityStack.push(myStr.charAt(i));
                } else if (!validityStack.empty()) {
                    if (myStr.charAt(i) == ':') {
                        if (validityStack.peek() == '"');
                        else{
                            validityStack.push(myStr.charAt(i));
                        }
                    } else if (myStr.charAt(i) == '\"') {
                        if (validityStack.peek() == '\"') {
                            validityStack.pop();
                        } else {
                            validityStack.push(myStr.charAt(i));
                        }
                    } else if (myStr.charAt(i) == ',') {
                        if (validityStack.peek() == ':') {
                            validityStack.pop();
                        } else if (validityStack.peek() == '[');
                        else if (validityStack.peek() == '"');
                        else {
                            System.out.println("here in ,");
                            return false;
                        }
                    } else if (myStr.charAt(i) == '[') {
                        if (validityStack.peek() == ':') {
                            validityStack.push(myStr.charAt(i));
                        } else {
                            System.out.println("here in [");
                            return false;
                        }
                    } else if (myStr.charAt(i) == ']') {
                        if (validityStack.peek() == '[') {
                            validityStack.pop();
                        } else {
                            System.out.println("here in ]");
                            return false;
                        }
                    } else if (myStr.charAt(i) == '}') {
                        if (validityStack.peek() == '{') {
                            validityStack.pop();
                        } else if (validityStack.peek() == ':') {
                            validityStack.pop();
                            validityStack.pop();

                        } else {
                            System.out.println("here in }");
                            return false;
                        }


                    }
                }
            }

        }

        return validityStack.empty();

    }

/**
 * JSON syntax rules:
 * begins with { and ends with }
 * if there is a new object, the attribute opens { } and fills it with its own attributes.
 * every attribute name is surrounded by "" and followed by :
 * if the value is a string use "" otherwise numbers or boolean don't need them.
 * a comma , separates between each attribute pair (name:value)
 * [] indicates an array.
 * <p>
 * example:
 * <p>
 * {"menu": {
 * "id": "file",
 * "value": "File",
 * "popup": {
 * "menuitem": [
 * {"value": "New", "onclick": "CreateNewDoc()"},
 * {"value": "Open", "onclick": "OpenDoc()"},
 * {"value": "Close", "onclick": "CloseDoc()"}
 * ]
 * }
 * }}
 */
}
