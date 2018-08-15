package com.tylerkindy.github

import org.gradle.api.Project
import java.util.*

fun loadUserProperties(project: Project) =
        Properties().apply {
            load(project.rootProject.file("user.properties").inputStream())
        }
