package geoquiz.android.bignerdranch.com.geoquiz;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent; // enables intent
/**
 * Created by user on 16/8/2015.
 */


public class CheatActivity extends Activity
{
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static final String EXTRA_ANSWER_IS_TRUE="com.bignerdbranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN="com.bignerdbranch.android.geoquiz.answer_shown";// pg106
    private boolean mAnswerIsTrue;

    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }

    @Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);

    mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false); // pg104
    mAnswerTextView=(TextView)findViewById(R.id.answerTextView);
    mShowAnswer=(Button)findViewById(R.id.showAnswerButton);

    //Answer will not be shown until the user presses the button
    setAnswerShownResult(false);//pg106
    mShowAnswer.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            if (mAnswerIsTrue)
            {
                mAnswerTextView.setText(R.string.true_button);
            }
            else
            {
                mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShownResult(true);
        }
    }
    );


    mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
    mAnswerTextView=(TextView)findViewById(R.id.answerTextView);

    mShowAnswer=(Button)findViewById(R.id.showAnswerButton);
    mShowAnswer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mAnswerIsTrue)
            {
                mAnswerTextView.setText(R.string.true_button);
            }
            else
            {
                mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShownResult(true);
        }
    });
}


}// end class


