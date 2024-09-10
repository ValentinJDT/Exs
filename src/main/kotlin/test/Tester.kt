package test

import test.target.*

class Tester {

    val testClasses = buildSet {
//        add(EqTest::class)
//        add(PathTest::class)
//        add(BuilderTest::class)
//        add(ExecutorTest::class)
//        add(ExTest::class)
//        add(StrFunTest::class)
//        add(ProviderTest::class)
//        add(HtmlTest::class)
//        add(DateEqTest::class)
//        add(GetSetTest::class)
//        add(SperaTest::class)
//        add(CallTest::class)
//        add(EnumTest::class)
//        add(LinkedHashMapOrderTest::class)
//        add(DeferTest::class)
//        add(InlineCircleCallTest::class)
//        add(CBackMappingTest::class)
//        add(IgnoredRun::class)
//        add(ContainsTest::class)
        add(ForeachTest::class)
//        add(WriteFileTest::class)
//        add(StaticDataTest::class)
//        add(FilterColTest::class)
//        add(FlowTest::class)
//        add(FileTestersTest::class)
//        add(UriTest::class)
//        add(GetSetTest::class)
//        add(BeanIntegrationTest::class)
    }

    init {
        for(clazz in testClasses) run {
            clazz.java.getDeclaredConstructor().newInstance()
        }
    }
}
