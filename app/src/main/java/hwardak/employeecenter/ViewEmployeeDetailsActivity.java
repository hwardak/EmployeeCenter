package hwardak.employeecenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private  ImageView photoImage;

    int employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_form);
        dbDataAccess = new DBDataAccess(this);


        employeeId = getIntent().getIntExtra("employeeId", 111);

        instantiateViews();
        populateEditTextList();
        dbDataAccess.open();
        loadEmployeeDetails(dbDataAccess.getEmployeeDetails(employeeId));
        dbDataAccess.close();
        editText_id.setText(String.valueOf(employeeId));

        prepareFormViewOnly();
        getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        );

    }
    /*
       * activity_employee_form is modified here to fit the privileges of this Activity.
       *
       * All editTexts are disabled.
       */
    private void prepareFormViewOnly() {
        editText_id.setEnabled(false);

        for(int i = 0; i < list_editTexts.size(); i++){
            list_editTexts.get(i).setEnabled(false);
        }



    }

    private void loadEmployeeDetails(ArrayList<String> employeeDetails) {

        //      NEED TO REMOVE THE -1, WAS THERE TO CHECK THE PIC PATH
        for(int i = 0; i< employeeDetails.size()-1; i++){
            list_editTexts.get(i).setText(employeeDetails.get(i));
        }
        String path = employeeDetails.get(employeeDetails.size()-1);
        Log.d("PicturePath: " , path);
        Log.d("fileList",fileList().length+"");

        //load saved image
        try {
            FileInputStream fis = openFileInput(employeeId+"_photo_.jpeg");
            photoImage.setImageBitmap(BitmapFactory.decodeStream(fis));
            //Log.d("bitmapHeight", myBitmap.getHeight()+"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        Log.d("PictureBitMap", String.valueOf(BitmapFactory.decodeFile(employeeDetails.get(employeeDetails.size()-1))));
        //photoImage.setImageBitmap(BitmapFactory.decodeFile(employeeDetails.get(employeeDetails.size()-1)));

        //photoImage.getLayoutParams();
        //ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) photoImage.getLayoutParams();
        //params. = Gravity.CENTER;
        //image.setLayoutParams(params);


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

        photoImage = (ImageView) findViewById(R.id.photoImage);
        //photoImage.setMinimumHeight(1000);
        photoImage.setScaleType(ImageView.ScaleType.FIT_XY);
        // check if photo exists and load it into imageView
        /*if(dbDataAccess.doesIdExist(employeeId+"")){
            ArrayList<String> details = dbDataAccess.getEmployeeDetails(employeeId);
            File imgFile = new File(details.get(details.size()-1));

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                photoImage.setImageBitmap(myBitmap);

            }
        }*/


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
