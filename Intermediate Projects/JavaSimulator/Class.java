package JavaSimulator;

public class Class {

    /**
     * Global variables
     */
    Variable[] variables = new Variable[0];

    /**
     * Functions
     */
    Function[] functions = new Function[0];
    public Class(){}

    public Class(ClassParser classParser){
        if (classParser.parsed){
            classParser.parse();
        }
        functions = classParser.result.functions;
        variables = classParser.result.vars;
    }

    /**
     * Call function with provided name
     * 
     * Calls a function with a provided name.
     * If multiple functions have the same name they are all run.
     * If there exists no such functions with that name then nothing is run.
     *
     * @param nameOfFunction
     */
    public void runFunction(String nameOfFunction){
        for (int i=0; i < functions.length; i++){
            if (functions[i].Name.equals(nameOfFunction)){
                functions[i].run();
            }
        }
    }

    /**
     * Checks if function exists in the class
     *
     * @param nameOfFunction The function name to be checked
     * @return Existence of function
     */
    public boolean functionExists(String nameOfFunction){
        for (int i=0; i < functions.length; i++){
            if (functions[i].Name.equals(nameOfFunction)){
                return true;
            }
        }
        return false;
    }
    /**
     * Add function to the list
     * 
     * @param function Function being added
     */
    public void addFunction(Function function){
        Function[] functions = new Function[this.functions.length+1];
        System.arraycopy(this.functions, 0, functions, 0, this.functions.length);
        functions[functions.length-1]=function;
        this.functions =functions;
    }

    /**
     * Gives index of provided function
     *
     * @param functionName Function name being provided
     * @return The index of the variables
     */
    public int functionIndex(String functionName){
        for (int i = 0; i < variables.length; i++){
            if (variables[i] != null){
                if (variables[i].name.equals(functionName)){
                    return i;
                }
            }
        }
        return -1;
    }
}
