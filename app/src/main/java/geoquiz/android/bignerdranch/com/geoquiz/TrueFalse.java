package geoquiz.android.bignerdranch.com.geoquiz;

/**
 * Created by user on 26/6/2015.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion)
    {
        //mQuestion=question;
        //mTrueQuestion=trueQuestion;
        setQuestion(question);
        setTrueQuestion(trueQuestion);

    }// constructor
    public int getQuestion()
    {
     return mQuestion;
    }

    public void setQuestion(int question)
    {
        mQuestion=question;
    }

    public boolean isTrueQuestion()
    {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion)
    {
        mTrueQuestion=trueQuestion;
    }
}// end class1
