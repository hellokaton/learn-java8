package io.github.biezhi.java8.growing.jdk6;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 使用Compiler API
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class CompilerAPI {

    public static void main(String[] args) throws Exception {
        String program = "" +
                "public class LearnJava6 {\n" +
                "  public static void main(String[] args) {\n" +
                "    System.out.println(\"欢迎你学习跟上 Java 8 之 CompilerAPI!\");\n" +
                "  }\n" +
                "}\n";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        JavaFileObject compilationUnit =
                new StringJavaFileObject("LearnJava6", program);

        SimpleJavaFileManager fileManager =
                new SimpleJavaFileManager(compiler.getStandardFileManager(null, null, null));

        JavaCompiler.CompilationTask compilationTask = compiler.getTask(
                null, fileManager, null, null, null, Arrays.asList(compilationUnit));

        compilationTask.call();

        CompiledClassLoader classLoader =
                new CompiledClassLoader(fileManager.getGeneratedOutputFiles());

        Class<?> codeGenTest = classLoader.loadClass("LearnJava6");
        Method   main        = codeGenTest.getMethod("main", String[].class);
        main.invoke(null, new Object[]{null});
    }

    private static class StringJavaFileObject extends SimpleJavaFileObject {
        private final String code;

        public StringJavaFileObject(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension),
                    Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }
    }

    private static class ClassJavaFileObject extends SimpleJavaFileObject {
        private final ByteArrayOutputStream outputStream;
        private final String                className;

        protected ClassJavaFileObject(String className, Kind kind) {
            super(URI.create("mem:///" + className.replace('.', '/') + kind.extension), kind);
            this.className = className;
            outputStream = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return outputStream;
        }

        public byte[] getBytes() {
            return outputStream.toByteArray();
        }

        public String getClassName() {
            return className;
        }
    }

    private static class SimpleJavaFileManager extends ForwardingJavaFileManager {
        private final List<ClassJavaFileObject> outputFiles;

        protected SimpleJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);
            outputFiles = new ArrayList<ClassJavaFileObject>();
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            ClassJavaFileObject file = new ClassJavaFileObject(className, kind);
            outputFiles.add(file);
            return file;
        }

        public List<ClassJavaFileObject> getGeneratedOutputFiles() {
            return outputFiles;
        }
    }

    private static class CompiledClassLoader extends ClassLoader {
        private final List<ClassJavaFileObject> files;

        private CompiledClassLoader(List<ClassJavaFileObject> files) {
            this.files = files;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            Iterator<ClassJavaFileObject> itr = files.iterator();
            while (itr.hasNext()) {
                ClassJavaFileObject file = itr.next();
                if (file.getClassName().equals(name)) {
                    itr.remove();
                    byte[] bytes = file.getBytes();
                    return super.defineClass(name, bytes, 0, bytes.length);
                }
            }
            return super.findClass(name);
        }
    }
}