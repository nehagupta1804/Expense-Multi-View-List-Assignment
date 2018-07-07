package in.codingninjas.envision.expensemanager;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<Item> itemsList;
    LayoutInflater inflater;



    // Constructor
    public CustomAdapter(Context context, ArrayList<Item> itemsList){
        super(context,0);
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemsList = itemsList;
    }

    // This function return the number of different type of views that will be there in the list view.
    @Override
    public int getViewTypeCount(){
        return(2);
    }

    // This function returns the type of item(in our case header or list item) that adapter wants to know in getView function.
    @Override
    public int getItemViewType(int position) {
        int type=itemsList.get(position).getItemType();
        return(type);
    }

    // This function gives the total count of items that will be there in the list.
    @Override
    public int getCount() {
        return(itemsList.size());
    }

    // This function returns the object of the itemList that has to inflated at that position.
    @Override
    public Object getItem(int position) {
        return(itemsList.get(position));
    }


    // This function returns the unique id associated with every inflated layout, since it's is not useful in our case so
    // we return the position, which is also unique for every item.
    @Override
    public long getItemId(int position) {
        return(position);
    }


    // This is the function in which we have to inflate the layout as per its TYPE
    // this function gets the type of each item from getItemViewType and on the basis of it we apply if else and inflate the layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type=getItemViewType(position);
        if(type==0)
        {
            View output=inflater.inflate(R.layout.header_item_layout,parent,false);
            TextView textview= output.findViewById(R.id.headerTitleTextView);
            HeaderItem item=(HeaderItem)itemsList.get(position);
            textview.setText(item.getHeaderTitle());
            return(output);
        }
        else
        {
            View output=inflater.inflate(R.layout.expense_row_layout,parent,false);
            TextView nameTextview= output.findViewById(R.id.expenseName);
            TextView amountTextview= output.findViewById(R.id.expenseAmount);
            TextView dateTextview= output.findViewById(R.id.expenseDate);
            TextView timeTextview= output.findViewById(R.id.expenseTime);
            Expense item=(Expense) itemsList.get(position);
             nameTextview.setText(item.getName());
             amountTextview.setText(item.getAmount()+"");
             dateTextview.setText(item.getDate());
             timeTextview.setText(item.getTime());
             return(output);
        }


    }


}
