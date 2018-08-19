package com.tylerkindy.github

import org.gradle.api.Project
import java.util.Properties

fun loadUserProperties(project: Project) =
  Properties().apply {
    load(project.rootProject.file("user.properties").inputStream())
  }
