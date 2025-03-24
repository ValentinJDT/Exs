package test

import test.target.*
import kotlin.reflect.full.createInstance

class Tester {

    val testClasses = buildSet {
        add(AutoCloseTest::class)
//        add(BeanIntegrationTest::class)
//        add(BuilderTest::class)
//        add(CallTest::class)
//        add(CBackMappingTest::class)
//        add(CompareTest::class)
//        add(ConsoleTrickTest::class)
//        add(ContainsTest::class)
//        add(DateEqTest::class)
//        add(DbReaderTest::class)
//        add(DeferTest::class)
//        add(EditTest::class)
//        add(EnumTest::class)
//        add(EqTest::class)
//        add(ExecutorTest::class)
//        add(ExTest::class)
//        add(FileTestersTest::class)
//        add(FilterColTest::class)
//        add(FlowTest::class)
//        add(ForeachTest::class)
//        add(GenerateKeysTest::class)
//        add(GetSetTest::class)
//        add(HerTest::class)
//        add(HtmlTest::class)
//        add(IgnoredRun::class)
//        add(InlineCircleCallTest::class)
//        add(LinkedHashMapOrderTest::class)
//        add(MutatorTest::class)
//        add(NumberTest::class)
//        add(PathTest::class)
//        add(ProgressBarTest::class)
//        add(ProviderTest::class)
//        add(SerializationTest::class)
//        add(SperaTest::class)
//        add(StaticDataTest::class)
//        add(StrFunTest::class)
//        add(SymlinkTest::class)
//        add(UriTest::class)
//        add(WriteFileTest::class)
    }

    init {
        for (clazz in testClasses) run {
            clazz.java.getDeclaredConstructor().newInstance()
        }
    }


//    init {
//        val reflections = org.reflections.Reflections("test.target")
//        val testClasses = reflections.getSubTypesOf(TestClass::class.java)
//
//        for (clazz in testClasses) {
//            try {
//                clazz.kotlin.createInstance()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

}
