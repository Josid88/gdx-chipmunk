package net.josid.gdx.chipmunk.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChipmunkConverter {

    private String className = "cpSlideJoint";
    private String variableName = "constraint";

    private String indent = "    ";
    private String address = "nativeAddress";

    Map<String, String> dataTypesMap = new HashMap<>(); {
        dataTypesMap.put("cpFloat", "float");
        dataTypesMap.put("cpBool", "boolean");
    };

    public static void main(String[] args) {
        try {
            new ChipmunkConverter().convert();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public void convert() throws URISyntaxException, IOException{
        Path path = Paths.get(getClass().getClassLoader().getResource("NativeFile.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        StringBuilder commentBuilder = new StringBuilder();
        ArrayList<String> functions = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("///")) {
                commentBuilder.append(line.replace("///", "").trim());

            } else if (line.startsWith("CP_EXPORT")) {
                functions.add(line.replace("CP_EXPORT", "").replace(";", "").trim());

            } else {

                for (String function : functions) {
                    String returnType = getReturnType(function);
                    String functionName = getFunctionName(function);
                    String simpleFunctionName = getSimpleFunctionName(functionName);
                    String jniFunctionName = getJniFunctionName(functionName);
                    Parameter[] parameters = getParameters(function);
                    boolean isReturn = !returnType.equals("void");

                    p(indent).pln("/**");
                    p(indent).p(" * ").pln(commentBuilder);
                    if (isReturn)
                        p(indent).pln(" * @return "); // TODO variable
                    for (Parameter parameter : parameters) if (!parameter.isClass) {
                        p(indent).p(" * @param ").pln(parameter.variable);
                    }
                    p(indent).pln(" */ ");

                    p(indent).p("public ").p(returnType).p(" ").p(simpleFunctionName).p("(");
                    String separator = "";
                    for (Parameter parameter : parameters) if (!parameter.isClass) {
                        p(separator).p(parameter.dataType).p(" ").p(parameter.variable);
                        separator = ", ";
                    }
                    p(")").pln(" {");
                        p(indent).p(indent);
                        if (isReturn)
                            p("return ");
                        p(jniFunctionName).p("(").p(address);
                        for (Parameter parameter : parameters) if (!parameter.isClass) {
                            p(", ").p(parameter.variable);
                        }
                        pln(");");
                    p(indent).pln("}");

                    p(indent).p("private native ").p(returnType).p(" ").p(jniFunctionName).p("(long ").p(variableName);
                    for (Parameter parameter : parameters) if (!parameter.isClass) {
                        p(", ").p(parameter.dataType).p(" ").p(parameter.variable);
                    }
                    p(")").pln("; /*");
                    p(indent).p(indent).p(functionName).p("((").p(className).p("*)").p(variableName);
                    for (Parameter parameter : parameters) if (!parameter.isClass) {
                        p(", ").p(parameter.variable);
                    }
                    pln(");");
                    p(indent).pln("*/");

                    pln("");
                }

                commentBuilder.setLength(0);
                functions.clear();
            }
        }
    }

    private String getReturnType(String function) {
        int i = function.indexOf(' ');
        String returnType = function.substring(0, i);
        return convertDataType(returnType);
    }

    private String getFunctionName(String function) {
        int i1 = function.indexOf(' ');
        int i2 = function.indexOf('(');
        return function.substring(i1, i2).trim();
    }

    private String getSimpleFunctionName(String functionName) {
        functionName = functionName.replace(className, "");
        functionName = Character.toLowerCase(functionName.charAt(0)) + functionName.substring(1); 
        return functionName;
    }

    private String getJniFunctionName(String functionName) {
        functionName = functionName.replace(className, "");
        functionName = "jni" + functionName; 
        return functionName;
    }

    private Parameter[] getParameters(String function) {
        int i1 = function.indexOf('(') + 1;
        int i2 = function.indexOf(')');
        String[] params = function.substring(i1, i2).trim().replaceAll("const", "").replaceAll("\\*", "").split(",");
        Parameter[] parameters = new Parameter[params.length];
        for (int i = 0; i < parameters.length; i++) {
            String[] words = params[i].trim().split(" ");
            parameters[i] = new Parameter(words[0], words.length > 1 ? words[1] : "void");
        }
        return parameters;
    }

    private String convertDataType(String dataType) {
        String dataType2 = dataTypesMap.get(dataType);
        return null == dataType2 ? dataType : dataType2;
    }

    private ChipmunkConverter p(Object object) {
        System.out.print(object);
        return this;
    }

    private ChipmunkConverter pln(Object object) {
        System.out.println(object);
        return this;
    }


    class Parameter {
        boolean isClass = false;
        String dataType;
        String variable;

        public Parameter(String dataType, String variable) {
            this.isClass = className.equals(dataType);
            this.dataType = convertDataType(dataType);
            this.variable = variable;
        }
    }

}
