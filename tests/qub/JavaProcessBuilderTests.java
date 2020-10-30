package qub;

public interface JavaProcessBuilderTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(JavaProcessBuilder.class, () ->
        {
            JavaArgumentsTests.test(runner, (Test test) ->
            {
                return JavaProcessBuilder.create(test.getProcess()).await();
            });

            runner.testGroup("create(Process)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> JavaProcessBuilder.create((Process)null),
                        new PreConditionFailure("process cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JavaProcessBuilder java = JavaProcessBuilder.create(test.getProcess()).await();
                    test.assertNotNull(java);
                    test.assertEqual("java", java.getExecutablePath().toString());
                    test.assertEqual(Iterable.create(), java.getArguments());
                    test.assertEqual(test.getProcess().getCurrentFolderPath(), java.getWorkingFolderPath());
                });
            });

            runner.testGroup("create(ProcessFactory)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> JavaProcessBuilder.create((ProcessFactory)null),
                        new PreConditionFailure("processFactory cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JavaProcessBuilder java = JavaProcessBuilder.create(test.getProcess().getProcessFactory()).await();
                    test.assertNotNull(java);
                    test.assertEqual("java", java.getExecutablePath().toString());
                    test.assertEqual(Iterable.create(), java.getArguments());
                    test.assertEqual(test.getProcess().getCurrentFolderPath(), java.getWorkingFolderPath());
                });
            });
        });
    }
}
