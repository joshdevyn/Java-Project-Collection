package JavaSimulator;

public class ClassParser {

    /**
     * Source
     */
    String[] source = new String[0];

    /**
     * Result
     */
    Class result = new Class();

    /**
     * Instruction Pointer
     */
    int instructionPointer = 0;

    /**
     * Class Index
     */
    int classIndex = 0;

    /**
     * Parse Check
     */
    boolean isParsed = false;

    public ClassParser(String[] source, String name, int classIndex) {
        this.source = source;
        this.classIndex = classIndex;
    }

    /**
     * Parses a single line. Functions will change the instructionPointer.
     *
     * @param text The line to be parsed
     */
    public void parseOne(String text){
        boolean Public=(text.startsWith("public"));
        boolean Static=(text.contains("static"));
        String endingParenthesis = "\\)";
        String startingParenthesis = "\\(";
        if (text.endsWith("){")) {

            // Function
            String[] argument=text.split(startingParenthesis);
            String argumentTwo=argument[1];
            String argumentOne=argumentTwo.split(endingParenthesis)[0];
            String[] arguments=argumentOne.split(",");
            String[] names=new String[arguments.length];
            String[] types=new String[arguments.length];

            if (arguments.length!=1){
                for (int i=0; i<names.length; i++) {
                    names[i]=arguments[i].split(" ")[0];
                }

                for (int i=0; i<names.length; i++) {
                    types[i]=arguments[i].split(" ")[1];
                }
            }
            
            
            String[] strings = new String[0];
            instructionPointer++;
            int bracketCounter=1;

            while(bracketCounter!=0){
                
                if (source[instructionPointer].startsWith("}")){
                    bracketCounter--;
                }
                if (source[instructionPointer].endsWith("{")){
                    bracketCounter++;
                }
                if (bracketCounter!=0){
                    strings=add(strings, source[instructionPointer]);
                }
                
                instructionPointer++;
                
            }
            
            String[] textSplitBySpace = text.split(startingParenthesis)[0].split(" ");
            String name = textSplitBySpace[textSplitBySpace.length-1];
            String returnType = textSplitBySpace[textSplitBySpace.length-2];
            CommandParser commandParser = new CommandParser(strings, classIndex,result.functions.length);
            result.addFunction(new Function(commandParser,name,Public,Static,types,names,returnType));
        }

        if (Variable.validDatatype(text.split(" ")[0]) && text.contains("=")){//TODO: Allow thing like "int x" not "int x=0"
            String datatype = text.split(" ")[0];
            String name = text.split("=")[0].split(" ")[1];
            String source = text.split("=")[1];

            Variable[] variables = new Variable[result.vars.length+1];

            System.arraycopy(result.vars, 0, variables, 0, variables.length-1);

            if (datatype.equals("int")){
                variables[variables.length-1] = new IntVariable(name,Integer.parseInt(source),false);
            }
            else {
                variables[variables.length-1] = new Variable(name,source,datatype,false);
            }

            result.vars = variables;
        }
        
    }

    /**
     * Parses function and variables
     */
    public void parse(){
        while(instructionPointer < source.length){
            int instructionPointer1 = instructionPointer;
            parseOne(source[instructionPointer]);
            if (instructionPointer1 == instructionPointer){
                instructionPointer++;
            }
        }
        isParsed = true;
    }
    protected String[] add(String[] string1, String string2){
        String[] strings = new String[string1.length+1];
        System.arraycopy(string1, 0, strings, 0, string1.length);
        strings[string1.length]=string2;
        return strings;
    }
}
