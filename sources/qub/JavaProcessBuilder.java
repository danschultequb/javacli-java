package qub;

public class JavaProcessBuilder extends ProcessBuilderDecorator<JavaProcessBuilder> implements JavaArguments<JavaProcessBuilder>
{
    public static final String executablePathString = "java";
    public static final Path executablePath = Path.parse(JavaProcessBuilder.executablePathString);

    protected JavaProcessBuilder(ProcessBuilder processBuilder)
    {
        super(processBuilder);
    }

    /**
     * Create a JavaProcessBuilder from the provided Process.
     * @param process The DesktopProcess to get the JavaProcessBuilder from.
     * @return The JavaProcessBuilder.
     */
    public static Result<? extends JavaProcessBuilder> create(DesktopProcess process)
    {
        PreCondition.assertNotNull(process, "process");

        return JavaProcessBuilder.create(process.getProcessFactory());
    }

    /**
     * Create a JavaProcessBuilder from the provided ProcessFactory.
     * @param processFactory The ProcessFactory to get the JavaProcessBuilder from.
     * @return The JavaProcessBuilder.
     */
    public static Result<? extends JavaProcessBuilder> create(ProcessFactory processFactory)
    {
        PreCondition.assertNotNull(processFactory, "processFactory");

        return Result.create(() ->
        {
            return new JavaProcessBuilder(processFactory.getProcessBuilder(JavaProcessBuilder.executablePath).await());
        });
    }
}
