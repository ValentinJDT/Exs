package test

import test.target.*

class Tester {

    val testClasses = buildSet {
//        add(BeanIntegrationTest::class)
//        add(BuilderTest::class)
//        add(CallTest::class)
//        add(CBackMappingTest::class)
//        add(ContainsTest::class)
//        add(DateEqTest::class)
//        add(DeferTest::class)
//        add(EnumTest::class)
//        add(EqTest::class)
//        add(ExecutorTest::class)
//        add(EditTest::class)
        add(FileTestersTest::class)
//        add(FilterColTest::class)
//        add(FlowTest::class)
//        add(ForeachTest::class)
//        add(GetSetTest::class)
//        add(HtmlTest::class)
//        add(InlineCircleCallTest::class)
//        add(IgnoredRun::class)
//        add(LinkedHashMapOrderTest::class)
//        add(MutatorTest::class)
//        add(NumberTest::class)
//        add(PathTest::class)
//        add(ProviderTest::class)
//        add(SerializationTest::class)
//        add(StrFunTest::class)
//        add(SperaTest::class)
//        add(StaticDataTest::class)
//        add(UriTest::class)
//        add(WriteFileTest::class)
    }

    init {
        for(clazz in testClasses) run {
            clazz.java.getDeclaredConstructor().newInstance()
        }
    }
}
