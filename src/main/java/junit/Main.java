package junit;

public class Main
{
    public static void main(String[] args)
    {
        GradeBook gradebook = GradeBook.getGradeBook();
        gradebook.addSubject("maths");
        gradebook.addSubject("science");

        gradebook.addSubjectsMark("maths",4.);
        gradebook.addSubjectsMark("maths",5.);
        gradebook.addSubjectsMark("maths",3.);

        gradebook.addSubjectsMark("science",3.);
        gradebook.addSubjectsMark("science",4.);
        gradebook.addSubjectsMark("science",2.);

        System.out.println(gradebook);
    }

    public static int sum(int ... params)
    {
        int sum=0;
        for(int i=0;i<params.length;i++) sum+=params[i];
        return sum;
    }
}
