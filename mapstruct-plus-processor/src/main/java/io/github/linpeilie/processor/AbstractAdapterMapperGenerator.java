package io.github.linpeilie.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static javax.tools.Diagnostic.Kind.ERROR;

public abstract class AbstractAdapterMapperGenerator {

    public void write(ProcessingEnvironment processingEnv,
        Collection<AbstractAdapterMethodMetadata> adapterMethods,
        String adapterClassName) {
        // write Adapter
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(adapterPackage() + "." + adapterClassName)
            .openWriter()) {
            JavaFile.builder(adapterPackage(), createTypeSpec(adapterMethods, adapterClassName))
                .build()
                .writeTo(writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR, "Error while opening " + adapterClassName + " output file: " + e.getMessage());
        }
    }

    protected abstract TypeSpec createTypeSpec(Collection<AbstractAdapterMethodMetadata> adapterMethods,
        String adapterClassName);

    protected String adapterPackage() {
        return AutoMapperProperties.getAdapterPackage();
    }

    private TypeName wrapperTypeName(TypeName source) {
        if (source.isPrimitive() || source.isBoxedPrimitive()) {
            return source;
        }
        if ("java.util.Map".contentEquals(source.toString())) {
            return ParameterizedTypeName.get((ClassName) source, ClassName.get("java.lang", "String"),
                ClassName.get("java.lang", "Object"));
        }
        return source;
    }

    protected List<MethodSpec> buildProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        if (adapterMethodMetadata.needCycleAvoiding()) {
            methodSpecs.add(buildCycleAvoidingProxyMethod(adapterMethodMetadata));
        } else {
            methodSpecs.add(buildDefaultProxyMethod(adapterMethodMetadata));
        }

        return methodSpecs;
    }

    protected MethodSpec buildDefaultProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        CodeBlock targetCode = adapterMethodMetadata.isStatic()
                               ? CodeBlock.of("return $T.$N($N);", adapterMethodMetadata.getMapper(),
            adapterMethodMetadata.getMapperMethodName(), "param")
                               : proxyMethodTarget(adapterMethodMetadata);
        ParameterSpec parameterSpec =
            ParameterSpec.builder(wrapperTypeName(adapterMethodMetadata.getSource()), "param").build();
        return MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(adapterMethodMetadata.getReturn())
            .addCode(targetCode)
            .build();
    }

    protected MethodSpec buildCycleAvoidingProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        CodeBlock targetCode = adapterMethodMetadata.isStatic()
                               ? CodeBlock.of("return $T.$N($N, $N);", adapterMethodMetadata.getMapper(),
            adapterMethodMetadata.getMapperMethodName(), "param", "context")
                               : cycleAvoidingMethodTarget(adapterMethodMetadata);
        ParameterSpec parameterSpec =
            ParameterSpec.builder(wrapperTypeName(adapterMethodMetadata.getSource()), "param").build();
        ParameterSpec contextParameterSpec =
            ParameterSpec.builder(
                ClassName.get("io.github.linpeilie", "CycleAvoidingMappingContext"),
                    "context")
                .build();
        return MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .addParameter(contextParameterSpec)
            .returns(adapterMethodMetadata.getReturn())
            .addCode(targetCode)
            .build();
    }

    protected abstract CodeBlock proxyMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata);

    protected abstract CodeBlock cycleAvoidingMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata);

}
