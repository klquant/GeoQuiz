package geoquiz.android.bignerdranch.com.geoquiz;


import android.app.Activity;
//import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.content.Intent; // enables intent

public class QuizActivity extends Activity {

    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX="QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private ImageButton mPrevImgButton;
    private ImageButton mNextImgButton;
    private Button mCheatButton;

    private boolean mIsCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {// pg108
     if (data==null)
     {
         return;
     }
        mIsCheater=data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,false);

    }

    private TrueFalse[] mQuestionBank=new TrueFalse[]
            {
                    new TrueFalse(R.string.question_oceans,true),
                    new TrueFalse(R.string.question_mideast,false),
                    new TrueFalse(R.string.question_africa,false),
                    new TrueFalse(R.string.question_americas,true),
                    new TrueFalse(R.string.question_asia,true),

            };
    private int mCurrentIndex=0;

    private TextView mQuestionTextView;
    public void updateQuestion()
    {
        // pg 78
       // Log.d(TAG,"Updating question text for question #"+ mCurrentIndex, new Exception());
        int question =mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue)
    {// pg109
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId=0;

        if (mIsCheater)
        {
            messageResId=R.string.judgement_toast;
        }
        else
        {
            if (userPressedTrue==answerIsTrue)
            {
                messageResId=R.string.correct_toast;
            }
            else
            {
                messageResId=R.string.incorrect_toast;
            }
        }
        /*
        if (userPressedTrue==answerIsTrue)
        {
            messageResId=R.string.correct_toast;
        }
        else
        {
            messageResId=R.string.incorrect_toast;
        }
        */
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

    }// end checkAnswer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.d(TAG, "onCreate(Bundle) called"); // pg55
        setContentView(R.layout.activity_quiz);

        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener()
                                             {//implements the click activity on testView ie if user clicks on textView of question, it goes tothe next
                                                 @Override
                                                 public void onClick(View v)
                                                 {
                                                     mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                                                     updateQuestion();
                                                 }
                                             }
        );

        //int question=mQuestionBank[mCurrentIndex].getQuestion();
        //mQuestionTextView.setText(question);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Does nothing yet, but soon!
                //Toast.makeText(QuizActivity.this,R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View V)
            {
            // Does nothing yet, but soon
            // Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }


    });

        mPrevImgButton=(ImageButton)findViewById(R.id.prev_ImgButton);
        mPrevImgButton.setOnClickListener(new View.OnClickListener()
        {
         @Override
         public void onClick(View v)
         {
             if (mCurrentIndex==0)
             {
                 mCurrentIndex=(mQuestionBank.length - 1) % mQuestionBank.length;

             }
             else
             {
                 mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
             }

             updateQuestion();

         }

        });

        mNextImgButton=(ImageButton)findViewById(R.id.next_ImgButton);
        mNextImgButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
                public void onClick(View v)
            {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                mIsCheater=false; // pg109
                updateQuestion();
            }
        }
        );

        mNextButton =(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                    mIsCheater=false; // pg109
                    updateQuestion();
                  //  int question=mQuestionBank[mCurrentIndex].getQuestion();
//                    mQuestionTextView.setText(question);
            }

        }
        );




        mPrevButton=(Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener()
        {
        @Override
              public void onClick(View v)
              {
                  if (mCurrentIndex==0)
                  {
                     mCurrentIndex=(mQuestionBank.length - 1) % mQuestionBank.length;

                  }
                  else
                  {
                      mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                  }

                  updateQuestion();

              }
        }
        );// end anonymous class

        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // pg103 & pg105
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                //startActivity(i);
                startActivityForResult(i, 0);
            }


        });
        updateQuestion();


        if (savedInstanceState!=null)
        { // pg 67
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }

        updateQuestion();


    }// end onCreate

    @Override
    public void onStart()
    {
        super.onStart();
        android.util.Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        android.util.Log.d(TAG,"onPause() called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        android.util.Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
        //android.util.Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        android.util.Log.d(TAG, "onDestroy() called");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

}// end QuizActivity
