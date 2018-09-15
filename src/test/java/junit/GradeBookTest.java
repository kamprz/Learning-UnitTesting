package junit;


import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GradeBookTest
{
    @After
    public void clearGradeBook()
    {
        GradeBook.getGradeBook().clearGradeBook();
    }

    @Test
    public void addingASubjectWithString()
    {
        //given
        String maths = "maths";
        GradeBook gb = GradeBook.getGradeBook();

        //when
        gb.addSubject(maths);

        //then
        assertEquals(1,gb.getNumberOfSubjects());
    }

    @Test
    public void addingSubjectWithSubjectParam()
    {
        //given
        Subject science = new Subject("science");
        GradeBook gb = GradeBook.getGradeBook();

        //when
        gb.addSubject(science);

        //then
        assertEquals(gb.getNumberOfSubjects(),1);
    }

    @Test
    public void addingSameSubject2Times()
    {
        //given
        GradeBook gb = GradeBook.getGradeBook();

        //when
        gb.addSubject("maths");
        gb.addSubject(new Subject("maths"));

        //then
        assertEquals(gb.getNumberOfSubjects(),1);
    }

    @Test
    public void subjectMean()
    {
        //given
        GradeBook gradebook = GradeBook.getGradeBook();
        Subject maths = new Subject("maths");
        maths.addMark(4.);
        maths.addMark(5.);
        maths.addMark(3.);
        gradebook.addSubject(maths);

        //when
        Double assertedMean = 4.;
        Double calculatedMean = gradebook.subjectsMean(maths);

        //then
        assertEquals(assertedMean,calculatedMean);
    }

    @Test
    public void totalMean()
    {
        //given
        GradeBook gradebook = GradeBook.getGradeBook();
        gradebook.addSubject("maths");
        gradebook.addSubject("science");

        gradebook.addSubjectsMark("maths",4.);
        gradebook.addSubjectsMark("maths",5.);
        gradebook.addSubjectsMark("maths",3.);

        gradebook.addSubjectsMark("science",3.);
        gradebook.addSubjectsMark("science",4.);
        gradebook.addSubjectsMark("science",2.);

        //when
        Double assertedMean = 3.5;
        Double calculatedMean = gradebook.totalMean();

        //then
        assertEquals(assertedMean,calculatedMean);
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestsOfSubjectsMeansWithIterable()
    {
        GradeBook gradebook = GradeBook.getGradeBook();
        gradebook.addSubject("maths");
        gradebook.addSubject("science");

        gradebook.addSubjectsMark("maths",4.);
        gradebook.addSubjectsMark("maths",5.);
        gradebook.addSubjectsMark("maths",3.);
        Double mathsMean = 4.;

        gradebook.addSubjectsMark("science",3.);
        gradebook.addSubjectsMark("science",4.);
        gradebook.addSubjectsMark("science",2.);
        Double scienceMean = 3.;

        return Arrays.asList(
                DynamicTest.dynamicTest("Maths mean test",
                        () -> assertEquals(mathsMean, gradebook.subjectsMean("maths"))),
                DynamicTest.dynamicTest("Science mean test",
                        () -> assertEquals(scienceMean, gradebook.subjectsMean("science"))));
    }


}
