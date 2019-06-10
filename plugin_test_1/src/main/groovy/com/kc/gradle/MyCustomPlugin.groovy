package com.kc.gradle;

import org.gradle.api.*;

class MyCustomPlugin implements Plugin<Project> {
	void apply(Project project) {
		project.task('myTask') << {
			println "Hi this is micky's plugin 2"
		}

		project.task('customTask', type:MyCustomTask)
	}
}
