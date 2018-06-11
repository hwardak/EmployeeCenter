package hwardak.employeecenter;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewEmployeesActivity extends AppCompatActivity {

    ListView listview_employee;
    ListAdapter listAdapter;
    ArrayList<String> employeeList;
    DBDataAccess dbDataAccess = new DBDataAccess(this);
    View rowView;
    View previouslySelectedView;
    //    Button deleteEmployeeButton;
    Button viewEmployeeDetailsButton;
    int listViewPosition;
    String employeeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employees);

//        deleteEmployeeButton = (Button) findViewById(R.employeeId.button_delete_employee);
        viewEmployeeDetailsButton = (Button) findViewById(R.id.button_view_details);



        this.updateListView();
        this.instantiateListViewClickListener();




    }

    /**
     * On click listener on the ListView of employees. If an item in the ListView is clicked, it is
     * highlighted, and the previous selection is un-highlighted. The first time an item is selected
     * the deleteEmployeeButton is enabaled.
     */
    private void instantiateListViewClickListener() {

        listview_employee.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                viewEmployeeDetailsButton.setEnabled(true);
                viewEmployeeDetailsButton.setTextColor(Color.parseColor("#41ea12"));

                 employeeID = ((TextView) view.findViewById(R.id.listview_row_employee_id)).getText().toString();

                rowView = view;
                listViewPosition = position;
                //If there is a previously selected view in the list, it will be un-highlighted when
                //a new view within the list is selected.
                if (previouslySelectedView != null) {
                    previouslySelectedView.setBackgroundColor(0);
                    previouslySelectedView = view;
                } else {
                    previouslySelectedView = view;
                }
                //Clicked view in list will be highlighted. Light blue
                view.setBackgroundColor(Color.parseColor("#66ccff"));

            }
        });
    }

    private void updateListView() {
        employeeList = dbDataAccess.getEmployeeList();

        Cursor cursor = dbDataAccess.getEmployeeListCursor();

        String[] columns = {
                DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_ID,
                DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_FIRST_NAME,
                DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_LAST_NAME
        };


        int[] resourceIds = {
                R.id.listview_row_employee_id,
                R.id.listview_row_employee_first_name,
                R.id.listview_row_employee_last_name
        };


        listAdapter = new SimpleCursorAdapter(this, R.layout.employee_listview_row,
                cursor, columns, resourceIds, 0);



        listview_employee = (ListView) findViewById(R.id.listview_employees);
        listview_employee.setAdapter(listAdapter);
    }



    public void viewEmployeeDetailsButtonOnClick(View view) {
        Intent intent = new Intent(this, ViewEmployeeDetailsActivity.class);
        intent.putExtra("employeeId", Integer.parseInt(employeeID));
        startActivity(intent);


    }
}
