package junit;

import java.util.ArrayList;

public class Subject
{
    private ArrayList<Double> marks = new ArrayList<>();
    private String subjectName;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    void addMark(Double mark) throws IllegalArgumentException
    {
        if(mark<1 || mark > 6) throw new IllegalArgumentException("Mark like given doesn't exist.");
        marks.add(mark);
    }

    public Double mean()
    {
        Double sum=.0;
        for(Double mark: marks)
        {
            sum+=mark;
        }
        if(marks.size()!=0) return (sum/marks.size());
        else return null;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public String toString() {
        return "\n" + subjectName + ' ' + marks;
    }

    public Double getFirstMark()
    {
        if(marks.size()>0) return marks.get(0);
        else return null;
    }
    public int getMarksAmount()
    {
        return marks.size();
    }
}
