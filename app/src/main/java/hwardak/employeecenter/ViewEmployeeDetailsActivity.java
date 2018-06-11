package hwardak.employeecenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;


public class ViewEmployeeDetailsActivity extends AppCompatActivity {

    DBDataAccess dbDataAccess;

    private EditText editText_id;
    private EditText editText_first_name;
    private EditText editText_last_name;
    private EditText editText_middle_name;
    private EditText editText_date_of_birth;
    private EditText editText_sin_number;
    private EditText editText_email;
    private EditText editText_phone_number;
    private EditText editText_street_address;
    private EditText editText_city;
    private EditText editText_province;
    private EditText editText_postal_code;
    private EditText editText_starting_date;
    private ArrayList<EditText> list_editTexts;

    int employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        dbDataAccess = new DBDataAccess(this);

        employeeId = getIntent().getIntExtra("employeeId", 111);

        instantiateViews();
        populateEditTextList();
        loadEmployeeDetails(dbDataAccess.getEmployeeDetails(employeeId));


        editText_id.setText(String.valueOf(employeeId));



    }

    private void loadEmployeeDetails(ArrayList<String> employeeDetails) {

        for(int i = 0; i< employeeDetails.size(); i++){
            list_editTexts.get(i).setText(employeeDetails.get(i));
        }

    }

    private void instantiateViews() {
        editText_id = (EditText) findViewById(R.id.edittext_id);
        editText_first_name = (EditText) findViewById(R.id.edittext_first_name);
        editText_last_name = (EditText) findViewById(R.id.edittext_last_name);
        editText_middle_name = (EditText) findViewById(R.id.edittext_middle_name);
        editText_date_of_birth = (EditText) findViewById(R.id.edittext_date_of_birth);
        editText_sin_number = (EditText) findViewById(R.id.edittext_sin_number);
        editText_email = (EditText) findViewById(R.id.edittext_email);
        editText_phone_number = (EditText) findViewById(R.id.edittext_phone_number);
        editText_street_address = (EditText) findViewById(R.id.edittext_street_address);
        editText_city = (EditText) findViewById(R.id.edittext_city);
        editText_province = (EditText) findViewById(R.id.edittext_province);
        editText_postal_code = (EditText) findViewById(R.id.edittext_postal_code);
        editText_starting_date = (EditText) findViewById(R.id.edittext_starting_date);

    }


    private void populateEditTextList() {
        list_editTexts = new ArrayList<>();

//        list_editTexts.add(editText_id);
        list_editTexts.add(editText_first_name);
        list_editTexts.add(editText_middle_name);
        list_editTexts.add(editText_last_name);
        list_editTexts.add(editText_date_of_birth);
        list_editTexts.add(editText_sin_number);
        list_editTexts.add(editText_email);
        list_editTexts.add(editText_phone_number);
        list_editTexts.add(editText_street_address);
        list_editTexts.add(editText_city);
        list_editTexts.add(editText_province);
        list_editTexts.add(editText_postal_code);
        list_editTexts.add(editText_starting_date);
    }

//    private void getAllEmployeeDetails(){
//        String[] employeeDetails = dbDataAccess.getEmployeeDetails(employeeId);
//
//
//    }



}
