package hwardak.employeecenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeCenterMainActivity extends AppCompatActivity {


    private Button button_view_employees;
    private Button button_add_employees;
    private Button button_settings;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_center_main);

        this.instantiateButtons();



    }

    private void instantiateButtons() {
        button_view_employees = (Button) findViewById(R.id.button_view_employees);
        button_add_employees = (Button) findViewById(R.id.button_add_employees);
        button_settings = (Button) findViewById(R.id.button_settings);

    }

    public void viewEmployeesButtonOnClick(View view) {
        Intent intent = new Intent(this, ViewEmployeesActivity.class);
        startActivity(intent);
    }

    public void addEmployeesButtonOnClick(View view) {
        Intent intent = new Intent(this, AddEmployeeActivity.class);
        startActivity(intent);
    }

    public void settingsButtonOnClick(View view) {
    }
}
