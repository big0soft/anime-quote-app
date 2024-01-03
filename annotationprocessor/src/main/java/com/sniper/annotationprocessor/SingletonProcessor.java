package com.sniper.annotationprocessor;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.sniper.annotation.Singleton;


@AutoService(Processor.class)
public class SingletonProcessor extends AbstractProcessor implements Const {
    private Filer filer;
    private Elements elements;
    private Element element;
    private final String PREFIX = packageName;
    private final String SINGLETON_CLASS = "_Singleton";
    private TypeSpec singletonClass;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elements = processingEnvironment.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Singleton.class)) {
            this.element = element;
            if (element.getKind() != ElementKind.CLASS) {
                System.out.println("Can only be applied to class.");
                return true;
            }

            // private constructor
            MethodSpec constructor = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PRIVATE)
                    .build();

            // singleton object
            TypeElement typeElement = (TypeElement) element;
            CodeBlock codeBlock = CodeBlock.builder().add("null").build();
            String varName = element.getSimpleName().toString().toLowerCase();

            FieldSpec singletonObject = FieldSpec.builder(
                    ClassName.get(typeElement),
                    varName,
                    Modifier.PRIVATE,
                    Modifier.STATIC)
                    .initializer(codeBlock)
                    .build();

            // singleton method
            MethodSpec getInstanceMethod = MethodSpec.methodBuilder("getInstance")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(ClassName.get(typeElement))
                    .beginControlFlow("if ($N == null)", varName)
                    .addStatement("$N = new $T()", varName, ClassName.get(typeElement))
                    .endControlFlow()
                    .addStatement("return $N", varName)
                    .build();

            // singleton class

            singletonClass = TypeSpec.classBuilder(element.getSimpleName().toString() + SINGLETON_CLASS)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructor)
                    .addMethod(getInstanceMethod)
                    .addField(singletonObject)
                    .build();


            // write class
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
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(Singleton.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public String packageName() {
        return PREFIX + singletonClass.name.toLowerCase();
    }

    @Override
    public String className() {
        return null;
    }


}