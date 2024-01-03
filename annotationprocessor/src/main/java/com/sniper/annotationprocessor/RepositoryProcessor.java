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

import com.sniper.annotation.FromJson;
import com.sniper.annotation.HttpMethod;

@AutoService(Processor.class)
public class RepositoryProcessor extends AbstractProcessor implements Const {
    private Filer filer;
    private Elements elements;
    Element element;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elements = processingEnvironment.getElementUtils();
        System.out.println("init");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("processprocessprocessprocessprocess ");
        for (Element element1 : roundEnvironment.getElementsAnnotatedWith(HttpMethod.class)) {
            System.out.println("search= " + element1.getSimpleName());
            if (element1.getKind() != ElementKind.METHOD) {
                System.out.println("Can only be applied HttpMethod method.");
                return true;
            }
            this.element = element1;
            // TODO: 2/11/2022 create variable Gson
            String paramName = "json";

            //    Target method down
            //    insert param
            //    insert type mutable Live data

    /*
       1- global value mutable live data,ApiService
       2- make constructor
       3- init value in constructor
       4- make method
       5- insert param inserted in method request
       6- enqueue http method
       7- in anonymous class get type insert in http method
     **/

            ParameterSpec parameterType = ParameterSpec.builder(TypeName.get(String.class), paramName).build();

//            ParameterSpec returnValue = ParameterSpec.builder(TypeName.get(element.asType()), paramName).build();

            ModuleElement moduleElement = elements.getModuleElement("java.lang");
            TypeElement typeElement = (TypeElement) element;
            CodeBlock codeBlock = CodeBlock.builder().add("null").build();
            String varName = element.getSimpleName().toString().toLowerCase();
//            String valueToReturn = "return gson.fromJson(" + paramName + "," + element.asType() + ".class" + ")";


            // TODO: 2/11/2022 get object to return value
//            ModuleElement importGson = elements.getModuleElement("com.google.gson");
//            TypeElement gsonObject = typeElement;
//            String gsonClass = gsonObject.getSimpleName().toString();
//            String object = gsonClass.toLowerCase();

//            String gsonInit = "new " + gsonClass + "();";
//            ClassName className = ClassName.get(gsonObject)
            CodeBlock gsonObject = CodeBlock.builder()
                    .add("$T", ClassName.get(Gson.class))
                    .add(" gson")
                    .add("=")
                    .add("new ")
                    .add("Gson123")
                    .add("()")
                    .add(";")
                    .build();

            MethodSpec toJson = MethodSpec.methodBuilder("fromJson")
                    .addParameter(parameterType)
                    .addCode(gsonObject)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                    .addStatement(gsonInit+" "+gsonInit.toLowerCase() + "=" + "new "+gsonInit+"()")
//                    Gson gson = new Gson();
//                    .addStatement("", gsonClass + gsonObject + gsonInit)
//                    .addStatement(String.class.getSimpleName(),String.class," var")
//                    .addStatement(gsonObject)
//                    .endControlFlow()
                    .addStatement("\nreturn gson.fromJson(" + parameterType.name + "," + element.getSimpleName().toString() + ".class" + ")")
                    .returns(ClassName.get(typeElement))
                    .build();
//            $N getClass generator

//            public static ExpiryUser fromJsonExpiryUser(String stringJson) {
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                return gson.fromJson(stringJson, ExpiryUser.class);
//            }

//            FieldSpec singletonObject = FieldSpec.builder(ClassName.get(String.class), varName, Modifier.PRIVATE, Modifier.STATIC)
//                    .initializer(codeBlock)
//                    .build();
            //                    .addField(singletonObject)
            TypeSpec singletonClass = TypeSpec.classBuilder(className())
                    .addModifiers(Modifier.PUBLIC)
//                    .addField(singletonObject)
                    .addMethod(toJson)
                    .build();
            try {
                JavaFile.builder(packageName(), singletonClass)
                        .skipJavaLangImports(true)
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        System.out.println("end generated");
        return true;
    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        System.out.println("getCompletions");
        return super.getCompletions(element, annotation, member, userText);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(FromJson.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public String packageName() {
        return packageName + "repository";
    }

    String PREFIX = "PREFIX";

    @Override
    public String className() {
        return element.getSimpleName().toString()+"_"+PREFIX;
    }
}
