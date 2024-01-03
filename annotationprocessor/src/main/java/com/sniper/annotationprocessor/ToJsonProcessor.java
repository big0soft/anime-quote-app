package com.sniper.annotationprocessor;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.sniper.annotation.ToJson;


@AutoService(Processor.class)
public class ToJsonProcessor extends AbstractProcessor implements Const {
    private Filer filer;
    private Elements elements;
    Element element;
    private final String PREFIX = packageName;
    private final String SINGLETON_CLASS = "_ConvertToJson";
    private TypeSpec singletonClass;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elements = processingEnvironment.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element1 : roundEnvironment.getElementsAnnotatedWith(ToJson.class)) {
            if (element1.getKind() != ElementKind.CLASS) {
                System.out.println("Can only be applied toJson class.");
                return true;
            }
            this.element = element1;
            // singleton object
//            FieldSpec singletonObject = FieldSpec.builder(
//                    ClassName.get(typeElement),
//                    varName,
//                    Modifier.PRIVATE,
//                    Modifier.STATIC)
//                    .initializer(codeBlock)
//                    .build();

            // TODO: 2/11/2022 create variable Gson
//            ParameterSpec variableString = ParameterSpec.builder(TypeName.get(Gson.class), "test_string").build();
            ModuleElement moduleElement1 = elements.getModuleElement(Gson.class.getPackageName());
//            TypeElement string = elements.getTypeElement(moduleElement, "String");
//            TypeElement typeElement1 = elements.getTypeElement();
//            CodeBlock gsonInit = CodeBlock.builder()
//                    .add("Gson")
//                    .add(" gson")
//                    .add("=")
//                    .add("new Gson()")
////                    .add("null")
//                    .build();
            String paramName = "response";
            String varName1 = element.getSimpleName().toString().toLowerCase();
            ClassName gsonInit = ClassName.get(Gson.class);


            ParameterSpec parameterString = ParameterSpec.builder(TypeName.get(element.asType()), paramName).build();
            ParameterSpec returnValue = ParameterSpec.builder(TypeName.get(String.class), "test_string").build();
            ModuleElement moduleElement = elements.getModuleElement("java.lang");
//            TypeElement string = elements.getTypeElement(moduleElement, "String");
            TypeElement typeElement = (TypeElement) element;
            CodeBlock codeBlock = CodeBlock.builder().add("null").build();
            String varName = element.getSimpleName().toString().toLowerCase();
            CodeBlock gsonObject = CodeBlock.builder()
                    .add("$T",ClassName.get(Gson.class))
                    .add(" gson")
                    .add("=")
                    .add("new ")
                    .add("Gson")
                    .add("()")
                    .build();
            MethodSpec toJson = MethodSpec.methodBuilder("toJson")
//                    .addParameter(ClassName.get("java.lang",Integer.class.getSimpleName().replace(".","asd"),""),"varasdasd")
                    .addParameter(parameterString)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                    .returns(ClassName.get(typeElement))
//                    .returns(ClassName.get("java.lang",String.class.getSimpleName().replace(".","asd"),""))

//                    .beginControlFlow("if ($N == null)", varName)
//                    .addStatement("$N = new $T()", varName, ClassName.get(typeElement))
//                    .endControlFlow()
                    .addStatement(gsonObject)
//                    .addStatement(gsonInit)
                    .addStatement("return gson.toJson("+parameterString.name+")")
                    .returns(returnValue.type)
//                    .addStatement("return $N", varName)
                    .build();

//            $N getClass generator

            singletonClass = TypeSpec.classBuilder(element.getSimpleName().toString() + SINGLETON_CLASS)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(toJson)
//                    .addField(singletonObject)
                    .build();
//            // private constructor
//          public static String toJson(ServerResponse response) {
//        Gson gson = new Gson();
//        return gson.toJson(response);
//           }
//
//
//
//            // singleton object
//            TypeElement typeElement = (TypeElement) element;
//            CodeBlock codeBlock = CodeBlock.builder().add("null").build();
//            String varName = element.getSimpleName().toString().toLowerCase();
//
//
//
//
//
//
//
//
//
//            // singleton method
//            MethodSpec toJson = MethodSpec.methodBuilder("getInstance")
//                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                    .returns(ClassName.get(typeElement))
//                    .beginControlFlow("if ($N == null)", varName)
//                    .addStatement("$N = new $T()", varName, ClassName.get(typeElement))
//                    .endControlFlow()
//                    .addStatement("return $N", varName)
//                    .build();
//
//            // singleton class
//
//
//
//
//
//
//
//
//
//            // write class
            try {
                JavaFile.builder(packageName(), singletonClass)
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return true;
    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        return super.getCompletions(element, annotation, member, userText);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(ToJson.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public String packageName() {
        return PREFIX + "converter";
    }

    @Override
    public String className() {
        return element.getSimpleName().toString();
    }
}
