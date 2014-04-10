package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.utils.ImageLoader;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FaqAdapter extends BaseAdapter {
	private Activity activity;
    private List<String> questions;
    private List<String> answers;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    public FaqAdapter(Activity a, List<String> qs, List<String> as) {
        activity = a;
        questions = qs;
        answers = as;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
    
    public int getCount() {
        return questions.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.faq_list_item, null);
 
        TextView question = (TextView)vi.findViewById(R.id.question); // question
        TextView answer = (TextView)vi.findViewById(R.id.answer); // answer
 
        // Setting all values in listview
        question.setText("Q: " + questions.get(position) + "?");
        answer.setText("A: " + answers.get(position));
        return vi;
    }
}