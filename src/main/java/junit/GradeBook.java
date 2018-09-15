package junit;

import java.util.HashMap;
import java.util.Map;

public class GradeBook
{
    private Map<String, Subject> subjects = new HashMap<>();
    private static GradeBook singleton;

    public void addSubject(String subject)
    {
        if(!subjects.containsKey(subject))
            subjects.put(subject,new Subject(subject));
    }

    public void addSubject(Subject subject)
    {
        if(!subjects.containsKey(subject.getSubjectName()))
            subjects.put(subject.getSubjectName(),subject);
    }

    public Double totalMean()
    {
        Double mean=.0;
        for(Map.Entry<String, Subject> entry: subjects.entrySet())
        {
            mean += entry.getValue().mean();
        }
        return (mean/subjects.size());
    }

    public Double subjectsMean(String subject)
    {
        return subjects.get(subject).mean();
    }

    public Double subjectsMean(Subject subject)
    {
        return subjects.get(subject.getSubjectName()).mean();
    }

    void addSubjectsMark(String subject, Double mark)
    {
        subjects.get(subject).addMark(mark);
    }

    void addSubjectsMark(Subject subject, Double mark)
    {
        subjects.get(subject.getSubjectName()).addMark(mark);
    }

    public static void createGradeBook()
    {
        if(singleton==null) singleton = new GradeBook();
    }
    static GradeBook getGradeBook()
    {
        if(singleton==null) singleton = new GradeBook();
        return singleton;
    }
    private GradeBook()
    {

    }

    int getNumberOfSubjects()
    {
        return subjects.size();
    }

    void clearGradeBook()
    {
        subjects.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for(Map.Entry<String,Subject> entry : subjects.entrySet())
        {
            result.append("\n" + entry.getValue());
        }

        return result.toString();
    }
}
