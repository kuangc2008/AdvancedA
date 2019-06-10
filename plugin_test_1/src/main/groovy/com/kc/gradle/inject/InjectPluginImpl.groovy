package com.kc.gradle.inject

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class InjectPluginImpl implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println ":applied LazierTracker"
        project.extensions.create('codelessConfig', InjectPluginParams)


        registerTransform(project)
        initDir(project);
        project.afterEvaluate {
            com.kc.gradle.inject.utils.Log.setQuiet(project.codelessConfig.keepQuiet);
            com.kc.gradle.inject.utils.Log.setShowHelp(project.codelessConfig.showHelp);
            com.kc.gradle.inject.utils.Log.logHelp();
            if (project.codelessConfig.watchTimeConsume) {
                com.kc.gradle.inject.utils.Log.info "watchTimeConsume enabled"
                project.gradle.addListener(new TimeListener())
            } else {
                com.kc.gradle.inject.utils.Log.info "watchTimeConsume disabled"
            }
        }
    }

    def static registerTransform(Project project) {
//        def isApp = project.plugins.hasPlugin("com.android.application")
        BaseExtension android = project.extensions.getByType(BaseExtension)
        InjectTransform transform = new InjectTransform(project)
        android.registerTransform(transform)
    }

    static void initDir(Project project) {
        File pluginTmpDir = new File(project.buildDir, 'LazierTracker')
        if (!pluginTmpDir.exists()) {
            pluginTmpDir.mkdir()
        }
        com.kc.gradle.inject.utils.DataHelper.ext.pluginTmpDir = pluginTmpDir
    }
}
