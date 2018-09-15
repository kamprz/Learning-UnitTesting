package junit;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubjectTest
{
    @Test
    public void addMarkTest()
    {
        //given
        Subject maths = new Subject("maths");
        Double mark = 4.0;

        //when
        maths.addMark(mark);

        //then
        assertNotNull(maths.getFirstMark());
        assertEquals(maths.getFirstMark(),mark);
        assertEquals(maths.getMarksAmount(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addWrongMark()
    {
        //given
        Subject maths = new Subject("maths");

        //when
        maths.addMark(7.);

        //then IllegalArgumentException
    }

    @Test
    public void meanTest()
    {
        //given
        Subject maths = new Subject("maths");
        maths.addMark(4.);
        maths.addMark(3.);
        maths.addMark(5.);
        Double assertedMean = 4.;

        //when
        Double calculatedMean = maths.mean();

        //then
        assertEquals(assertedMean,calculatedMean);
    }

    @Test
    public void meanWithoutMarks()
    {
        //given
        Subject maths = new Subject("maths");

        //when
        Double calculatedMean = maths.mean();

        //then
        assertNull(calculatedMean);
    }

    @Test
    public void gettingFirstMarkWhenThereIsNone()
    {
        //given
        Subject maths = new Subject("maths");

        //when
        Double mark = maths.getFirstMark();

        //then
        assertNull(mark);
    }
}
