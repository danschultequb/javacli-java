package qub;

public interface JavaProcessBuilderDecoratorTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(JavaProcessBuilderDecorator.class, () ->
        {
            JavaArgumentsTests.test(runner, JavaProcessBuilderDecoratorTests::create);

            runner.testGroup("addArgument(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    test.assertThrows(() -> java.addArgument(null),
                        new PreConditionFailure("argument cannot be null."));
                    test.assertEqual(Iterable.create(), java.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    test.assertThrows(() -> java.addArgument(""),
                        new PreConditionFailure("argument cannot be empty."));
                    test.assertEqual(Iterable.create(), java.getArguments());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    final JavaProcessBuilder addArgumentResult = java.addArgument("hello");
                    test.assertSame(java, addArgumentResult);
                    test.assertEqual(Iterable.create("hello"), java.getArguments());
                });
            });

            runner.testGroup("addArguments(Iterable<String>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    test.assertThrows(() -> java.addArguments((Iterable<String>)null),
                        new PreConditionFailure("arguments cannot be null."));
                    test.assertEqual(Iterable.create(), java.getArguments());
                });

                runner.test("with no values", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    final JavaProcessBuilder addArgumentsResult = java.addArguments(Iterable.create());
                    test.assertSame(java, addArgumentsResult);
                    test.assertEqual(Iterable.create(), java.getArguments());
                });

                runner.test("with one value", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    final JavaProcessBuilder addArgumentsResult = java.addArguments(Iterable.create("--hello"));
                    test.assertSame(java, addArgumentsResult);
                    test.assertEqual(Iterable.create("--hello"), java.getArguments());
                });

                runner.test("with two values", (Test test) ->
                {
                    final JavaProcessBuilderDecorator<JavaProcessBuilder> java = JavaProcessBuilderDecoratorTests.create(test);
                    final JavaProcessBuilder addArgumentsResult = java.addArguments(Iterable.create("--hello", "--there"));
                    test.assertSame(java, addArgumentsResult);
                    test.assertEqual(Iterable.create("--hello", "--there"), java.getArguments());
                });
            });
        });
    }

    static JavaProcessBuilderDecorator<JavaProcessBuilder> create(Test test)
    {
        final JavaProcessBuilder java = JavaProcessBuilder.create(test.getProcess()).await();
        return new JavaProcessBuilderDecorator<>(java);
    }
}
