package qub;

public interface JavaProcessBuilderTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(JavaProcessBuilder.class, () ->
        {
            JavaArgumentsTests.test(runner, (Test test) ->
            {
                return JavaProcessBuilder.create(FakeDesktopProcess.create()).await();
            });

            runner.testGroup("create(RealDesktopProcess)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> JavaProcessBuilder.create((DesktopProcess)null),
                        new PreConditionFailure("process cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JavaProcessBuilder java = JavaProcessBuilder.create(FakeDesktopProcess.create()).await();
                    test.assertNotNull(java);
                    test.assertEqual("java", java.getExecutablePath().toString());
                    test.assertEqual(Iterable.create(), java.getArguments());
                    test.assertEqual(Path.parse("/"), java.getWorkingFolderPath());
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
                    final JavaProcessBuilder java = JavaProcessBuilder.create((FakeDesktopProcess.create()).getProcessFactory()).await();
                    test.assertNotNull(java);
                    test.assertEqual("java", java.getExecutablePath().toString());
                    test.assertEqual(Iterable.create(), java.getArguments());
                    test.assertEqual(Path.parse("/"), java.getWorkingFolderPath());
                });
            });
        });
    }
}
