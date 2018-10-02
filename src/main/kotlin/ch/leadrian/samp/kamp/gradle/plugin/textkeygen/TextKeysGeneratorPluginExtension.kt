package ch.leadrian.samp.kamp.gradle.plugin.textkeygen

import java.util.*

open class TextKeysGeneratorPluginExtension {

    var outputDirectory: Any? = null
    var resourcesDirectory: Any? = null
    var packageNames: Set<String> = HashSet()
}