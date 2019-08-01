package com.kc.gradle

import com.android.build.api.transform.Context
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider;
import org.gradle.api.*;

class MyTransform extends Transform {
    Project project

    MyTransform(Project project) {
        this.project = project
    }

    @Override
    String getName() {
        return "MyTransform"
    }
    //设置输入类型，我们是针对class文件处理
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }
    //设置输入范围，我们选择整个项目
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return true
    }
    //重点就是该方法，我们需要将修改字节码的逻辑就从这里开始
    @Override
    void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        inputs.each {
            TransformInput input ->
                input.getJarInputs().each {
                    //处理jar文件，代码太多，这里暂时不贴
                }
                input.getDirectoryInputs().each {
                    //处理目录文件，这里的ASMHelper.transformClass()是修改字节码逻辑
                    def destDir = transformInvocation.outputProvider.getContentLocation(
                            "${dir.name}_transformed",
                            dir.contentTypes,
                            dir.scopes,
                            Format.DIRECTORY)
                    if (dir.file) {
                        def modifiedRecord = [:]
                        dir.file.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) {
                            File classFile ->
                                def className = classFile.absolutePath.replace(dir.getFile().getAbsolutePath(), "")
                                if (!ASMHelper.filter(className)) {
                                    def transformedClass = ASMHelper.transformClass(classFile, dir.file, transformInvocation.context.temporaryDir)
                                    modifiedRecord[(className)] = transformedClass
                                }
                        }
                        FileUtils.copyDirectory(dir.file, destDir)
                        modifiedRecord.each { name, file ->
                            def targetFile = new File(destDir.absolutePath, name)
                            if (targetFile.exists()) {
                                targetFile.delete()
                            }
                            FileUtils.copyFile(file, targetFile)
                        }
                        modifiedRecord.clear()
                    }
                }
        }
    }
}