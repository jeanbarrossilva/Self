import java.net.URI
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

/**
 * Adds the repository in which the
 * [Aurelius design system](https://github.com/jeanbarrossilva/Aurelius) is located.
 *
 * @param project [Project] to which the repository is being added.
 **/
fun RepositoryHandler.aurelius(project: Project): MavenArtifactRepository {
    return maven {
        url = URI.create("https://maven.pkg.github.com/jeanbarrossilva/Aurelius")

        credentials {
            with(localProperties(project.rootDir)) {
                username = getProperty("github.username") ?: System.getenv("GITHUB_USERNAME")
                password = getProperty("github.token") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
