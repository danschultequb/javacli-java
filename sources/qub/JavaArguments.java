package qub;

public interface JavaArguments<T>
{
    /**
     * Add the provided arguments to the list of arguments that will be provided to the executable
     * when this ProcessBuilder is run.
     * @param arguments The arguments to add.
     * @return This object for method chaining.
     */
    T addArguments(String... arguments);

    /**
     * Get the arguments that have been added to this object.
     * @return The arguments that have been added to this object.
     */
    Iterable<String> getArguments();

    /**
     * Add a classpath argument to the java process.
     * @param classpath The classpath argument to add to the java process.
     * @return This object for method chaining.
     */
    default T addClasspath(String classpath)
    {
        PreCondition.assertNotNullAndNotEmpty(classpath, "classpath");

        return this.addArguments("-classpath", classpath);
    }

    /**
     * Add a classpath argument to the java process.
     * @param classpath The classpath argument to add to the java process.
     * @return This object for method chaining.
     */
    default T addClasspath(Iterable<String> classpath)
    {
        PreCondition.assertNotNullAndNotEmpty(classpath, "classpath");

        return this.addClasspath(Strings.join(';', classpath));
    }

    /**
     * Add a javaagent argument to the java process.
     * @param javaAgent The javaagent argument.
     * @return This object for method chaining.
     */
    default T addJavaAgent(String javaAgent)
    {
        PreCondition.assertNotNullAndNotEmpty(javaAgent, "javaAgent");

        return this.addArguments("-javaagent:" + javaAgent);
    }

    /**
     * Add a version argument to the java process.
     * @return This object for method chaining.
     */
    default T addVersion()
    {
        return this.addVersion(VersionDestination.StandardOutput);
    }

    /**
     * Add a version argument to the java process.
     * @param versionDestination The destination stream that the version number will be printed to.
     * @return This object for method chaining.
     */
    default T addVersion(VersionDestination versionDestination)
    {
        PreCondition.assertNotNull(versionDestination, "versionDestination");

        return this.addArguments(versionDestination == VersionDestination.StandardOutput
            ? "--version"
            : "-version");
    }

    /**
     * Add a show-version argument to the java process.
     * @return This object for method chaining.
     */
    default T addShowVersion()
    {
        return this.addShowVersion(VersionDestination.StandardOutput);
    }

    /**
     * Add a show-version argument to the java process.
     * @param versionDestination The destination stream that the version number will be printed to.
     * @return This object for method chaining.
     */
    default T addShowVersion(VersionDestination versionDestination)
    {
        PreCondition.assertNotNull(versionDestination, "versionDestination");

        return this.addArguments(versionDestination == VersionDestination.StandardOutput
            ? "--show-version"
            : "-showversion");
    }
}
