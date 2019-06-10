package com.kc.gradle;

import org.gradle.api.*;


class MyCustomPluginExtension {
	def message = "From MyCustomPluginExtention"
	def sender = "MyCustomPluin"
}

class MyCustomPlugin implements Plugin<Project> {
	void apply(Project project) {
		project.task('myTask') << {
			println "Hi this is micky's plugin 2"
		}

		project.task('customTask', type:MyCustomTask)


		project.extensions.create('myArgs', MyCustomPluginExtension)
		project.task('customTask2', type:MyCustomTask2)
	}
}
