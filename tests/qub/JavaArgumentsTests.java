package qub;

public interface JavaArgumentsTests
{
    static <T extends JavaArguments<T>> void test(TestRunner runner, Function1<Test,T> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(JavaArguments.class, () ->
        {
            runner.testGroup("addArguments(String...)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addArguments((String[])null),
                        new PreConditionFailure("arguments cannot be null."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with no values", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addArgumentsResult = javaArguments.addArguments();
                    test.assertSame(javaArguments, addArgumentsResult);
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with one value", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addArgumentsResult = javaArguments.addArguments("--hello");
                    test.assertSame(javaArguments, addArgumentsResult);
                    test.assertEqual(Iterable.create("--hello"), javaArguments.getArguments());
                });

                runner.test("with two values", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addArgumentsResult = javaArguments.addArguments("--hello", "--there");
                    test.assertSame(javaArguments, addArgumentsResult);
                    test.assertEqual(Iterable.create("--hello", "--there"), javaArguments.getArguments());
                });
            });

            runner.testGroup("addClasspath(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addClasspath((String)null),
                        new PreConditionFailure("classpath cannot be null."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addClasspath(""),
                        new PreConditionFailure("classpath cannot be empty."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addClasspathResult = javaArguments.addClasspath("hello");
                    test.assertSame(javaArguments, addClasspathResult);
                    test.assertEqual(Iterable.create("-classpath", "hello"), javaArguments.getArguments());
                });
            });

            runner.testGroup("addClasspath(Iterable<String>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addClasspath((Iterable<String>)null),
                        new PreConditionFailure("classpath cannot be null."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addClasspath(Iterable.create()),
                        new PreConditionFailure("classpath cannot be empty."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with one value", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addClasspathResult = javaArguments.addClasspath(Iterable.create("hello"));
                    test.assertSame(javaArguments, addClasspathResult);
                    test.assertEqual(Iterable.create("-classpath", "hello"), javaArguments.getArguments());
                });

                runner.test("with two values", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addClasspathResult = javaArguments.addClasspath(Iterable.create("hello", "there"));
                    test.assertSame(javaArguments, addClasspathResult);
                    test.assertEqual(Iterable.create("-classpath", "hello;there"), javaArguments.getArguments());
                });

                runner.test("with three values", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addClasspathResult = javaArguments.addClasspath(Iterable.create("hello", "there", "again"));
                    test.assertSame(javaArguments, addClasspathResult);
                    test.assertEqual(Iterable.create("-classpath", "hello;there;again"), javaArguments.getArguments());
                });
            });

            runner.testGroup("addJavaAgent()", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addJavaAgent(null),
                        new PreConditionFailure("javaAgent cannot be null."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addJavaAgent(""),
                        new PreConditionFailure("javaAgent cannot be empty."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addJavaAgentResult = javaArguments.addJavaAgent("hello");
                    test.assertSame(javaArguments, addJavaAgentResult);
                    test.assertEqual(Iterable.create("-javaagent:hello"), javaArguments.getArguments());
                });
            });

            runner.test("addVersion()", (Test test) ->
            {
                final T javaArguments = creator.run(test);
                final T addVersionResult = javaArguments.addVersion();
                test.assertSame(javaArguments, addVersionResult);
                test.assertEqual(Iterable.create("--version"), javaArguments.getArguments());
            });

            runner.testGroup("addVersion(VersionDestination)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addVersion(null),
                        new PreConditionFailure("versionDestination cannot be null."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with " + VersionDestination.StandardOutput, (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addVersionResult = javaArguments.addVersion(VersionDestination.StandardOutput);
                    test.assertSame(javaArguments, addVersionResult);
                    test.assertEqual(Iterable.create("--version"), javaArguments.getArguments());
                });

                runner.test("with " + VersionDestination.StandardError, (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addVersionResult = javaArguments.addVersion(VersionDestination.StandardError);
                    test.assertSame(javaArguments, addVersionResult);
                    test.assertEqual(Iterable.create("-version"), javaArguments.getArguments());
                });
            });

            runner.test("addShowVersion()", (Test test) ->
            {
                final T javaArguments = creator.run(test);
                final T addShowVersionResult = javaArguments.addShowVersion();
                test.assertSame(javaArguments, addShowVersionResult);
                test.assertEqual(Iterable.create("--show-version"), javaArguments.getArguments());
            });

            runner.testGroup("addShowVersion(VersionDestination)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    test.assertThrows(() -> javaArguments.addShowVersion(null),
                        new PreConditionFailure("versionDestination cannot be null."));
                    test.assertEqual(Iterable.create(), javaArguments.getArguments());
                });

                runner.test("with " + VersionDestination.StandardOutput, (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addShowVersionResult = javaArguments.addShowVersion(VersionDestination.StandardOutput);
                    test.assertSame(javaArguments, addShowVersionResult);
                    test.assertEqual(Iterable.create("--show-version"), javaArguments.getArguments());
                });

                runner.test("with " + VersionDestination.StandardError, (Test test) ->
                {
                    final T javaArguments = creator.run(test);
                    final T addShowVersionResult = javaArguments.addShowVersion(VersionDestination.StandardError);
                    test.assertSame(javaArguments, addShowVersionResult);
                    test.assertEqual(Iterable.create("-showversion"), javaArguments.getArguments());
                });
            });
        });
    }
}
