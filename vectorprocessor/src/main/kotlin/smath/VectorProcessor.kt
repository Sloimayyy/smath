package com.sloimay.smath

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import java.io.OutputStream

// 1. Define our annotation
class GenVecAnnotation(val processingEnv: SymbolProcessorEnvironment) : SymbolProcessor {
    companion object {
        val VECTOR_ANNOTATION = "com.sloimay.smath.annotations.GenVecOps"
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        //processingEnv.logger.info("KSP PROCESSOR IS RUNNING")
        // Find all classes with our annotation
        //processingEnv.logger.warn("${resolver.getAllFiles().toList().first().location}")
        val symbols = resolver.getSymbolsWithAnnotation(VECTOR_ANNOTATION)
        //processingEnv.logger.warn("${symbols.toList()}")
        val unprocessed = symbols.filterNot { it.validate() }.toList()

        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(VecClassVisitor(processingEnv.codeGenerator), Unit) }

        return unprocessed
    }

    // Visitor to process each annotated class
    class VecClassVisitor(val codeGen: CodeGenerator) : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            // Get the vector details
            val className = classDeclaration.simpleName.asString()
            val packageName = classDeclaration.packageName.asString()

            // Get annotation data
            val annotation = classDeclaration.annotations.first {
                it.annotationType.resolve().declaration.qualifiedName?.asString() == VECTOR_ANNOTATION
            }
            val compType = annotation.arguments.first { it.name?.asString() == "compType" }
                .value as String

            // Get dimension from property count
            val props = classDeclaration.getAllProperties().toList()
            val dims = props.size
            val compNames = props.map { it.simpleName.asString() }

            // Generate the extension functions file
            genVecExtensions(
                codeGen,
                packageName,
                className,
                compType,
                dims,
                compNames
            )
        }

        private fun genVecExtensions(
            codeGen: CodeGenerator,
            packageName: String,
            className: String,
            compType: String,
            dims: Int,
            compNames: List<String>
        ) {
            // Create file
            //val file = ClassName(packageName, "${className}Extensions")
            val outputStream = codeGen.createNewFile(
                dependencies = Dependencies(false),
                packageName = packageName,
                fileName = "${className}Extensions"
            )

            // Generate code
            genVecCode(
                outputStream,
                packageName,
                className,
                compType,
                dims,
                compNames
            )

            outputStream.close()
        }

        private fun genVecCode(
            outputStream: OutputStream,
            packageName: String,
            className: String,
            compType: String,
            dims: Int,
            compNames: List<String>
        ) {
            val code = StringBuilder()

            val genContext = GenContext(code, packageName, className, compType, dims, compNames)

            code.append("package $packageName\n\n")
            code.append("// Auto-generated extensions for $className\n\n")



            genConstants(genContext)

            //genOperators(genContext)





            // Write to file
            outputStream.write(code.toString().toByteArray())
        }
    }
}

// 2. Provider for our processor
class VectorProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return GenVecAnnotation(environment)
    }
}