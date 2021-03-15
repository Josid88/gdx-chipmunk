package net.josid.gdx.chipmunk.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ChipmunkStaticConverter {

    private String className = "cpArbiter";
    private String variableName = "arb";

    private String indent = "    ";

    Map<String, String> dataTypesMap = new HashMap<>(); {
        dataTypesMap.put("cpFloat", "float");
        dataTypesMap.put("cpBool", "boolean");
    };

    Set<String> longReturnTypes = new HashSet<>(); {
        longReturnTypes.add("cpSpace*");
        longReturnTypes.add("cpBody*");
        longReturnTypes.add("cpShape*");
        longReturnTypes.add("cpConstraint*");
        longReturnTypes.add("cpPolyShape*");
    }

    public static void main(String[] args) {
        try {
            new ChipmunkStaticConverter().convert();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public void convert() throws URISyntaxException, IOException{
        Path path = Paths.get(getClass().getClassLoader().getResource("NativeFile.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        StringBuilder commentBuilder = new StringBuilder();
        //ArrayList<String> functions = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("///")) {
                commentBuilder.append(line.replace("///", "").trim());

            } else if (line.startsWith("CP_EXPORT")) {
                String function = line.replace("CP_EXPORT", "").replace(";", "").trim();

                String returnType = getReturnType(function);
                String functionName = getFunctionName(function);
                String simpleFunctionName = getSimpleFunctionName(functionName);
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

                p(indent).p("public static native ");
                if (longReturnTypes.contains(returnType)) {
                    p("long");
                } else if (returnType.equals("cpVect")){
                    p("void");
                } else {
                    p(returnType);
                }

                p(" ").p(simpleFunctionName).p("(long ").p(variableName);
                for (Parameter parameter : parameters) if (!parameter.isClass) {
                    p(", ");
                    if (parameter.dataType.equals("cpVect")) {
                        p("float ").p(parameter.variable).p("_x, float ").p(parameter.variable).p("_y");
                    } else {
                        p(parameter.dataType).p(" ").p(parameter.variable);
                    }
                }
                if (returnType.equals("cpVect")) {
                    p(", float[] out_floats");
                }
                p(")").pln("; /*");

                p(indent).p(indent);
                String postReturnType = ";";
                if (longReturnTypes.contains(returnType)) {
                    p("return (jlong) ");
                } else if (returnType.equals("cpVect")){
                    p("cpVect v = ");
                    postReturnType = ";\n" + indent + indent + "gdxCpVectToFloats(&v, out_floats);";
                } else if (!returnType.equals("void")) {
                    p("return ");
                }

                p(functionName).p("((").p(className).p("*)").p(variableName);
                for (Parameter parameter : parameters) if (!parameter.isClass) {
                    p(", ");
                    if (parameter.dataType.equals("cpVect")) {
                        p("cpv(").p(parameter.variable).p("_x, ").p(parameter.variable).p("_y)");
                    } else {
                        p(parameter.variable);
                    }
                }
                p(")").pln(postReturnType);
                p(indent).pln("*/");

                pln("");

                commentBuilder.setLength(0);

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

    private ChipmunkStaticConverter p(Object object) {
        System.out.print(object);
        return this;
    }

    private ChipmunkStaticConverter pln(Object object) {
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
